package de.shared.BO;

import java.util.Date;

public class Suchprofil extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fname, lname, haarfarbe, religion;
	private int koerpergroesse;
	private Date geburtsdatum;
	private boolean raucher;
	private boolean geschlecht;
	
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public int getKoerpergroesse() {
		return koerpergroesse;
	}
	public void setKoerpergroesse(int koerpergroesse) {
		this.koerpergroesse = koerpergroesse;
	}
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public boolean isRaucher() {
		return raucher;
	}
	public void setRaucher(boolean raucher) {
		this.raucher = raucher;
	}
	public void setGeschlecht(boolean geschlecht){
		this.geschlecht = geschlecht;
	}
	public boolean isGeschlecht(){
		return geschlecht;
	}
	
	
}
