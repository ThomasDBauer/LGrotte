package de.shared.BO;
/**
 * 
 * @author Thomas Bauer
 * 
 * @version 1.0
 * 
 * Ein Besuch besteht aus einem besuchendem Profil und einem 
 * besuchten Profil.
 * 
 */

public class Besuch {

	private Profil besuchtesProfil;

	private Profil besuchendesProfil;

	public Profil getBesuchtesProfil() {
		return besuchtesProfil;
	}

	public void setBesuchtesProfil(Profil besuchtesProfil) {
		this.besuchtesProfil = besuchtesProfil;
	}

	public Profil getBesuchendesProfil() {
		return besuchendesProfil;
	}

	public void setBesuchendesProfil(Profil besuchendesProfil) {
		this.besuchendesProfil = besuchendesProfil;
	}

}
