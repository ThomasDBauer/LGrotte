package de.shared.BO;

public class Aehnlichkeitsmass extends BusinessObject{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Profil referenz;
	private Profil vergleich;
	private byte mass;
	
	
	public Profil getReferenz() {
		return referenz;
	}
	public void setReferenz(Profil referenz) {
		this.referenz = referenz;
	}
	public Profil getVergleich() {
		return vergleich;
	}
	public void setVergleich(Profil vergleich) {
		this.vergleich = vergleich;
	}
	public byte getMass() {
		return mass;
	}
	public void setMass(byte mass) {
		this.mass = mass;
	}
	
	
	
}
