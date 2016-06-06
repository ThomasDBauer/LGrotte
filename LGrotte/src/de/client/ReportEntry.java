package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.client.report.HTMLWriter;
import de.shared.ReportServiceAsync;
import de.shared.RO.ProfilReport;

public class ReportEntry implements EntryPoint {

	private ReportServiceAsync reportService;

	public void onModuleLoad() {
		reportService = ClientSideSettings.getReportService();

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
	}

}
