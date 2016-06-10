package de.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

	public void onModuleLoad() {
		reportService = ClientSideSettings.getReportService();
		
		LoginServiceAsync loginService = ClientSideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html", new AsyncCallback<Profil>(){
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("login callback" + caught.toString()));
			}
			public void onSuccess(Profil result) {
				RootPanel.get().add(new Label("Eingeloggter User: " + result.getEmail()));
				
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
			reportService.getProfilReport("Anna@LG", new AsyncCallback<ProfilReport>() {

				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(ProfilReport result) {

					HTMLWriter writer = new HTMLWriter();
					writer.process(result);
					RootPanel.get().add(new HTML(writer.getReportText()));
				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			reportService.getProfilInfos(new AsyncCallback<Vector<ProfilInformation>>() {
				public void onFailure(Throwable caught) {
					
				}
				public void onSuccess(Vector<ProfilInformation> result) {
					FlexTable table = new FlexTable();
					for(int i = 0; i < result.size(); i++){
						table.setWidget(i, 0, new Label(result.elementAt(i).getName()));
						table.setWidget(i, 1, new Label(result.elementAt(i).getWert()));
					}
					RootPanel.get().add(table);
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
