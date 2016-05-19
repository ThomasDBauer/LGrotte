package de.shared.BO;

import java.util.Date;

public class Profil extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String fname, lname, haarfarbe, religion, geschlecht, raucher, email;
	private int koerpergroesse;
	private Date geburtsdatum;
	private Info[] profilInformation;
	private Merkzettel merkzettel;
	private Kontaktsperre[] sperrliste;
	private Aehnlichkeitsmass[]aehnlichkeitsmasse;
	
	private boolean loggedIn;
	private String loginUrl, logoutUrl;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	
	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}
	public String getGeschlecht(){
		return this.geschlecht;
	}
	
	public Merkzettel getMerkzettel() {
		return merkzettel;
	}
	public void setMerkzettel(Merkzettel merkzettel) {
		this.merkzettel = merkzettel;
	}
	public Kontaktsperre[] getSperrliste() {
		return sperrliste;
	}
	public void setSperrliste(Kontaktsperre[] sperrliste) {
		this.sperrliste = sperrliste;
	}
	public Aehnlichkeitsmass[] getAehnlichkeitsmasse() {
		return aehnlichkeitsmasse;
	}
	public void setAehnlichkeitsmasse(Aehnlichkeitsmass[] aehnlichkeitsmasse) {
		this.aehnlichkeitsmasse = aehnlichkeitsmasse;
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
	public String getRaucher() {
		return raucher;
	}
	public void setRaucher(String raucher) {
		this.raucher = raucher;
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
	public Info[] getProfilInformation() {
		return profilInformation;
	}
	public void setProfilInformation(Info[] profilInformation) {
		this.profilInformation = profilInformation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
