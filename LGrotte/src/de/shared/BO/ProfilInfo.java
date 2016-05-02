package de.shared.BO;

public class ProfilInfo extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private int profilID;
	private int infoID;
	private int EigenschaftID;
	
	public int getProfilID() {
		return profilID;
	}
	public void setProfilID(int profilID) {
		this.profilID = profilID;
	}
	public int getInfoID() {
		return infoID;
	}
	public void setInfoID(int infoID) {
		this.infoID = infoID;
	}
	public int getEigenschaftID() {
		return EigenschaftID;
	}
	public void setEigenschaftID(int eigenschaftID) {
		EigenschaftID = eigenschaftID;
	}
	
	
	
	
}
