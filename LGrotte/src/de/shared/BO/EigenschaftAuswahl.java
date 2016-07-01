package de.shared.BO;

/**
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class EigenschaftAuswahl extends Eigenschaft {

	public EigenschaftAuswahl(int id) {
		super.setId(id);
	}

	private static final long serialVersionUID = 1L;

	private String[] values;

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

}
