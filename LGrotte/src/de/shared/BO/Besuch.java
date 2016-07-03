package de.shared.BO;

/**
 * 
 *          Ein Besuch besteht aus einem besuchendem Profil und einem besuchten
 *          Profil.
 * 
 * @author Thomas Bauer
 * 
 * @version 1.0
 */

public class Besuch {

	/**
	 * Deklaration der Attribute
	 */
	private Profil besuchtesProfil;
	private Profil besuchendesProfil;

	/**
	 * Auslesen des besuchten Profils
	 * 
	 * @return besuchtesProfil
	 */
	public Profil getBesuchtesProfil() {
		return besuchtesProfil;
	}

	/**
	 * Setzen des besuchten Profils
	 * 
	 * @param besuchtesProfil
	 */
	public void setBesuchtesProfil(Profil besuchtesProfil) {
		this.besuchtesProfil = besuchtesProfil;
	}

	/**
	 * Auslesen des besuchenden Profils
	 * 
	 * @return besuchendesProfil
	 */
	public Profil getBesuchendesProfil() {
		return besuchendesProfil;
	}

	/**
	 * Setzen des besuchten Profils
	 * 
	 * @param besuchendesProfil
	 */
	public void setBesuchendesProfil(Profil besuchendesProfil) {
		this.besuchendesProfil = besuchendesProfil;
	}

}
