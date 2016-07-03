package de.shared.BO;

/**
 * Die Klasse Auswahl erweitert die Klasse BusinessObject.
 * Auswahl-Objekte repräsentieren die Auswahl zu Auswahl-Eigenschaften.
 * Entsprechend tragen sie die Information, zu welcher Eigenschaft sie
 * gehören und den Wert der Auswahl.
 * 
 * @author Thomas Bauer
 * 
 * @version 1.0 
 */
public class Auswahl extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der Attribute
	 */
	private int eigenschaft_id;
	private String value;

	/**
	 * Auslesen der Eigenschaft_id
	 * 
	 * @return eigenschaft_id
	 */
	public int getEigenschaft_id() {
		return eigenschaft_id;
	}

	/**
	 * Setzen der Eigenschaft_id
	 * 
	 * @param eigenschaft_id
	 */
	public void setEigenschaft_id(int eigenschaft_id) {
		this.eigenschaft_id = eigenschaft_id;
	}

	/**
	 * Auslesen des Value
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Setzen des Value
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
