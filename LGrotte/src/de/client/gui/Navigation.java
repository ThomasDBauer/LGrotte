package de.client.gui;

import com.google.api.server.spi.Constant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class Navigation extends HorizontalPanel {

	private Navigation navigation = this;
	private final Button findLoveButton = new Button("Find Love",
			new NavigationsButtonHandler());
	private final Button profilButton = new Button("Mein Profil",
			new ProfilPopupClickHandler());
	private final Button impressumButton = new Button("Impressum",
			new NavigationsButtonHandler());

	private final PopupNavi popup = new PopupNavi();

	public Navigation() {
		findLoveButton.setStylePrimaryName("navi-button");
		this.add(findLoveButton);

		profilButton.setStylePrimaryName("navi-button");
		this.add(profilButton);

		impressumButton.setStylePrimaryName("navi-button");
		this.add(impressumButton);
	}

	class ProfilPopupClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			Button active = (Button) e.getSource();

			if (!active.getStyleName().equals("aktiv")) {
				findLoveButton.removeStyleName("aktiv");
				profilButton.removeStyleName("aktiv");
				impressumButton.removeStyleName("aktiv");
				active.addStyleName("aktiv");

				popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(int offsetWidth, int offsetHeight) {
						int left = profilButton.getAbsoluteLeft();
						int top = profilButton.getAbsoluteTop() + 30;
						popup.setPopupPosition(left, top);
					}

				});
			}
		}
	}

	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			Button active = (Button) e.getSource();

			if (!active.getStyleName().equals("aktiv")) {
				findLoveButton.removeStyleName("aktiv");
				profilButton.removeStyleName("aktiv");
				impressumButton.removeStyleName("aktiv");
				active.addStyleName("aktiv");
			}

			switch (active.getText()) {
			case "Find Love":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new PartnervorschlaegeReport());
				break;
<<<<<<< HEAD
=======
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

>>>>>>> refs/heads/master
			case "Impressum":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new Label("Hier kommt das Impressum hin!"));
				break;
			}
		};
	};
}
