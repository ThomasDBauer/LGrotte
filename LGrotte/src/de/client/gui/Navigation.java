package de.client.gui;

import com.google.api.server.spi.Constant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.client.ClientSideSettings;
import de.client.TestService;
import de.client.TestServiceAsync;
//import de.client.gui.PopupNavi.NavigationPopup;
import de.client.temp.SeedButton;
import de.shared.ReportService;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;

public class Navigation extends HorizontalPanel {

	private Navigation navigation = this;
	private Button findLoveButton = new Button("Find Love",
			new NavigationsButtonHandler());
	private Button profilButton = new Button("Mein Profil",
			new ProfilPopupClickHandler());
	private Button suchprofilButton = new Button("Suchprofil",
			new NavigationsButtonHandler());
	private Button merklisteButton = new Button("Merkliste",
			new NavigationsButtonHandler());
	private Button kontaktsperreButton = new Button("Kontaktsperre",
			new NavigationsButtonHandler());
	private Button impressumButton = new Button("Impressum",
			new NavigationsButtonHandler());
	
	private static class PopupNavi extends PopupPanel {

	      public PopupNavi() {
	         super(true);
	         setWidget(new Label("Click outside of this popup to close it"));
	      }
	}
	
	public class ProfilPopupClickHandler implements ClickHandler {
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

	            final PopupNavi popup = new PopupNavi();
	            popup.setPopupPositionAndShow(new PopupPanel.PositionCallback(){
	               public void setPosition(int offsetWidth, int offsetHeight) {
	                  int left = (Window.getClientWidth() - offsetWidth) / 3;
	                  int top = (Window.getClientHeight() - offsetHeight) / 3;
	                  popup.setPopupPosition(left, top);
	               }
	            });
		}
	}

	public Navigation() {
		findLoveButton.setStylePrimaryName("navi-button");
		this.add(findLoveButton);

		profilButton.setStylePrimaryName("navi-button");
		this.add(profilButton);

		suchprofilButton.setStylePrimaryName("navi-button");
		this.add(suchprofilButton);

		merklisteButton.setStylePrimaryName("navi-button");
		this.add(merklisteButton);

		kontaktsperreButton.setStylePrimaryName("navi-button");
		this.add(kontaktsperreButton);

		impressumButton.setStylePrimaryName("navi-button");
		this.add(impressumButton);
	};

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

			switch (active.getText()) {
			case "Find Love":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new PartnervorschlaegeReport());
				break;
			case "Mein Profil":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(new MeinProfilEditor());
				break;
			case "Suchprofil":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new Label("Hier kommt das Suchprofil hin!"));
				break;
			case "Merkliste":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new Label("Hier kommt die Merkzettelauflistung hin!"));
				break;

			case "Kontaktsperre":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new Label("Hier kommt die Kontaktsperre hin!"));
				break;

			case "Impressum":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new Label("Hier kommt das Impressum hin!"));
				break;
			}
		}
	}
}