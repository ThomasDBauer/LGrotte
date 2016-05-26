package de.shared.BO;

public class Merkzettel extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String merkendesProfil;
	private String gemerktesProfil;
	
	public String getMerkendesProfil() {
		return merkendesProfil;
	}
	public void setMerkendesProfil(String merkendesProfil) {
		this.merkendesProfil = merkendesProfil;
	}
	public String getGemerktesProfil() {
		return gemerktesProfil;
	}
	public void setGemerktesProfil(String gemerktesProfil) {
		this.gemerktesProfil = gemerktesProfil;
	}

	
}
