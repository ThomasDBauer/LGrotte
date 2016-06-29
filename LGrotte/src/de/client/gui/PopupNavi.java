package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.client.LGrotte;
import de.client.gui.Navigation;
import de.shared.BO.Profil;

public class PopupNavi extends HorizontalPanel {
	public final Button profilBearbeitenButton = new Button(
			"Profil bearbeiten", new PopupClickHandler());
	public final Button suchprofilButton = new Button("Suchprofil",
			new PopupClickHandler());
	public final Button merklisteButton = new Button("Merkliste",
			new PopupClickHandler());
	public final Button kontaktsperreButton = new Button("Kontaktsperre",
			new PopupClickHandler());
	public final Button profilloeschenButton = new Button("Profil löschen",
			new PopupClickHandler());
	public final static ProfilLoeschenPopup profilLoeschenPopup = new ProfilLoeschenPopup();

	public PopupNavi() {
		// super(true);
		profilBearbeitenButton.setStylePrimaryName("navi-button");
		suchprofilButton.setStylePrimaryName("navi-button");
		merklisteButton.setStylePrimaryName("navi-button");
		kontaktsperreButton.setStylePrimaryName("navi-button");
		profilloeschenButton.setStylePrimaryName("navi-button");
		profilBearbeitenButton.setStyleName("navi-button-big", true);
		profilloeschenButton.setStyleName("navi-button-big", true);
		this.addStyleName("Navi-Pop-Panel");
		this.add(profilBearbeitenButton);
		this.add(suchprofilButton);
		this.add(merklisteButton);
		this.add(kontaktsperreButton);
		this.add(profilloeschenButton);
		
		profilloeschenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent e) {
				profilLoeschenPopup
						.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
							public void setPosition(int offsetWidth,
									int offsetHeight) {
								int left = profilloeschenButton
										.getAbsoluteLeft() - 60;
								int top = profilloeschenButton
										.getAbsoluteTop() + 45;
								profilLoeschenPopup.setPopupPosition(
										left, top);
								profilLoeschenPopup.show();
							}
						});
			}
		});
	}

	private class PopupClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			Button popClick = (Button) e.getSource();

			if (!popClick.getStyleName().equals("aktiv")) {
				profilBearbeitenButton.removeStyleName("aktiv");
				suchprofilButton.removeStyleName("aktiv");
				merklisteButton.removeStyleName("aktiv");
				kontaktsperreButton.removeStyleName("aktiv");
				profilloeschenButton.removeStyleName("aktiv");
				popClick.addStyleName("aktiv");
			}

			switch (popClick.getText()) {
			case "Profil bearbeiten":
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Inhalt").add(
						new HTML("<h2>Dein Profil bearbeiten</h2>"));
				try {
					RootPanel.get("Inhalt").add(new ProfilErstellenEditor());
					RootPanel.get("Zusatz").add(new MeinProfilEditor());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "Suchprofil":
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Inhalt").add(
						new HTML("<h2>Deine Suchprofile</h2>"));
				try {
					RootPanel.get("Inhalt").add(new SuchprofilMainFrame());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Merkliste":
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Inhalt").add(
						new HTML("<h2>Deine Merkliste</h2>"));
				try {
					RootPanel.get("Inhalt").add(new MerkzettelEditor());
				} catch (Exception e1) {
					RootPanel.get("Inhalt").add(new Label("Fehler im Popup"));
				}
				break;
			case "Kontaktsperre":
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Inhalt").add(new HTML("<h2>Kontaktsperre</h2>"));
				try {
					RootPanel.get("Inhalt").add(new KontaktsperreEditor());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
		}
	}
}
