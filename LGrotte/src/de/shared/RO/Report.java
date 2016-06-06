package de.shared.RO;

import java.io.Serializable;

public abstract class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @author Thomas Bauer
	 * 
	 *         Ein Report-Object kann zwischen dem Server und dem Client über
	 *         Netzwerke kommuniziert werden. Beispiele für implementierende
	 *         Klassen sind ProfilReports, die ein einzelnes Profil mit allen
	 *         Details anzeigen, auch die des eingeloggten Users. Außerdem gibt
	 *         es Reports, die viele Profile auf einmal darstellen: alle Profile
	 *         nach Suchprofil oder noch nicht besuchte Profile sind Beispiele.
	 * 
	 *         Report-Objects werden den (HTML)Writern übergeben, die die
	 *         Inhalte der Reports auslesen und in die richtige Darstellung
	 *         formatieren (in unserem Fall HTML)
	 */

	/*
	 * Jeder Report hat einen Titel. Beispiele sind: "Mein Profil",
	 * "Profil von -name-", "Noch nicht besuchte Profile",
	 * "Ergebnisse von Suchprofil xy"
	 */
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
