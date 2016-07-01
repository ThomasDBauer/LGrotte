package de.shared.BO;

/**
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class SuchprofilInfo {

	// Das Name des Suchprofils
	private Suchprofil sp;

	// Die Information
	private Info info;

	// Das Profil
	private Profil profil;

	public Suchprofil getSp() {
		return sp;
	}

	public void setSp(Suchprofil sp) {
		this.sp = sp;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

}
