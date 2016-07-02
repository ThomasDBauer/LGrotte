package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.client.LGrotte;


public class ProfilLoeschenPopup extends PopupPanel {
	/**
	 * Erstellen verschiedner Buttons, Panels und Label
	 */
		private VerticalPanel loeschenLabelPanel = new VerticalPanel();
		private HorizontalPanel loeschenButtonPanel = new HorizontalPanel();
		public final Button loeschenjaButton = new Button("Ja", 
				new ProfilLoeschenClickHandler());
		public final Button loeschenneinButton = new Button("Nein", 
				new LoeschenNeinClickHandler());
		public final Label loeschenLabel = new Label("Möchtest du dein Profil wirklich löschen");
		/**
		 * Stylen der GUI Elemente im Konstruktor
		 */
		public ProfilLoeschenPopup(){
			loeschenjaButton.setStylePrimaryName("loeschen-button");
			loeschenneinButton.setStylePrimaryName("loeschen-button");
			loeschenLabel.setStylePrimaryName("logout-Text");
			loeschenjaButton.setStyleName("loeschen-ja", true);
			loeschenneinButton.setStyleName("loeschen-nein", true);
			loeschenButtonPanel.add(loeschenjaButton);
			loeschenButtonPanel.add(loeschenneinButton);
			loeschenLabelPanel.add(loeschenLabel);
			loeschenLabelPanel.add(loeschenButtonPanel);
			this.add(loeschenLabelPanel);
		}
		
		/**
		 * Clickhandler um Popup bei Auswahl Nein zu schließen
		 */
		private class LoeschenNeinClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				PopupNavi.profilLoeschenPopup.hide();
			}	
		}
		/**
		 * Clickhandler zum löschen des Profils
		 */
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
		/**
		 * Callback für das Loeschen
		 */
		private class ProfilLoeschenCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Object result) {
			}		
		}

}
