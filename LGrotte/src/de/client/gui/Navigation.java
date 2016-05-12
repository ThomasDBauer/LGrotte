package de.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.client.ClientSideSettings;
import de.client.TestService;
import de.client.TestServiceAsync;
import de.client.temp.SeedButton;
import de.shared.ReportService;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;

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
	private Button findLoveButton = new Button("Find Love", new NavigationsButtonHandler());
	private Button profilButton = new Button("Mein Profil");
	private Button merklisteButton = new Button("Merkliste");
	private Button impressumButton = new Button("Impressum");

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

	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			RootPanel.get("Inhalt_unten").add(new PartnervorschlaegeReport());
		}

}}