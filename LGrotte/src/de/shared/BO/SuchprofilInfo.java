package de.shared.BO;

/**
 * Die Klasse SuchprofilInfo kombiniert alle wichtigen Informationen zu
 * SuchprofilInformationen in einem Objekt. 
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class SuchprofilInfo {

	/**
	 * Deklaration der Attribute
	 */
	private Suchprofil sp;
	private Info info;
	private Profil profil;

	
	/**
	 * Auslesen des Suchprofils
	 * 
	 * @return sp
	 */
	public Suchprofil getSp() {
		return sp;
	}

	/**
	 * Setzen des Suchprofils
	 * 
	 * @param sp
	 */
	public void setSp(Suchprofil sp) {
		this.sp = sp;
	}

	/**
	 * Auslesen der Info
	 * 
	 * @return info
	 */
	public Info getInfo() {
		return info;
	}

	/**
	 * Setzen der Info
	 * 
	 * @param info
	 */
	public void setInfo(Info info) {
		this.info = info;
	}

	/**
	 * Auslesen des Profils
	 * 
	 * @return profil
	 */
	public Profil getProfil() {
		return profil;
	}

	/**
	 * Setzen des Profils
	 * 
	 * @param profil
	 */
	public void setProfil(Profil profil) {
		this.profil = profil;
	}

}
