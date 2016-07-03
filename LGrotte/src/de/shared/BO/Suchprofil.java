package de.shared.BO;

import java.util.Vector;

import de.shared.RO.ProfilEigenschaft;

/**
 * Die Klasse Suchprofil erweitert die Klasse BusinessObject. Sie enthaelt
 * Attribute, die im Suchprofil dargestellt werden und einen Vector der die
 * SuchprofilInformationen abspeichert.
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class Suchprofil extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der Suchprofil-Attribute
	 */
	private String suchprofilname, profil, haarfarbe, religion, raucher, geschlecht;
	private int minGroesse, maxGroesse, minAlter, maxAlter;

	/**
	 * SuchprofilInformationen werden in einem Vector abgelegt
	 */
	private Vector<ProfilEigenschaft> profileigenschaften = new Vector<ProfilEigenschaft>();

	/**
	 * Auslesen des Suchprofil Namen
	 * 
	 * @return suchprofilname
	 */
	public String getSuchprofilname() {
		return suchprofilname;
	}

	/**
	 * Setzen des Suchprofil Namens
	 * 
	 * @param suchprofilname
	 */
	public void setSuchprofilname(String suchprofilname) {
		this.suchprofilname = suchprofilname;
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
	 * Auslesen ob Raucher
	 * 
	 * @return religion
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
	 * Auslesen des Geschlechts
	 * 
	 * @return geschelcht
	 */
	public String getGeschlecht() {
		return geschlecht;
	}

	/**
	 * Setzen des Geschlechts
	 * 
	 * @param geschlecht
	 */
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	/**
	 * Auslesen des Mindestalters
	 * 
	 * @return minAlter
	 */
	public int getMinAlter() {
		return minAlter;
	}

	/**
	 * Setzen des Mindestalters
	 * 
	 * @param minalter
	 */
	public void setMinAlter(int minAlter) {
		this.minAlter = minAlter;
	}

	/**
	 * Auslesen des Maximalalters
	 * 
	 * @return maxAlter
	 */
	public int getMaxAlter() {
		return maxAlter;
	}

	/**
	 * Setzen des Maximalalters
	 * 
	 * @param maxAlter
	 */
	public void setMaxAlter(int maxAlter) {
		this.maxAlter = maxAlter;
	}

	/**
	 * Auslesen des Profils
	 * 
	 * @return profil
	 */
	public String getProfil() {
		return profil;
	}

	/**
	 * Setzen des Profils
	 * 
	 * @param profil
	 */
	public void setProfil(String profil) {
		this.profil = profil;
	}

	/**
	 * Auslesen der Mindestgroesse
	 * 
	 * @return minGroesse
	 */
	public int getMinGroesse() {
		return minGroesse;
	}

	/**
	 * Setzen der Mindestgroesse
	 * 
	 * @param minGroesse
	 */
	public void setMinGroesse(int minGroesse) {
		this.minGroesse = minGroesse;
	}

	/**
	 * Auslesen der Maximalgroesse
	 * 
	 * @return maxGroesse
	 */
	public int getMaxGroesse() {
		return maxGroesse;
	}

	/**
	 * Setzen der Maximalgroesse
	 * 
	 * @param maxGroesse
	 */
	public void setMaxGroesse(int maxGroesse) {
		this.maxGroesse = maxGroesse;
	}

	/**
	 * Auslesen der Profileigenschaften
	 * 
	 * @return profilEigenschaften
	 */
	public Vector<ProfilEigenschaft> getProfileigenschaften() {
		return profileigenschaften;
	}

	/**
	 * Setzen der Profileigenschaften
	 * 
	 * @param profileigenschaften
	 */
	public void setProfileigenschaften(Vector<ProfilEigenschaft> profileigenschaften) {
		this.profileigenschaften = profileigenschaften;
	}

}
