package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.client.LGrotte;

/**
 * Der PopupPanel, fuer die Logoutabfrage
 * 
 * @author Lukas Kircher
 * 
 * @version 1.0
 *
 */

public class LogOutPopUp extends PopupPanel {
	// Label und zwei Button, zum befuellen des Panels
	private VerticalPanel popupPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	public final Button jaButton = new Button("Ja", new LogOutClickHandler());
	public final Button neinButton = new Button
			("Nein", new HideClickHandler());
	private Label logOutText = new Label
			("Möchtest du dich wirklich abmelden?");

	/*
	 * Im Konstruktor wird das Panel sichtbar, Styles werden zugewiesen und dem
	 * Panel hinzugefuegt
	 */
	public LogOutPopUp() {
		super(true);
		jaButton.setStylePrimaryName("logout-ja");
		neinButton.setStylePrimaryName("logout-nein");
		logOutText.setStylePrimaryName("logout-Text");
		popupPanel.add(logOutText);
		buttonPanel.add(jaButton);
		buttonPanel.add(neinButton);
		popupPanel.add(buttonPanel);
		setWidget(popupPanel);
	}

	// ClickHandler zum schliessen des Popups
	public class HideClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			LogOutPopUp hideIt = LGrotte.getLogOutPop();
			hideIt.hide();
			LGrotte.setLogOutPop(hideIt);
		}
	}

	// ClickHandler zum durchfuehren des Logouts
	private class LogOutClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			Window.open(LGrotte.logOutUrl, "_self", "");
		}
	}

}
