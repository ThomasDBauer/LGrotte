package de.shared.RO;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value = "";

	public Column() {
	}

	//erzwingt einen Spalteneintrag
	public Column(String s) {
		this.value = s;
	}

	//Auslesen des Spaltenwerts
	public String getValue() {
		return value;
	}

	//Aktuellen Spaltenwert überschreiben
	public void setValue(String value) {
		this.value = value;
	}

	//Column-Objekt in String umwandeln
	public String toString() {
		return this.value;
	}
}
