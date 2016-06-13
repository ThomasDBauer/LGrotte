package de.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.client.report.HTMLWriter;
import de.shared.LoginServiceAsync;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;
	
public class ReportEntry implements EntryPoint {

	private ReportServiceAsync reportService;
	private Button alleAnzeigenButton = new Button("Alle Profle");
	private Button meinProfilButton = new Button("Mein Profil");
	private Button gesperrteProfile = new Button("Gesperrte Profile");
	private Button gemerkteProfile = new Button("Gemerkte Profile");

	public void onModuleLoad() {
		reportService = ClientSideSettings.getReportService();
		
		LoginServiceAsync loginService = ClientSideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html", new AsyncCallback<Profil>(){
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("login callback" + caught.toString()));
			}
			public void onSuccess(Profil result) {
				RootPanel.get().add(new Label("Eingeloggter User: " + result.getEmail()));
				RootPanel.get().add(alleAnzeigenButton);
				RootPanel.get().add(meinProfilButton);
				RootPanel.get().add(gesperrteProfile);
				RootPanel.get().add(gemerkteProfile);
				
				reportService.setUser(result, new AsyncCallback(){
					public void onFailure(Throwable caught) {
						RootPanel.get().add(new Label("SetUserCallback " + caught.toString()));
					}
					public void onSuccess(Object result) {
					}
				});
			}
		});

		try {
			reportService.getAllReports(new AsyncCallback<Vector<ProfilReport>>() {

				public void onFailure(Throwable caught) {
					RootPanel.get().add(new Label("ReportEntry.getAllProfilesCallback "
							+caught.toString() ));
				}

				@Override
				public void onSuccess(Vector<ProfilReport> result) {
					HTMLWriter writer = new HTMLWriter();
					writer.process(result);
					RootPanel.get().add(new HTML(writer.getReportText()));
				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
