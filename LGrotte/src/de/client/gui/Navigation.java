package de.client.gui;

import com.google.api.server.spi.Constant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class Navigation extends HorizontalPanel {

	public Navigation navigation = this;

	private final Button findLoveButton = new Button("Find Love",
			new NavigationsButtonHandler());
	private final Button profilButton = new Button("Mein Profil",
			new ProfilPopupClickHandler());
	private final Button impressumButton = new Button("Impressum",
			new NavigationsButtonHandler());
	public PopupNavi popup = new PopupNavi();

	public Navigation() {
		findLoveButton.setStylePrimaryName("navi-button");
		this.add(findLoveButton);

		profilButton.setStylePrimaryName("navi-button");
		this.add(profilButton);

		impressumButton.setStylePrimaryName("navi-button");
		this.add(impressumButton);

	}

	private class ProfilPopupClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			Button active = (Button) e.getSource();

			RootPanel.get("Zusatz").clear();
			RootPanel.get("Inhalt").clear();
			RootPanel.get("Content").clear();
			RootPanel.get("Inhalt").add(new HTML(
					"<h2>Dein Profil</h2>"));
			RootPanel.get("Inhalt").add(new ProfilAnzeigenEditor());

			if (!active.getStyleName().equals("aktiv")) {
				findLoveButton.removeStyleName("aktiv");
				profilButton.removeStyleName("aktiv");
				impressumButton.removeStyleName("aktiv");
				active.addStyleName("aktiv");
//				RootPanel.get("Navi-Pop-Container").add(new HTML("<div id=\"Navi-Pop\"></div>"));
				RootPanel.get("Navi-Pop").add(popup);
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
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Navi-Pop").clear();
				RootPanel.get("Inhalt").add(
						new HTML("<h2>Deine Partnervorschlaege</h2>"));
				try {
					RootPanel.get("Inhalt").add(new FindLove());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Impressum":
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Navi-Pop").clear();
				RootPanel.get("Content").add(new HTML("<h2>Impressum</h2>"));
				// RootPanel.get("Inhalt_unten").add(new ImpressumReport());
				break;
			}
		};
	}
}
