package de.shared.RO;

public class FremdesProfilReport extends ProfilReport{

	/**
	 * 
	 * @author Thomas Bauer
	 * 
	 * Erweitert @class ProfilReport um ein Matching Ergebnis, das zwischen
	 * dem eingeloggten Profil und anderen Profilen besteht.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * @class Match trägt eine Zahl, die das Ergebnis repräsentiert.
	 */
	private Match match;
	
	/*
	 * Zum Auslesen in den (HTML)Writern
	 */
	public Match getMatch() {
		return match;
	}
	
	/*
	 * Zum serverseitigen Beschreiben
	 */
	public void setMatch(Match match) {
		this.match = match;
	}


}
