package de.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
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
import de.server.db.SuchprofilMapper;
import de.shared.LoginServiceAsync;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;
import de.shared.RO.ProfilReport;

public class ReportEntry implements EntryPoint {

	private ReportServiceAsync reportService;
	private Button alleAnzeigenButton = new Button("Alle Profile", 
			new AlleProfileHandler());
	private Button nichtBesuchteButton = new Button("Nicht besuchte Profile", 
			new NichtBesuchteHandler());
	private Label suchProfileAnzeigenLabel = new Label("Erstelle Deinen Suchprofil-Report");
	private ListBox suchprofilListbox = new ListBox();
	private Vector<Suchprofil> suchprofile;
	private Suchprofil p = new Suchprofil();
	private VerticalPanel suchprofilPanel = new VerticalPanel();

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
				alleAnzeigenButton.setStylePrimaryName("navi-button");
				navPanel.add(nichtBesuchteButton);
				nichtBesuchteButton.setStylePrimaryName("navi-button");
				rechts.add(suchProfileAnzeigenLabel);
				suchProfileAnzeigenLabel.setStylePrimaryName("textlabel");
				rechts.add(suchprofilListbox);
				rechts.add(suchprofilPanel);
				suchprofilListbox.setStylePrimaryName("listbox");
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
	 * L�dt initial alle Suchprofile. --> Baut die ListBox zum Selektieren auf
	 * --> �bergibt dem ListBox ChangeHandler das Suchprofil.
	 */
	public void loadSuchprofile() {
		try {
			reportService.getSuchprofile(new AsyncCallback<Vector<Suchprofil>>() {
				public void onFailure(Throwable caught) {
					Window.alert("getSuchprofileCallback " + caught.toString());
				}
				public void onSuccess(Vector<Suchprofil> result) {
					suchprofile = result;
					suchprofilListbox.addItem("Waehle Dein Suchprofil aus");
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
	
	//HtML Reports erstellen lassen f�r Report-Listen
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
				RootPanel.get().add(new Label("ReportEntry.loadReportBySuchprofil() " + caught.toString()));
			}
			public void onSuccess(Vector<ProfilReport> result) {
				loadHtmlReports(result);
			}
		}));
	}
	
	/*
	 * Baut Reports f�r nicht besuchte Profile auf
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
			SelectElement selectElement = suchprofilListbox.getElement().cast();
            selectElement.getOptions().getItem(0).setDisabled(true);
			Suchprofil sp = getSuchprofil();
			if(sp != null){
				updateSuchprofilPanel(sp);
				try {
					loadReportBySuchprofil(sp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateSuchprofilPanel(Suchprofil sp){
		suchprofilPanel.setStylePrimaryName("suchprofil-filter");
		suchprofilPanel.clear();
		suchprofilPanel.add(new Label("Geschlecht: " + sp.getHaarfarbe()));
		suchprofilPanel.add(new Label("Raucher: " + sp.getRaucher()));
		suchprofilPanel.add(new Label("Haarfarbe: " + sp.getHaarfarbe()));
		suchprofilPanel.add(new Label("Religion: " + sp.getReligion()));
//		suchprofilPanel.add(new Label("Körpergröße: " + sp));
//		suchprofilPanel.add(new Label("Alter: " + sp));
		
		
		for(int i = 0; i < sp.getProfileigenschaften().size(); i++){
			ProfilEigenschaft pe = sp.getProfileigenschaften().elementAt(i);
			suchprofilPanel.add(new Label(pe.getName() + ": " + pe.getWert()));
		}
	}
	
	/*
	 * Weil wir leider nicht mit einem DataListProvider arbeiten,
	 * m�ssen wir die Strings der ListBox mit den Suchprofilen abgleichen.
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
	 * ClickHandler f�r AlleProfile-Button
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
	 * ClickHandler f�r Nicht besuchte Profile-Button
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
