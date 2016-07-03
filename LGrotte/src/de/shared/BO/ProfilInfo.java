package de.shared.BO;

/**
 *  
 * Die Klasse ProfilInfo erweitert die Klasse BusinessObject.
 * Sie enthaelt Attribute, die Fremdschluessel auf das dazugehoerige Profil bilden
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class ProfilInfo extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der Attribute
	 */
	
	// Foreign Key auf das zugeh�rige Profil
	private String profilEmail;

	// Foreign Key auf die Info. Siehe @class Info
	private int infoID;

	/**
	 * Auslesen der ProfilEmail
	 * 
	 * @return profilEmail
	 */
	public String getProfilEmail() {
		return profilEmail;
	}

	/**
	 * Setzen der ProfilEmail
	 * 
	 * @param profilEmail
	 */
	public void setProfilEmail(String profilEmail) {
		this.profilEmail = profilEmail;
	}

	/**
	 * Auslesen der InfoID
	 * 
	 * @return infoID
	 */
	public int getInfoID() {
		return infoID;
	}

	/**
	 * Setzen der InfoID
	 * 
	 * @param infoID
	 */
	public void setInfoID(int infoID) {
		this.infoID = infoID;
	}

}
