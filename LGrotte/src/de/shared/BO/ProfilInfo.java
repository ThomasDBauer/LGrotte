package de.shared.BO;

public class ProfilInfo extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private String profilEmail;
	private int infoID;
	
	
	public String getProfilEmail() {
		return profilEmail;
	}
	public void setProfilEmail(String profilEmail) {
		this.profilEmail = profilEmail;
	}
	public int getInfoID() {
		return infoID;
	}
	public void setInfoID(int infoID) {
		this.infoID = infoID;
	}
	
}
