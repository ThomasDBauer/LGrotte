package de.client.gui;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

public class PartnervorschlaegeReport extends HorizontalPanel {
	private HorizontalPanel pvR = this;
	private VerticalPanel vPanel = new VerticalPanel();

	public PartnervorschlaegeReport() {
		try {
			ClientSideSettings.getReportService().showProfilReport(1, new ReportCallback());
		} catch (Exception e1) {
			pvR.add(new Label(e1.toString()));
			e1.printStackTrace();
		}

	}

	private class ReportCallback implements AsyncCallback<Profil> {
		public void onFailure(Throwable caught) {
			pvR.add(new Label(caught.toString()));
		}

		public void onSuccess(Profil profil) {
			pvR.add(new Label(profil.getFname() + " " + profil.getLname()));
			pvR.add(new Label(profil.getGeschlecht()));
			pvR.add(new Label(profil.getHaarfarbe()));
			pvR.add(new Label(profil.getReligion()));
			pvR.add(new Label(profil.getRaucher()));
			

		}
	}

}
