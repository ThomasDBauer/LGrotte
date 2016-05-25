package de.shared.BO;

public class Merkzettel extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Profil[]gemerkteProfile;
	private Profil[] merkendeProfile;

	public Profil[] getMerkendeProfile() {
		return merkendeProfile;
	}

	public void setMerkendeProfile(Profil[] merkendeProfile) {
		this.merkendeProfile = merkendeProfile;
	}

	public Profil[] getGemerkteProfile() {
		return gemerkteProfile;
	}

	public void setGemerkteProfile(Profil[] gemerkteProfile) {
		this.gemerkteProfile = gemerkteProfile;
	}
	
	/** wahrscheinlich unn�tig: */
	public void addProfil(Profil p){
		//crazy code um Profil dem merkzettel
		//hinzuzuf�gen + service.addProfilZuMerkzettel
	}
	
	public void removeProfil(Profil p){
		/* code um Profil vom Merkzettel zu l�schen
		 * + service.removeProfilbazinga?*/
	}
	
	
	
}
