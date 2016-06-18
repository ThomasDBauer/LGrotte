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
	// Alles um das Profil zu löschen
	private VerticalPanel loeschenLabelPanel = new VerticalPanel();
	private HorizontalPanel loeschenButtonPanel = new HorizontalPanel();
	public final Button profilloeschenButton = new Button("Profil löschen",
			new PopupClickHandler());
	public final Button loeschenjaButton = new Button("Ja", 
			new ProfilLoeschenClickHandler());
	public final Button loeschenneinButton = new Button("Nein", 
			new LoeschenNeinClickHandler());
	public final Label loeschenLabel = new Label("Wollen Sie Ihr Profil wirklich löschen");

	public PopupNavi() {
		super(true);
		profilBearbeitenButton.setStylePrimaryName("navi-button");
		suchprofilButton.setStylePrimaryName("navi-button");
		merklisteButton.setStylePrimaryName("navi-button");
		kontaktsperreButton.setStylePrimaryName("navi-button");
		profilloeschenButton.setStylePrimaryName("navi-button");
		loeschenjaButton.setStylePrimaryName("loeschen-ja");
		loeschenneinButton.setStylePrimaryName("loeschen-nein");
		popupPanel.add(profilBearbeitenButton);
		popupPanel.add(suchprofilButton);
		popupPanel.add(merklisteButton);
		popupPanel.add(kontaktsperreButton);
		popupPanel.add(profilloeschenButton);
		setWidget(popupPanel);
	}
	
	private class ProfilLoeschenCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
		}
		public void onSuccess(Object result) {
		}		
	}
	private class ProfilLoeschenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			try {
				ClientSideSettings.getEditorService().deleteProfil(new ProfilLoeschenCallback());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Window.open(LGrotte.logOutUrl, "_self", "");
		}	
	}
	
	private class LoeschenNeinClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			loeschenLabelPanel.clear();
		}	
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

			// PopupNavi popup = new PopupNavi();
			// popup = Navigation.popup;
			// popup.hide();

			switch (popClick.getText()) {
			case "Profil bearbeiten":
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Inhalt").add(new HTML(
						"<h2>Dein Profil bearbeiten</h2>"));
//				RootPanel.get("Einstellungen").add(new MeinProfilReport());
//				RootPanel.get("Inhalt_unten").add(new MeinProfilEditor());
				try {
					RootPanel.get("Inhalt").add(new ProfilEditor());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "Suchprofil":
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Content").clear();
//				RootPanel.get("Einstellungen").add(new MeinProfilReport());
				RootPanel.get("Inhalt")
				.add(new HTML(
						"<h2>Deine Suchprofile</h2>"));
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
//				RootPanel.get("Einstellungen").add(new MeinProfilReport());
				RootPanel.get("Inhalt")
				.add(new HTML(
						"<h2>Deine Merkliste</h2>"));
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
//				RootPanel.get("Einstellungen").add(new MeinProfilReport());
				RootPanel.get("Inhalt")
				.add(new HTML(
						"<h2>Kontaktsperre</h2>"));
				try {
					RootPanel.get("Inhalt").add(new KontaktsperreEditor());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			case "Profil löschen":
				loeschenButtonPanel.add(loeschenjaButton);
				loeschenButtonPanel.add(loeschenneinButton);
				loeschenLabelPanel.add(loeschenLabel);
				loeschenLabelPanel.add(loeschenButtonPanel);
				setWidget(loeschenLabelPanel);
				break;
			}
		}
	}
}