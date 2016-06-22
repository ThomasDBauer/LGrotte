package de.client.gui;

import com.google.gwt.user.client.ui.HTML;

public class Impressum extends HTML {

	
	private HTML impressum = new HTML(
			
			
			"<div class='" + "Impressum" + "'>"
			+ "<b>" +"Angaben gem&auml&szlig §5 Telemediengesetz"+"</b>"+"</br>"
			+ "Hochschule der Medien"+ "</br>"
			+ "Wirtschaftsinformatik und Digitale Medien"+ "</br>"
			+ "Nobelstra&szlige 10<br />"
			+ "70569 Stuttgart"+ "</br>"+ "</br>"
			+ "Kontakt"+ "</br>" +  "Telefon: " + "+49 711 8923 10"+"</br>"
			+ "E-Mail: " + "coming soon..." + "</div>");

	public HTML getImpressum() {
		return impressum;
	}

	

}
