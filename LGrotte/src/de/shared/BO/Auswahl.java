package de.shared.BO;

import java.util.Vector;

public class Auswahl extends BusinessObject{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int eigenschaft_id;
	private String value;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getEigenschaft_id() {
		return eigenschaft_id;
	}
	public void setEigenschaft_id(int eigenschaft_id) {
		this.eigenschaft_id = eigenschaft_id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
	
	
	
}
