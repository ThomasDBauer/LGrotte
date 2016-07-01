package de.client.gui;

import com.google.gwt.user.client.ui.HTML;

/**
 * Das Impressum als HTML-Text
 * 
 * @author Sedat Akar & Lukas Kircher
 *
 * @version 1.0
 */

public class Impressum extends HTML {

	//Der Konstruktor, der den HTML-Text des erstellten Impressum setzt
	public Impressum (){	
	this.setHTML((
			"<div class='Impressum'>"
			+ "<b>" +"Angaben gem&auml&szlig §5 Telemediengesetz</b></br></br>"
			+ "Hochschule der Medien"+ "</br>"
			+ "<b>Wirtschaftsinformatik und Digitale Medien</b></br>"
			+ "Nobelstra&szlige 10</br>"
			+ "70569 Stuttgart</br></br>"
			+ "Kontakt</br>Telefon: +49 711 8923 10</br>"
			+ "<br><br>Der Studiengang Wirtschaftsinformatik und digitale "
			+ "Medien<br>wird rechtlich vertreten durch die Hochschule der Med"
			+ "ien Stuttgart. <br> <br><A HREF=\"https://www.hdm-stuttgart.de/"
			+ "impressum\"TARGET=\"_blank\">Impressum der Hochschule</A>"
			+ "</div>"));
	}
}
