package de.client.gui;

import com.google.gwt.user.client.ui.HTML;

public class ImpressumReport extends HTML{
	
	private HTML impressum = new HTML ("<div class='" + 
			"Impressum"+ 
			"'><h2>Impressum</h2>" + 
			"<h2>Angaben gemäß § 5 TMG:</h2>" +
			"<p>Dana Thüring<br />" +
			"Nobelstraße 10<br />" +
			"70569 Stuttgart" +
			"</p>" +
			"<h2>Kontakt:</h2>" +
			"<table><tr>" +
			"<td>Telefon:</td>" +
			"<td>0711 8923 10</td></tr>" +
			"<tr><td>E-Mail:</td>" +
			"<td>dt018@hdm-stuttgart.de</td>" +
			"</tr></table><p></div>");
	
	public HTML getImpressum(){
		return impressum;
	}

}
