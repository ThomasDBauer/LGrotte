package de.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class SuchprofilMainFrame extends HorizontalPanel{
	
	SuchprofilInfoEditor spie = new SuchprofilInfoEditor();
	public SuchprofilMainFrame() throws Exception{
		this.add(new SuchprofilEditor(spie));
//		this.add(spie);	
	}

}
