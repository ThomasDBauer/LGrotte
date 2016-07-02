package de.shared.BO;

/**
 * 
 * 
 * @author Thomas Bauer
 * 
 * @version 1.0
 * 
 * Auswahl-Objekte repräsentieren die Auswahl zu Auswahl-Eigenschaften.
 * Entsprechend tragen sie die Information, zu welcher Eigenschaft sie
 * gehören und den Wert der Auswahl.
 * 
 */
public class Auswahl extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int eigenschaft_id;
	private String value;

	
	/*
	 * Get und Set Methoden
	 */
	
	public int getEigenschaft_id() {
		return eigenschaft_id;
	}

	public void setEigenschaft_id(int eigenschaft_id) {
		this.eigenschaft_id = eigenschaft_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
