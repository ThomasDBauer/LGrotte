package de.shared.RO;

public class Match {

	/**
	 * @author Thomas Bauer
	 * 
	 *         Trägt Informationen über das Matching-Ergebnis, das in Reports
	 *         dargestellt wird.
	 */

	/*
	 * Das Ergebnis eines Matches wird repräsentiert durch eine Zahl.
	 * In den Servlets wird das Objekt beschrieben. In den Writer-Klassen, 
	 * die aus den Report-Objects HTML-Code generieren, wird das Ergebnis
	 * ausgelesen.
	 */
	private int matchResult;

	public int getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(int matchResult) {
		this.matchResult = matchResult;
	}

}
