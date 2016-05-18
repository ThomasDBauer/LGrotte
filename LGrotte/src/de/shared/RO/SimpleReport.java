package de.shared.RO;

import java.util.Vector;

public abstract class SimpleReport extends Report {

  
  private static final long serialVersionUID = 1L;

  
  private Vector<Row> table = new Vector<Row>();

  //Zeile hinzufügen
  public void addRow(Row r) {
    this.table.addElement(r);
  }

  //Zeile entfernen
  public void removeRow(Row r) {
    this.table.removeElement(r);
  }

  //Auslesen saemtlicher Daten
  public Vector<Row> getRows() {
    return this.table;
  }
}