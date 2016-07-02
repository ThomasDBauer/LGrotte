package de.shared.RO;

import java.io.Serializable;

/**
 * @author Thomas Bauer
 * 
 *         Traegt Informationen ueber das Matching-Ergebnis, das in Reports
 *         dargestellt wird.
 */
public class Match implements Serializable {


	/*
	 * Das Ergebnis eines Matches wird repraesentiert durch eine Zahl. In den
	 * Servlets wird das Objekt beschrieben. In den Writer-Klassen, die aus den
	 * Report-Objects HTML-Code generieren, wird das Ergebnis ausgelesen.
	 */

	/*
	 * Deklaration des Attributs
	 */
	private int matchResult;

	/*
	 * Konstruktoren der Klasse
	 */
	public Match() {

	}

	public Match(int m) {
		matchResult = m;
	}

	/*
	 * Auslesen des Match Results
	 * 
	 * @return matchResult
	 */
	public int getMatchResult() {
		return matchResult;
	}

	/*
	 * Setzen des Match Results
	 * 
	 * @param matchResult
	 */
	public void setMatchResult(int matchResult) {
		this.matchResult = matchResult;
	}

}
