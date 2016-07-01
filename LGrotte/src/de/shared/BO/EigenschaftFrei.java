package de.shared.BO;

/**
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class EigenschaftFrei extends Eigenschaft {

	public EigenschaftFrei(int id) {
		super.setId(id);
	}

	private static final long serialVersionUID = 1L;

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
