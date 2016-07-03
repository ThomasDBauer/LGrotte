package de.client.gui;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * GUI Klasse das HorizontalPanel vererbt bekommt
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */
public class ProfilEditor extends HorizontalPanel{

	/**
	 * Konstruktor in dem Dein Profil bearbeiten erzeugt wird
	 */
	public ProfilEditor() throws Exception{
		
		this.add(new HTML(
						"<h2 style = \"color: #c0c0c0\">"
						+ "Dein Profil bearbeiten</h2>"));
		/*
		 * Aufruf von MeinProfilEditor
		 */
		this.add(new MeinProfilEditor());
	}
	
}
