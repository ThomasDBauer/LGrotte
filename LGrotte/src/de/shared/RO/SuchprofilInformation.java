package de.shared.RO;

import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;

public class SuchprofilInformation {
	
	//Das Name des Suchprofils
	private String sp;
	
	//Die Information
	private int info;
	
	//Der Primary Key des zugehörigen Profils
	private String email;

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public String getProfil() {
		return email;
	}

	public void setProfil(String email) {
		this.email = email;
	}
	
	
	
	
}
