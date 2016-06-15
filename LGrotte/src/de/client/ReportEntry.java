package de.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
	private Button alleAnzeigenButton = new Button("Alle Profle");
	private Button meinProfilButton = new Button("Mein Profil");
	private Button gesperrteProfile = new Button("Gesperrte Profile");
	private Button gemerkteProfile = new Button("Gemerkte Profile");
	private Vector<Suchprofil> suchprofile;
	private ListBox suchprofilListbox = new ListBox();
	
	
	//Buttons
	private HorizontalPanel navPanel = new HorizontalPanel();
	//Hauptfenster
	private HorizontalPanel mainPanel = new HorizontalPanel();
	//Reports + Suchprofil-Details
	private HorizontalPanel hpanel = new HorizontalPanel();
	private VerticalPanel links = new VerticalPanel();
	private VerticalPanel rechts = new VerticalPanel();
	
	
	public void onModuleLoad() {
		
		RootPanel.get().add(navPanel);
		RootPanel.get().add(mainPanel);
		mainPanel.add(hpanel);
		hpanel.add(links);
		hpanel.add(rechts);
		
		
		
		reportService = ClientSideSettings.getReportService();

		LoginServiceAsync loginService = ClientSideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html", new AsyncCallback<Profil>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("login callback" + caught.toString()));
			}

			public void onSuccess(Profil result) {
				navPanel.add(alleAnzeigenButton);
				navPanel.add(meinProfilButton);
				navPanel.add(gesperrteProfile);
				navPanel.add(gemerkteProfile);

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

		// GET ALL REPORTS klappt

		// try {
		// reportService.getAllReports(new AsyncCallback<Vector<ProfilReport>>()
		// {
		//
		// public void onFailure(Throwable caught) {
		// RootPanel.get().add(new Label("ReportEntry.getAllProfilesCallback "
		// +caught.toString() ));
		// }
		//
		// @Override
		// public void onSuccess(Vector<ProfilReport> result) {
		// HTMLWriter writer = new HTMLWriter();
		// writer.process(result);
		// RootPanel.get().add(new HTML(writer.getReportText()));
		// }
		//
		// });
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void loadReportBySuchprofil(Suchprofil sp) throws Exception{
		reportService.getReportsBySuchprofil(sp, (new AsyncCallback<Vector<ProfilReport>>(){
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
		}));
	}

	public void loadSuchprofile() {
		try {
			reportService.getSuchprofile(new AsyncCallback<Vector<Suchprofil>>() {
				public void onFailure(Throwable caught) {
					Window.alert("getSuchprofileCallback " + caught.toString());
				}

				public void onSuccess(Vector<Suchprofil> result) {
					suchprofile = result;
					for(int i = 0; i < result.size(); i++){
						suchprofilListbox.addChangeHandler(new SucheChangeHandler(result.elementAt(i)));
						suchprofilListbox.addItem(result.elementAt(i).getSuchprofilname());
					}
					rechts.add(suchprofilListbox);
					try {
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
	
	public Suchprofil selectSuchprofil(){
		Suchprofil sp = null;
		for(int i = 0; i < suchprofile.size(); i++){
			if(suchprofile.elementAt(i).getSuchprofilname().equals(
					suchprofilListbox.getItemText(suchprofilListbox.getSelectedIndex()))){
				sp = suchprofile.elementAt(i);
				break;
			};
		}
		return sp;
	}
	
	private class SucheChangeHandler implements ChangeHandler{
		
		private Suchprofil sp;
		
		public SucheChangeHandler(Suchprofil sp){
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
}
