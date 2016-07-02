package de.shared.BO;
import java.util.Date;

/**
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class Profil extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	/*
	 * ProfilAttribute
	 */
	private String fname, lname, haarfarbe, religion, geschlecht, raucher,
			email;
	private int koerpergroesse;
	private Date geburtsdatum;

	/*
	 * Login-Informationen
	 */
	private boolean loggedIn;
	private String loginUrl, logoutUrl;
	
	/*
	 * Get und Set Methoden
	 */
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

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getGeschlecht() {
		return this.geschlecht;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
