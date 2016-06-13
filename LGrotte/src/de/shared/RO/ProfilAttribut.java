package de.shared.RO;

import de.shared.BO.Info;

public class ProfilAttribut extends ProfilInformation{

	/**
	 * @author Thomas Bauer, Sedat Akar
	 * 
	 *         Zur Darstellung von Profilattributen, wie z.B.
	 *         Alter, Raucher, Religion, Körpergröße
	 *         Folgt dem Muster der Superklasse:
	 *         Eigenschaft : Info
	 *         
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public String getWert() {
		return super.getWert();
	}

	public void setWert(String wert){
		super.setWert(wert);
	}
	
	
}
