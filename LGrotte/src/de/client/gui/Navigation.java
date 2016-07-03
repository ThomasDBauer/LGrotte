package de.client.gui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Navigations-Gui, welches vom HorizontalPanel erbt. Sie stellt die Button zur
 * Navigation durch das Programm dar.
 * 
 * @author Lukas Kircher
 *
 * @version 1.0
 */
public class Navigation extends HorizontalPanel {
	
	/**
	 * Instanz-Variablen werden deklariert
	 */
	public Navigation navigation = this;

	//Buttons, die dem User die Interaktion ermoeglichen werden erstellt
	private final Button findLoveButton = new Button("Find Love",
			new NavigationsButtonHandler());
	private final Button profilButton = new Button("Mein Profil",
			new ProfilPopupClickHandler());
	private final Button impressumButton = new Button("Impressum",
			new NavigationsButtonHandler());
	
	//Das PopupPanel, dass beim anklicken des Button "mein Profil" erscheint
	public PopupNavi popup = new PopupNavi();
	
	//Das anzuzeigende Impressum
	public Impressum imp = new Impressum();

	/**
	 * Im No-Argument-Konstruktor werden Styles zugewiesen und die Button
	 * dem Panel zugefuegt
	 */
	public Navigation() {
		findLoveButton.setStylePrimaryName("navi-button");
		this.add(findLoveButton);

		profilButton.setStylePrimaryName("navi-button");
		this.add(profilButton);

		impressumButton.setStylePrimaryName("navi-button");
		this.add(impressumButton);

	}
	
	/**
	 * Beim Klick auf den Button "Mein Profil" wird das Profil, 
	 * durch das Erzeugen einer neuen Instanz 
	 * der Klasse ProfilAnzeigenEditor angzeigt,
	 * die Divs im RootPanel werden gecleart & dass PopupPanel-Navi geaddet
	 */
	private class ProfilPopupClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			RootPanel.get("Zusatz").clear();
			RootPanel.get("Inhalt").clear();
			RootPanel.get("Content").clear();
			RootPanel.get("Inhalt").add(new HTML("<h2>Dein Profil</h2>"));
			RootPanel.get("Inhalt").add(new ProfilAnzeigenEditor());
			RootPanel.get("Navi-Pop").add(popup);
		}
	}

	/**
	 * Im NavigationsButtonHandler wird zuerst dem geklickten Button 
	 * ein anderer Style zugewiesen
	 * Je nachdem, welcher Button aufgerufen wird wird ein neues
	 * GUI-Objekt mit passender HTML-Ueberschrift erzeugt 
	 * und dem RootPanel angefuegt
	 */
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
					e1.printStackTrace();
				}
				break;
			case "Impressum":
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Navi-Pop").clear();
				RootPanel.get("Inhalt").add(new HTML("<h2>Impressum</h2>"));
				RootPanel.get("Inhalt").add(imp);
				break;
			}
		};
	}
}
