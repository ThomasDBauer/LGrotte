package de.client.gui;

import com.google.api.server.spi.Constant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

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
	private Button findLoveButton = new Button("Find Love",
			new NavigationsButtonHandler());
	private Button profilButton = new Button("Mein Profil",
			new NavigationsButtonHandler());
	private Button suchprofilButton = new Button("Suchprofil",
			new NavigationsButtonHandler());
	private Button merklisteButton = new Button("Merkliste",
			new NavigationsButtonHandler());
	private Button impressumButton = new Button("Impressum",
			new NavigationsButtonHandler());

	public Navigation() {
		findLoveButton.setStylePrimaryName("navi-button");
		this.add(findLoveButton);

		profilButton.setStylePrimaryName("navi-button");
		this.add(profilButton);
		
		suchprofilButton.setStylePrimaryName("navi-button");
		this.add(suchprofilButton);

		merklisteButton.setStylePrimaryName("navi-button");
		this.add(merklisteButton);

		impressumButton.setStylePrimaryName("navi-button");
		this.add(impressumButton);
	};

	private class ActiveButtonHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			Button active = (Button) e.getSource();
			active.setStylePrimaryName("aktiver-navi-button");
		}
	}

	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			
			Button active = (Button) e.getSource();
			
			if (!active.getStyleName().equals("aktiv")) {
				findLoveButton.removeStyleName("aktiv");
				profilButton.removeStyleName("aktiv");
				suchprofilButton.removeStyleName("aktiv");
				merklisteButton.removeStyleName("aktiv");
				impressumButton.removeStyleName("aktiv");
				active.addStyleName("aktiv");
			}
			
			
			
// Jetzt muss gewährleistet sein, dass nur ein Button den Style aktiv haben kann,
// dann kann ma prüfen, welcher Style aktiv ist und dementsprechend die Formulare adden und clearen
			
// Für jedes Gui eine id, welche zur eindeutigen

			// switch (buttonName) {
			// case "Find Love":
			// RootPanel.get("Inhalt_unten").clear();
			// RootPanel.get("Inhalt_unten").add(
			// new PartnervorschlaegeReport());
			// break;
			// case "Mein Profil":
			// RootPanel.get("Inhalt_unten").clear();
			// RootPanel.get("Inhalt_unten").add(new Editor());
			// break;
			// case "Merkliste":
			// RootPanel.get("Inhalt_unten").clear();
			// RootPanel.get("Inhalt_unten").add(
			// new Label("Hier kommt die Merkzettelauflistung hin!"));
			// break;
			//
			// case "Impressum":
			// RootPanel.get("Inhalt_unten").clear();
			// RootPanel.get("Inhalt_unten").add(
			// new Label("Hier kommt die Merkzettelauflistung hin!"));
			// break;
			//
			// default:
			// RootPanel.get("Inhalt_unten").clear();
			// RootPanel.get("Inhalt_unten").add(new Label("Error 404"));
			// }
		}

	}
}