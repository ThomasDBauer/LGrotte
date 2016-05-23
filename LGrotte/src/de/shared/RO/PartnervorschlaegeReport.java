package de.shared.RO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

public class PartnervorschlaegeReport extends HorizontalPanel {
	private HorizontalPanel pvR = this;


	public PartnervorschlaegeReport() {

		try {
			ClientSideSettings.getReportService().showAllProfiles(new ReportCallback());
		} catch (Exception e1) {
			pvR.add(new Label(e1.toString()));
			e1.printStackTrace();
		}

	}

	private class ReportCallback implements AsyncCallback<String> {
		public void onFailure(Throwable caught) {
			pvR.add(new Label(caught.toString()));
		}
		
		public void onSuccess(String s) {
			pvR.add(new HTML(s));
			 
			
		}
	}

}