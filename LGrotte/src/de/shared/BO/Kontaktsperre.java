package de.shared.BO;

public class Kontaktsperre extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Profil sperrendesProfil;
	
	private Profil gesperrtesProfil;

	
	
	public Profil getSperrendesProfil() {
		return sperrendesProfil;
	}

	public void setSperrendesProfil(Profil sperrendesProfil) {
		this.sperrendesProfil = sperrendesProfil;
	}

	public Profil getGesperrtesProfil() {
		return gesperrtesProfil;
	}

	public void setGesperrtesProfil(Profil gesperrtesProfil) {
		this.gesperrtesProfil = gesperrtesProfil;
	}
	
	
	
}
