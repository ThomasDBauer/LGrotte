package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.gui.Editor;
import de.client.gui.Navigation;
import de.client.temp.SeedButton;

public class LGrotte implements EntryPoint {

	private final TestServiceAsync greetingService = GWT.create(TestService.class);

	public void onModuleLoad() {
		
		RootPanel.get("Inhalt_oben").add(new SeedButton());
		RootPanel.get("Navi").add(new Navigation());
		RootPanel.get("Inhalt_unten").add(new Editor());
		
		
	}
}
