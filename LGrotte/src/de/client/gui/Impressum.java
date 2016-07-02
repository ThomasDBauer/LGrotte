package de.client.gui;

import com.google.gwt.user.client.ui.HTML;

/**
 * Die Impressum-Klasse erbt von HTML. Sie setzt den Text, der 
 * als Impressum angezeigt.
 * 
 * @author Sedat Akar & Lukas Kircher
 *
 * @version 1.0
 */
public class Impressum extends HTML {

	/**
	 * Setzt den HTML-Text des erstellten Impressum, der in einem Div
	 * steht und unter anderem einen Link zum Impressum der HdM beinhaltet
	 */
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
