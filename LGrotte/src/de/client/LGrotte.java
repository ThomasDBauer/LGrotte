package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import de.client.temp.SeedButton;

public class LGrotte implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public void onModuleLoad() {
		
		//Der SeedButton aus dem temp-package
		RootPanel.get().add(new SeedButton());
			
	}
}
