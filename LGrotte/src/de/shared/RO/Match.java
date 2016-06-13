package de.shared.RO;

import java.io.Serializable;

public class Match implements Serializable{

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
	
	public Match(){
		
	}
	
	public Match(int m){
		matchResult = m;
	}

	public int getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(int matchResult) {
		this.matchResult = matchResult;
	}

}
