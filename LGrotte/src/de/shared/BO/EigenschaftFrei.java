package de.shared.BO;

public class EigenschaftFrei extends Eigenschaft{
	
	public EigenschaftFrei(int id) {
			super.setId(id);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
