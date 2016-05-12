package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

public class PartnervorschlaegeReport extends HorizontalPanel {
	private HorizontalPanel pvR = this;

	public PartnervorschlaegeReport() {
		try {
			ClientSideSettings.getReportService().showProfilReport(1, new ReportCallback());
		} catch (Exception e1) {
			pvR.add(new Label(e1.toString() + " im Partnervorschlaege@Konstruktor"));
			e1.printStackTrace();
		}

	}

	private class ReportCallback implements AsyncCallback<Profil> {
		public void onFailure(Throwable caught) {
			pvR.add(new Label(caught.toString() + " im ReportCallback"));
		}

		public void onSuccess(Profil profil) {
			pvR.add(new Label(profil.getFname()));
			pvR.add(new Label(profil.getLname()));
		}
	}

	// private class findLoveButtonClickHandler implements ClickHandler {
	// public void onClick(ClickEvent e) {
	// try {
	// ClientSideSettings.getReportService().showProfilReport(1, new
	// ReportCallback());
	// } catch (Exception e1) {
	// pvR.add(new Label(e1.toString()));
	// e1.printStackTrace();
	// }
	//
	// }
	// }

	// private class ReportCallback implements AsyncCallback<Profil> {
	// public void onFailure(Throwable caught) {
	// pvR.add(new Label(caught.toString()));
	// }
	//
	// public void onSuccess(Profil profil) {
	// pvR.add(new Label(profil.getFname()));
	// pvR.add(new Label(profil.getLname()));
	// }
	// }
}
