package de.shared.BO;

public class Eigenschaft extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String erlaeuterung;
	
	private int auswahl;
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getErlaeuterung() {
		return erlaeuterung;
	}

	public void setErlaeuterung(String name) {
		this.erlaeuterung = name;
	}

	public int getAuswahl() {
		return auswahl;
	}

	public void setAuswahl(int auswahl) {
		this.auswahl = auswahl;
	}
	
	
	
}
