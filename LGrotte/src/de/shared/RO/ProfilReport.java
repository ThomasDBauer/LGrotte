package de.shared.RO;


import java.util.Vector;

import de.shared.RO.Row;


public class ProfilReport extends Report{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Vector<Row>rows = new Vector<Row>();
	
	public void removeRow(Row row){
		rows.removeElement(row);
	}
	
	public Vector<Row> getRows(){
		return rows;
	}
	
	public void addRow(Row r){
		rows.add(r);
	}
	
	
	
}
