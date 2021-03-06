package de.shared.BO;

/**
 * Die Klasse Info erweitert die Klasse BusinessObject.
 * Informationen zu einer Eigenschaft
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class Info extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der Attribute
	 */
	private int id;
	private String value;
	private int eigenschaft;

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
	 * Auslesen des Values
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Setzen des Values
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Auslesen der Eigenschaft
	 * 
	 * @return eigenschaft
	 */
	public int getEigenschaft() {
		return eigenschaft;
	}

	/**
	 * Setzen des Eigenschaft
	 * 
	 * @param eigenschaft
	 */
	public void setEigenschaft(int eigenschaft) {
		this.eigenschaft = eigenschaft;
	}

}
