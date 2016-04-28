package de.shared.BO;

public class EigenschaftAuswahl extends Eigenschaft{
	
	public EigenschaftAuswahl(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] values;

	
	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
	
	
	
}
