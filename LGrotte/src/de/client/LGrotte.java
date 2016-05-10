package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.temp.SeedButton;

public class LGrotte implements EntryPoint {

	private final TestServiceAsync greetingService = GWT.create(TestService.class);

	public void onModuleLoad() {
		
		HorizontalPanel navPanel = new HorizontalPanel();
		
		//Der SeedButton aus dem temp-package
		RootPanel.get("Inhalt_oben").add(new SeedButton());
		RootPanel.get("Navi").add(navPanel);
		
		final Button findLove = new Button("Find Love");
		findLove.setStylePrimaryName("navi-button");
		navPanel.add(findLove);
		
		final Button profil = new Button("Profil");
		profil.setStylePrimaryName("navi-button");
		navPanel.add(profil);
		
		final Button merkliste = new Button("Merkliste");
		merkliste.setStylePrimaryName("navi-button");
		navPanel.add(merkliste);
		
		final Button impressum = new Button("Impressum");
		impressum.setStylePrimaryName("navi-button");
		navPanel.add(impressum);
			
	}
}
