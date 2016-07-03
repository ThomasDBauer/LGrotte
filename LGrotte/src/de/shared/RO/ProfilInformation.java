package de.shared.RO;

import java.io.Serializable;

/**
 * Super-Klasse von @class ProfilAttribut und
 * @class ProfilEigenschaft
 * Traegt die Gemeinsamkeit, dass Informationen immer nach dem Schema
 * >>> Attribut : Information <<< aufgebaut sind.
 * 
 * @author Thomas Bauer
 * 
 * @version 1.0
 */
public abstract class ProfilInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Das Attribut
	 */
	private String name;

	/**
	 * Der Wert
	 */
	private String wert;

	/**
	 * Ein Null-Argument Konstruktor
	 */
	public ProfilInformation() {
	}

	/**
	 * Ein Konstruktor zum Setzen der beiden elementaren Werte
	 */
	public ProfilInformation(String name, String wert) {
		this.name = name;
		this.wert = wert;
	}

	/**
	 * Auslesen des Namens
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzen des Namens
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Auslesen des Werts
	 * 
	 * @return wert
	 */
	public String getWert() {
		return wert;
	}

	/**
	 * Setzen des Werts
	 * 
	 * @param wert
	 */
	public void setWert(String wert) {
		this.wert = wert;
	}

}
