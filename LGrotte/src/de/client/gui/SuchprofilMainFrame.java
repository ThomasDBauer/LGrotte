package de.client.gui;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Die SuchprofilMainFrame ruft den SuchprofilEditor auf
 * 
 * @author Thomas Bauer
 * 
 * 
 * @version 1.0
 */

public class SuchprofilMainFrame extends HorizontalPanel{
	
	/**
	 * SuchprofilEditor bekommt den SuchprofilInfoEditor als Parameter
	 */
	SuchprofilInfoEditor spie = new SuchprofilInfoEditor();
	public SuchprofilMainFrame() throws Exception{
		this.add(new SuchprofilEditor(spie));
	}

}
