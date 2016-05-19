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

public class PopupNavi extends PopupPanel {
	private VerticalPanel popupPanel = new VerticalPanel();
	private Button profilBearbeitenButton = new Button("Profil bearbeiten", new PopupClickHandler());
	private Button suchprofilButton = new Button("Suchprofil", new PopupClickHandler());
	private Button merklisteButton = new Button("Merkliste", new PopupClickHandler());
	private Button kontaktsperreButton = new Button("Kontaktsperre", new PopupClickHandler());

//	public void setPopupPosition(int left, int top) {
//		this.setPopupPosition(left, top);
//	}

	public PopupNavi() {
		super(true);
		popupPanel.add(profilBearbeitenButton);
		popupPanel.add(suchprofilButton);
		popupPanel.add(merklisteButton);
		popupPanel.add(kontaktsperreButton);
		setWidget(popupPanel);
	}
	
	private class PopupClickHandler implements ClickHandler{
		public void onClick(ClickEvent e) {
			
			Button popClick = (Button) e.getSource();

			if (!popClick.getStyleName().equals("aktiv")) {
				profilBearbeitenButton.removeStyleName("aktiv");
				suchprofilButton.removeStyleName("aktiv");
				merklisteButton.removeStyleName("aktiv");
				kontaktsperreButton.removeStyleName("aktiv");
				popClick.addStyleName("aktiv");
			
		}
			
			switch (popClick.getText()) {
			case "Profil bearbeiten":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new MeinProfilEditor());
				break;
			case "Suchprofil":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new SuchprofilEditor());
				break;
			case "Merkliste":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						(IsWidget) new MerkzettelEditor());
				break;
			case "Kontaktsperre":
				RootPanel.get("Inhalt_unten").clear();
				RootPanel.get("Inhalt_unten").add(
						new Label("Hier käme die Kontaktsperre hin!"));
				break;
			}
	}
}
}