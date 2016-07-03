package de.shared.BO;

import java.util.Date;

/**
 * Die Klasse Profil erweitert die Klasse BusinessObject. Die Klasse
 * Profil enthaelt Attribute, die die Eigenschaften einer Person
 * darstellen.
 *          
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class Profil extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der ProfilAttribute
	 */
	private String fname, lname, haarfarbe, religion, geschlecht, raucher, email;
	private int koerpergroesse;
	private Date geburtsdatum;

	/**
	 * Deklaration der Login-Informationen
	 */
	private boolean loggedIn;
	private String loginUrl, logoutUrl;

	/**
	 * Auslesen des LogIn
	 * 
	 * @return loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Setzen des LogIn
	 * 
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Auslesen der LogInUrl
	 * 
	 * @return loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * Setzen der LoginUrl
	 * 
	 * @param loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * Auslesen der LogoutUrl
	 * 
	 * @return logoutUrl
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * Setzen der LogotUrl
	 * 
	 * @param logotUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * Auslesen des Geschlechts
	 * 
	 * @return geschlecht
	 */
	public String getGeschlecht() {
		return this.geschlecht;
	}

	/**
	 * Setzen der LogotUrl
	 * 
	 * @param logotUrl
	 */
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	/**
	 * Auslesen der Haarfarbe
	 * 
	 * @return haarfarbe
	 */
	public String getHaarfarbe() {
		return haarfarbe;
	}

	/**
	 * Setzen der Haarfarbe
	 * 
	 * @param haarfarbe
	 */
	public void setHaarfarbe(String haarfarbe) {
		this.haarfarbe = haarfarbe;
	}

	/**
	 * Auslesen der Religion
	 * 
	 * @return religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * Setzen der Religion
	 * 
	 * @param religion
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * Auslesen der Koerpergroesse
	 * 
	 * @return koerpergroesse
	 */
	public int getKoerpergroesse() {
		return koerpergroesse;
	}

	/**
	 * Setzen der Koerpergroesse
	 * 
	 * @param koerpergroesse
	 */
	public void setKoerpergroesse(int koerpergroesse) {
		this.koerpergroesse = koerpergroesse;
	}

	/**
	 * Auslesen des Geburtsdatums
	 * 
	 * @return geburtsdatum
	 */
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * Setzen des Geburtsdatums
	 * 
	 * @param geburtsdatum
	 */
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	/**
	 * Auslesen ob Raucher
	 * 
	 * @return raucher
	 */
	public String getRaucher() {
		return raucher;
	}

	/**
	 * Setzen ob Raucher
	 * 
	 * @param raucher
	 */
	public void setRaucher(String raucher) {
		this.raucher = raucher;
	}

	/**
	 * Auslesen des Vornamens
	 * 
	 * @return fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Setzen des Vornamens
	 * 
	 * @param fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * Auslesen des Nachnames
	 * 
	 * @return lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Setzen des Vornamens
	 * 
	 * @param fname
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * Auslesen der Email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzen der Email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
