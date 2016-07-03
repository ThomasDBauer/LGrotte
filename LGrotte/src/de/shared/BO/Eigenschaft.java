package de.shared.BO;

/**
 *          Die Klasse Eigenschaft erweiter die Klasse BusinessObject.
 *          Eigenschaften bestehen aus einer ID, einer Erläuterung und der
 *          Information, ob sie Auswahl- oder Freitext-Eigenschaften sind.
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der Attribute
	 */
	private int id;
	private String erlaeuterung;
	private int auswahl;

	/**
	 * Auslesen der ID
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setzen der ID
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Auslesen der Erlaeuterung
	 * 
	 * @return erlaeuterung
	 */
	public String getErlaeuterung() {
		return erlaeuterung;
	}

	/**
	 * Setzen der Erlaeuterung
	 * 
	 * @param name
	 */
	public void setErlaeuterung(String name) {
		this.erlaeuterung = name;
	}

	/**
	 * Auslesen der Auswahl
	 * 
	 * @return auswahl
	 */
	public int getAuswahl() {
		return auswahl;
	}

	/**
	 * Setzen der Auswahl
	 * 
	 * @param auswahl
	 */
	public void setAuswahl(int auswahl) {
		this.auswahl = auswahl;
	}

}
