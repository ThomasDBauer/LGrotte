package de.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ProfilEditor extends HorizontalPanel{

	
	public ProfilEditor() throws Exception{
		HorizontalPanel hpanel = new HorizontalPanel();
		hpanel.add(new HTML(
						"<h2 style = \"color: #c0c0c0\">Dein Profil bearbeiten</h2>"));
		this.add(new MeinProfilEditor());
//		this.add(new ProfilErstellenEditor());
//		this.add(new ProfilEigAuswahlEditor());
		this.add(new MeinProfilEditor());
	}
	
}
