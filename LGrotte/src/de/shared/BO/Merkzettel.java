package de.shared.BO;

/**
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/*
	 * Merkzettel bestehen aus einem gemerkten und einem 
	 * merkendem Profil
	 */
	private String gemerktesProfil;
	private String merkendesProfil;

	/*
	 * Get und Set Methoden
	 */
	public String getGemerktesProfil() {
		return gemerktesProfil;
	}

	public void setGemerktesProfil(String gemerktesProfil) {
		this.gemerktesProfil = gemerktesProfil;
	}

	public String getMerkendesProfil() {
		return merkendesProfil;
	}

	public void setMerkendesProfil(String merkendesProfil) {
		this.merkendesProfil = merkendesProfil;
	}

}
