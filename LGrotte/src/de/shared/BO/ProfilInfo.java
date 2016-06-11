package de.shared.BO;

public class ProfilInfo extends BusinessObject{

	private static final long serialVersionUID = 1L;

	//Foreign Key auf das zugehörige Profil
	private String profilEmail;
	
	//Foreign Key auf die Info. Siehe @class Info
	private int infoID;
	
	
	
	//Get und Set Methoden
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
