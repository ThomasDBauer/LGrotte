package de.shared.RO;

/**
 * Die Klasse ProfilAttribut erweitert die Klasse ProfilInformation.
 * Zur Darstellung von Profilattributen, wie z.B. Alter, Raucher,
 * Religion, Koerpergroesse folgt sie dem Muster der Superklasse:
 * Eigenschaft : Info
 * 
 * @author Thomas Bauer und Sedat Akar
 * 
 * @version 1.0
 */

public class ProfilAttribut extends ProfilInformation {

	private static final long serialVersionUID = 1L;

	/**
	 * Auslesen des Namens
	 * 
	 * @return name
	 */
	public String getName() {
		return super.getName();
	}

	/**
	 * Setzen des Namens
	 * 
	 * @param name
	 */
	public void setName(String name) {
		super.setName(name);
	}

	/**
	 * Auslesen des Werts
	 * 
	 * @return wert
	 */
	public String getWert() {
		return super.getWert();
	}

	/**
	 * Setzen des Werts
	 * 
	 * @param wert
	 */
	public void setWert(String wert) {
		super.setWert(wert);
	}

}
