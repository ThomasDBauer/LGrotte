package de.shared.BO;

public class Info extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String value;
	
	private int eigenschaft;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getEigenschaft() {
		return eigenschaft;
	}

	public void setEigenschaft(int eigenschaft) {
		this.eigenschaft = eigenschaft;
	}
	
	
	
}
