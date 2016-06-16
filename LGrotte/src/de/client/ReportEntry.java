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
	private Button alleAnzeigenButton = new Button("Alle Profile", new AlleProfileHandler());
	private ListBox suchprofilListbox = new ListBox();

	// Buttons
	private HorizontalPanel navPanel = new HorizontalPanel();
	// Hauptfenster
	private HorizontalPanel mainPanel = new HorizontalPanel();
	// Reports + Suchprofil-Details
	private VerticalPanel links = new VerticalPanel();
	private VerticalPanel rechts = new VerticalPanel();

	/*
	 * onModuleLoad()
	 */
	public void onModuleLoad() {
		RootPanel.get().add(navPanel);
		RootPanel.get().add(mainPanel);
		mainPanel.add(links);
		mainPanel.add(rechts);
		reportService = ClientSideSettings.getReportService();
		LoginServiceAsync loginService = ClientSideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html", new AsyncCallback<Profil>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("login callback" + caught.toString()));
			}
			public void onSuccess(Profil result) {
				navPanel.add(alleAnzeigenButton);
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
					for (int i = 0; i < result.size(); i++) {
						// ChangeHandler
						suchprofilListbox.addChangeHandler(new SucheChangeHandler(result.elementAt(i)));
						// ListBox-Text
						suchprofilListbox.addItem(result.elementAt(i).getSuchprofilname());
					}
					// Hinzufügen zum GUI
					rechts.add(suchprofilListbox);
					try {
						// Anzeigen des ersten Suchprofils TODO lieber alle
						// Reports laden.
						loadReportBySuchprofil(result.elementAt(0));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Alle Profile laden
	public void loadAll() throws Exception{
		reportService.getReports(new AsyncCallback<Vector<ProfilReport>>()
		{
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("ReportEntry.getAllProfilesCallback "
						+caught.toString() ));
			}
			public void onSuccess(Vector<ProfilReport> result) {
				links.clear();
				HTMLWriter writer = new HTMLWriter();
				writer.process(result);
				links.add(new HTML(writer.getReportText()));
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
				links.clear();
				if(result.size()==0)links.add(new Label("Keine Ergebnisse"));
				HTMLWriter writer = new HTMLWriter();
				writer.process(result);
				links.add(new HTML(writer.getReportText()));
			}
		}));
	}

	/*
	 * Der ChangeHandler der Suchprofil-ListBox
	 */
	private class SucheChangeHandler implements ChangeHandler {
		private Suchprofil sp;
		public SucheChangeHandler(Suchprofil sp) {
			this.sp = sp;
		}
		public void onChange(ChangeEvent event) {
			try {
				loadReportBySuchprofil(sp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * ClickHandler für AlleProfile-Button
	 */
	public class AlleProfileHandler implements ClickHandler{
		public void onClick(ClickEvent e){
			try {
				loadAll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
