package de.shared.BO;

/**
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 * 
 *          Die Klasse Kontaktsperre erweitert die Klasse BusinessObject.
 *          Kontaktsperren bestehen aus einem sperrenden und einem gesperrten
 *          Profil.
 * 
 */

public class Kontaktsperre extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/*
	 * Deklaration der Attribute
	 */
	private String sperrendesProfil;
	private String gesperrtesProfil;

	/*
	 * Auslesen des sperrenden Profils
	 * 
	 * @return sperrendesProfil
	 */
	public String getSperrendesProfil() {
		return sperrendesProfil;
	}

	/*
	 * Setzen des sperrenden Profils
	 * 
	 * @param sperrendesProfil
	 */
	public void setSperrendesProfil(String sperrendesProfil) {
		this.sperrendesProfil = sperrendesProfil;
	}

	/*
	 * Auslesen des gesperrten Profils
	 * 
	 * @return gesperrtesProfil
	 */
	public String getGesperrtesProfil() {
		return gesperrtesProfil;
	}

	/*
	 * Setzen des gesperrten Profils
	 * 
	 * @param gesperrtesProfil
	 */
	public void setGesperrtesProfil(String gesperrtesProfil) {
		this.gesperrtesProfil = gesperrtesProfil;
	}

}
