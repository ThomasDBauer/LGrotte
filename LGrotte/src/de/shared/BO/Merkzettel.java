package de.shared.BO;

/**
 *          Die Klasse Merkzettel erweitert die Klasse BusinessObject Der
 *          Merkzettel besteht aus einem gemerkten und einem merkendem Profil
 *          
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der Attribute
	 */
	private String gemerktesProfil;
	private String merkendesProfil;

	/**
	 * Auslesen des gemerkten Profils
	 * 
	 * @return gemerktesProfil
	 */
	public String getGemerktesProfil() {
		return gemerktesProfil;
	}

	/**
	 * Setzen des gemerkten Profils
	 * 
	 * @param gemerktesProfil
	 */
	public void setGemerktesProfil(String gemerktesProfil) {
		this.gemerktesProfil = gemerktesProfil;
	}

	/**
	 * Auslesen des merkenden Profils
	 * 
	 * @return merkendesProfil
	 */
	public String getMerkendesProfil() {
		return merkendesProfil;
	}

	/**
	 * Setzen des merkenden Profils
	 * 
	 * @param merkendesProfil
	 */
	public void setMerkendesProfil(String merkendesProfil) {
		this.merkendesProfil = merkendesProfil;
	}

}
