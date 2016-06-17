package de.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.report.HTMLWriter;
import de.shared.LoginServiceAsync;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilReport;

public class ReportEntry implements EntryPoint {

	private ReportServiceAsync reportService;
	private Button alleAnzeigenButton = new Button("Alle Profile", 
			new AlleProfileHandler());
	private Button nichtBesuchteButton = new Button("Nicht besuchte Profile", 
			new NichtBesuchteHandler());
	private Label suchProfileAnzeigenLabel = new Label("Nach Suchprofil:");
	private ListBox suchprofilListbox = new ListBox();
	private Vector<Suchprofil> suchprofile;

	// Buttons
	private HorizontalPanel navPanel = new HorizontalPanel();
	// Reports + Suchprofil-Details
	private VerticalPanel links = new VerticalPanel();
	private VerticalPanel rechts = new VerticalPanel();

	/*
	 * onModuleLoad()
	 */
	public void onModuleLoad() {
		RootPanel.get("Navi").add(navPanel);
		RootPanel.get("Body-left").add(links);
		RootPanel.get("Body-right").add(rechts);
		suchprofilListbox.addClickHandler(new SucheChangeHandler());
		reportService = ClientSideSettings.getReportService();
		LoginServiceAsync loginService = ClientSideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html", new AsyncCallback<Profil>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("login callback" + caught.toString()));
			}
			public void onSuccess(Profil result) {
				navPanel.add(alleAnzeigenButton);
				navPanel.add(nichtBesuchteButton);
				navPanel.add(suchProfileAnzeigenLabel);
				navPanel.add(suchprofilListbox);
				reportService.setUser(result, new AsyncCallback() {
					public void onFailure(Throwable caught) {
						RootPanel.get().add(new Label("SetUserCallback " + caught.toString()));
					}
					public void onSuccess(Object result) {
						loadSuchprofile();
					}
				});
			}
		});
	}
	
	/*
	 * Lädt initial alle Suchprofile. --> Baut die ListBox zum Selektieren auf
	 * --> Übergibt dem ListBox ChangeHandler das Suchprofil.
	 */
	public void loadSuchprofile() {
		try {
			reportService.getSuchprofile(new AsyncCallback<Vector<Suchprofil>>() {
				public void onFailure(Throwable caught) {
					Window.alert("getSuchprofileCallback " + caught.toString());
				}
				public void onSuccess(Vector<Suchprofil> result) {
					suchprofile = result;
					for (int i = 0; i < result.size(); i++) {
						suchprofilListbox.addItem(result.elementAt(i).getSuchprofilname());
					}
					try {
						loadAllReports();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//HtML Reports erstellen lassen für Report-Listen
	public void loadHtmlReports(Vector<ProfilReport> reports){
		links.clear();
		if(reports.size()==0)links.add(new Label("Keine Ergebnisse"));
		HTMLWriter writer = new HTMLWriter();
		writer.process(reports);
		links.add(new HTML(writer.getReportText()));
	}
	
	//Alle Profile laden
	public void loadAllReports() throws Exception{
		reportService.getReports(new AsyncCallback<Vector<ProfilReport>>()
		{
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("ReportEntry.getAllProfilesCallback "
						+caught.toString() ));
			}
			public void onSuccess(Vector<ProfilReport> result) {
				loadHtmlReports(result);
			}
		});
	}
	

	/*
	 * Baut Reports nach Suchprofil auf. --> Wird vom ListBox-ChangeHandler
	 * aufgerufen
	 */
	public void loadReportBySuchprofil(Suchprofil sp) throws Exception {
		reportService.getReports(sp, (new AsyncCallback<Vector<ProfilReport>>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("ReportEntry.getAllProfilesCallback " + caught.toString()));
			}
			public void onSuccess(Vector<ProfilReport> result) {
				loadHtmlReports(result);
			}
		}));
	}
	
	/*
	 * Baut Reports für nicht besuchte Profile auf
	 */
	public void loadReportsForNotVisited() throws Exception {
		reportService.getNotVisitedReports(new AsyncCallback<Vector<ProfilReport>>(){
			public void onFailure(Throwable caught) {
				Window.alert("Entry.loadReportsForNotVisited " + caught.toString());
			}
			public void onSuccess(Vector<ProfilReport> result) {
				loadHtmlReports(result);
			}
		});
	}

	
	
	
	/*
	 * Der ChangeHandler der Suchprofil-ListBox
	 */
	private class SucheChangeHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			try {
				loadReportBySuchprofil(getSuchprofil());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Weil wir leider nicht mit einem DataListProvider arbeiten,
	 * müssen wir die Strings der ListBox mit den Suchprofilen abgleichen.
	 */
	public Suchprofil getSuchprofil(){
		Suchprofil sp = null;
		for(int i = 0; i < suchprofile.size(); i++){
			if(suchprofile.elementAt(i).getSuchprofilname().equals(
					suchprofilListbox.getItemText(suchprofilListbox.getSelectedIndex()))){
				sp = suchprofile.elementAt(i);
				break;
			}
		}
		return sp;
	}
	
	/*
	 * ClickHandler für AlleProfile-Button
	 */
	public class AlleProfileHandler implements ClickHandler{
		public void onClick(ClickEvent e){
			try {
				loadAllReports();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/*
	 * ClickHandler für Nicht besuchte Profile-Button
	 */
	public class NichtBesuchteHandler implements ClickHandler{
		public void onClick(ClickEvent e){
			try {
				loadReportsForNotVisited();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
}
