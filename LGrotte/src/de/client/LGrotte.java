package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		
		//Der FindLove-Button
		final Button findLoveButton = new Button("Find Love");
		findLoveButton.setStylePrimaryName("navi-button");
		navPanel.add(findLoveButton);
		
		//Der Profil-Button
		final Button profilButton = new Button("Mein Profil");
		profilButton.setStylePrimaryName("navi-button");
		navPanel.add(profilButton);
		
		//Der Merkliste-Button
		final Button merklisteButton = new Button("Merkliste");
		merklisteButton.setStylePrimaryName("navi-button");
		navPanel.add(merklisteButton);
		
		//Der Impressum-Button
		final Button impressumButton = new Button("Impressum");
		impressumButton.setStylePrimaryName("navi-button");
		navPanel.add(impressumButton);
		
		findLoveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				
			}
		});	
	}
}
