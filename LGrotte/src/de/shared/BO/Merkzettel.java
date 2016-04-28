package de.shared.BO;

public class Merkzettel extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Profil[]gemerkteProfile;

	public Profil[] getGemerkteProfile() {
		return gemerkteProfile;
	}

	public void setGemerkteProfile(Profil[] gemerkteProfile) {
		this.gemerkteProfile = gemerkteProfile;
	}
	
	/** wahrscheinlich unnötig: */
	public void addProfil(Profil p){
		//crazy code um Profil dem merkzettel
		//hinzuzufügen + service.addProfilZuMerkzettel
	}
	
	public void removeProfil(Profil p){
		/* code um Profil vom Merkzettel zu löschen
		 * + service.removeProfilbazinga?*/
	}
	
	
	
}
