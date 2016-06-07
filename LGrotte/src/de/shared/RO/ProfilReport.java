package de.shared.RO;

import java.io.Serializable;
import java.util.Vector;

public class ProfilReport implements Serializable{

	/**
	 * @author Thomas Bauer, Sedat Akar
	 * 
	 *         Jedes Report-Object traegt Informationen ueber ein einzelnes, detailliertes 
	 *         Profil, das zur Darstellung kommen soll. Es findet eine Unterscheidung
	 *         statt zwischen @class ProfilEigenschaft und @class ProfilAttribut, die
	 *         ProfilAttribute und ProfilInfos repräsentieren.
	 *         
	 *         
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Das Matchobjekt traegt das Aehnlichkeitsmass  
	 */
	private Match match;
	
	
	/*
	 * Jedes Profil hat eine Ueberschrift
	 */
	private String header;
	
	/*
	 * Eine unbestimmte Anzahl an @class ProfilEigenschaften und @class ProfilAttributen.
	 * 
	 */
	private Vector<ProfilEigenschaft> eigenschaften = new Vector<ProfilEigenschaft>();
	private Vector<ProfilAttribut> attribute = new Vector<ProfilAttribut>();
	
	/*
	 * Hinzufügen einzelner Eigenschaften
	 */
	public void addEigenschaft(ProfilEigenschaft pe) {
		eigenschaften.add(pe);
	}

	/*
	 * Entfernen von Eigenschaften
	 */
	public void removeEigenschaft(ProfilEigenschaft pe) {
		eigenschaften.removeElement(pe);
	}

	/*
	 * get() Methode zum Auslesen aller Eigenschaften
	 */
	public Vector<ProfilEigenschaft> getEigenschaften() {
		return eigenschaften;
	}
	
	/*
	 * Hinzufügen einzelner Attribute
	 */
	public void addAttribut(ProfilAttribut pa) {
		attribute.add(pa);
	}

	/*
	 * Entfernen von einzelnen Attributen
	 */
	public void removeAttribut(ProfilAttribut pa) {
		attribute.removeElement(pa);
	}

	/*
	 * get() Methode zum Auslesen aller Attribute
	 */
	public Vector<ProfilAttribut> getAttribute() {
		return attribute;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}
