package de.shared.BO;

import java.util.Date;

public class Suchprofil extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String suchprofilname, profil, haarfarbe, religion, raucher, geschlecht;
	private int koerpergroesse, minAlter, maxAlter;
	
	
	public String getSuchprofilname() {
		return suchprofilname;
	}
	public void setSuchprofilname(String suchprofilname) {
		this.suchprofilname = suchprofilname;
	}
	public String getHaarfarbe() {
		return haarfarbe;
	}
	public void setHaarfarbe(String haarfarbe) {
		this.haarfarbe = haarfarbe;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getRaucher() {
		return raucher;
	}
	public void setRaucher(String raucher) {
		this.raucher = raucher;
	}
	public String getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}
	public int getKoerpergroesse() {
		return koerpergroesse;
	}
	public void setKoerpergroesse(int koerpergroesse) {
		this.koerpergroesse = koerpergroesse;
	}
	public int getMinAlter() {
		return minAlter;
	}
	public void setMinAlter(int minAlter) {
		this.minAlter = minAlter;
	}
	public int getMaxAlter() {
		return maxAlter;
	}
	public void setMaxAlter(int maxAlter) {
		this.maxAlter = maxAlter;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	
	
	
	
	
	
}
