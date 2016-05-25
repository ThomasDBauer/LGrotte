package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.client.gui.Navigation;

public class PopupNavi extends PopupPanel {
	private HorizontalPanel popupPanel = new HorizontalPanel();
	public final Button profilBearbeitenButton = new Button(
			"Profil bearbeiten", new PopupClickHandler());
	public final Button suchprofilButton = new Button("Suchprofil",
			new PopupClickHandler());
	public final Button merklisteButton = new Button("Merkliste",
			new PopupClickHandler());
	public final Button kontaktsperreButton = new Button("Kontaktsperre",
			new PopupClickHandler());

	public PopupNavi() {
		super(true);
		profilBearbeitenButton.setStylePrimaryName("navi-button");
		suchprofilButton.setStylePrimaryName("navi-button");
		merklisteButton.setStylePrimaryName("navi-button");
		kontaktsperreButton.setStylePrimaryName("navi-button");
		popupPanel.add(profilBearbeitenButton);
		popupPanel.add(suchprofilButton);
		popupPanel.add(merklisteButton);
		popupPanel.add(kontaktsperreButton);
		setWidget(popupPanel);
	}

	private class PopupClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			Button popClick = (Button) e.getSource();

			if (!popClick.getStyleName().equals("aktiv")) {
				profilBearbeitenButton.removeStyleName("aktiv");
				suchprofilButton.removeStyleName("aktiv");
				merklisteButton.removeStyleName("aktiv");
				kontaktsperreButton.removeStyleName("aktiv");
				popClick.addStyleName("aktiv");
			}

			// PopupNavi popup = new PopupNavi();
			// popup = Navigation.popup;
			// popup.hide();

			switch (popClick.getText()) {
			case "Profil bearbeiten":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Mitte").clear();
				RootPanel.get("Inhalt_unten").add(new MeinProfilEditor());
				try {
					RootPanel.get("Mitte").add(new ProfilEigenschaftEditor());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Suchprofil":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Mitte").clear();
				RootPanel.get("Inhalt_unten").add(new SuchprofilEditor());
				break;
			case "Merkliste":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Mitte").clear();
				RootPanel.get("Inhalt_unten").add(new MerkzettelEditor());
				break;
			case "Kontaktsperre":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Mitte").clear();
				RootPanel.get("Inhalt_unten").add(
						new Label("Hier kaeme die Kontaktsperre hin!"));
				break;

			}
		}
	}
}