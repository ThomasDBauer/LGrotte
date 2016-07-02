package de.shared.BO;

/**
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 * 
 */

public class Kontaktsperre extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/*
	 * Kontaktsperren bestehen aus einem sperrenden und einem
	 * gesperrten Profil.
	 */
	private String sperrendesProfil;

	private String gesperrtesProfil;

	
	/*
	 * Get und Set Methoden
	 */
	public String getSperrendesProfil() {
		return sperrendesProfil;
	}

	public void setSperrendesProfil(String sperrendesProfil) {
		this.sperrendesProfil = sperrendesProfil;
	}

	public String getGesperrtesProfil() {
		return gesperrtesProfil;
	}

	public void setGesperrtesProfil(String gesperrtesProfil) {
		this.gesperrtesProfil = gesperrtesProfil;
	}

}
