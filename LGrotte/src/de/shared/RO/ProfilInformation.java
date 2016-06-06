package de.shared.RO;

import java.io.Serializable;

public class ProfilInformation implements Serializable {

	/**
	 * 
	 * @author Thomas Bauer
	 * 
	 *        Super-Klasse von @class WeicheInformation und
	 * 		  @class HarteInformation
	 *        Trägt die Gemeinsamkeit, dass Informationen immer nach dem Schema
	 *        >>> Attribut : Information <<< aufgebaut sind.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Das Attribut
	 */
	private String attribut;

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
	public ProfilInformation(String attribut, String wert) {
		this.attribut = attribut;
		this.wert = wert;
	}

	/*
	 * Getter und Setter
	 */
	public String getAttribut() {
		return attribut;
	}

	public void setAttribut(String attribut) {
		this.attribut = attribut;
	}

	public String getWert() {
		return wert;
	}

	public void setWert(String wert) {
		this.wert = wert;
	}

}
