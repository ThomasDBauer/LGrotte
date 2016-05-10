package de.shared.RO;

import java.io.Serializable;
import java.util.Vector;

public class Row implements Serializable {

	private static final long serialVersionUID = 1L;

	// Speicherplatz für die Spalten der Zeile in einem Vector
	private Vector<Column> columns = new Vector<Column>();

	// Auslesen aller Spalten
	public Vector<Column> getColumns() {
		return columns;
	}

}
