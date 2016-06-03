package de.shared.RO;


import java.io.Serializable;

public class Column implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String value;
	
	public Column(){
		
	}
	
	public Column(String s){
		setValue(s);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
	

}
