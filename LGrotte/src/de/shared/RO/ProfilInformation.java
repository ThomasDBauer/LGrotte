package de.shared.RO;

import java.io.Serializable;

public abstract class ProfilInformation implements Serializable {

	/**
	 * 
	 * @author Thomas Bauer
	 * 
	 *        Super-Klasse von @class ProfilAttribut und
	 * 		  @class ProfilEigenschaft
	 *        Tr�gt die Gemeinsamkeit, dass Informationen immer nach dem Schema
	 *        >>> Attribut : Information <<< aufgebaut sind.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Das Attribut
	 */
	private String name;

	/*
	 * Der Wert
	 */
	private String wert;

	/*
	 * Ein Null-Argument Konstruktor
	 */
	public ProfilInformation() {
	}

	/*
	 * Ein Konstruktor zum Setzen der beiden elementaren Werte
	 */
	public ProfilInformation(String name, String wert) {
		this.name = name;
		this.wert = wert;
	}

	/*
	 * Getter und Setter
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWert() {
		return wert;
	}

	public void setWert(String wert) {
		this.wert = wert;
	}

}
