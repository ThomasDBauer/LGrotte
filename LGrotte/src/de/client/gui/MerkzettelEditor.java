package de.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MerkzettelEditor extends VerticalPanel{
	Label test = new Label("Test");
	
	public MerkzettelEditor(){
		RootPanel.get("Inhalt_oben").add(test);
	}
}
