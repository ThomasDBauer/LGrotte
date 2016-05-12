package de.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.client.TestService;
import de.client.TestServiceAsync;
import de.client.temp.SeedButton;
import de.shared.ReportService;
import de.shared.ReportServiceAsync;

public class Navigation extends HorizontalPanel {

	// private static Navigation navigation;
	//
	// public static Navigation navigation() {
	// if (navigation == null) {
	// navigation = new Navigation();
	// }
	// return navigation;
	// }
	private Navigation navigation = this;
	final Button findLoveButton = new Button("Find Love");
	final Button profilButton = new Button("Mein Profil");
	final Button merklisteButton = new Button("Merkliste");
	final Button impressumButton = new Button("Impressum");

	public Navigation() {
		// Navigation navigation = new Navigation() ;
		findLoveButton.setStylePrimaryName("navi-button");
		this.add(findLoveButton);

		profilButton.setStylePrimaryName("navi-button");
		this.add(profilButton);

		merklisteButton.setStylePrimaryName("navi-button");
		this.add(merklisteButton);

		impressumButton.setStylePrimaryName("navi-button");
		this.add(impressumButton);
	};

	private class findLoveButtonClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			ReportServiceAsync reportService = GWT.create(ReportService.class);
			try {
				reportService.seed(new ReportCallback());
			} catch (Exception e1) {
				navigation.add(new Label(e1.toString()));
				e1.printStackTrace();
			}

		}
	}
	
	private class ReportCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			navigation.add(new Label(caught.toString()));
		}
		
		public void onSuccess(Object result) {
			navigation.add(new Label("onSuccess@callback"));
		}
	}
}