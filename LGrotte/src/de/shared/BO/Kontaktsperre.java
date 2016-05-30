package de.shared.BO;

public class Kontaktsperre extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sperrendesProfil;
	
	private String gesperrtesProfil;

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
