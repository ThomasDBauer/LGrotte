package de.shared.RO;

import java.io.Serializable;
import java.util.Vector;


public class Row implements Serializable {

	private static final long serialVersionUID = 1L;

	// Speicherplatz für die Spalten der Zeile in einem Vector
	private Vector<Column> columns = new Vector<Column>();

	// Hinzufügen einer neuen Spalte c
	public void addColumn(Column c) {
		this.columns.addElement(c);
	}

	// entfernen der Spalte c
	public void removeColumn(Column c) {
		this.columns.removeElement(c);
	}

	// Auslesen aller Spalten
	public Vector<Column> getColumns() {
		return columns;
	}

}
