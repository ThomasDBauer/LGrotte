package de.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;

public class PopupNavi extends PopupPanel {
	private HorizontalPanel popupPanel = new HorizontalPanel();
	private Button profilBearbeitenButton = new Button("Profil bearbeiten");
	private Button suchprofilButton = new Button("Suchprofil");
	private Button merklisteButton = new Button("Merkliste");
	private Button kontaktsperreButton = new Button("Kontaktsperre");

	public void setPopupPosition(int left, int top) {
		this.setPopupPosition(left, top);
	}

	public PopupNavi() {
		super(true);
		popupPanel.add(profilBearbeitenButton);
		popupPanel.add(suchprofilButton);
		popupPanel.add(merklisteButton);
		popupPanel.add(kontaktsperreButton);
		setWidget(popupPanel);
	}

}
