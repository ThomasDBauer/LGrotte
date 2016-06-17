package de.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class SuchprofilMainFrame extends HorizontalPanel{
	
	public SuchprofilMainFrame() throws Exception{
		SuchprofilInfoEditor spie = new SuchprofilInfoEditor();
		this.add(new SuchprofilEditor(spie));
		this.add(spie);
		
	}
}
