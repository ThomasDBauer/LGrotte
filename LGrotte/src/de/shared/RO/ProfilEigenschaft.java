package de.shared.RO;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;

/**
 * Kombiniert als Report-Object die @class ProfilInfo. Traegt also
 * eine Kombination aus @class Eigenschaft und @class Info,
 * zugehoerig zu einem Profil. Folgt dem Muster der Superklasse:
 * Eigenschaft : Info
 * 
 * @author Thomas Bauer und Sedat Akar
 * 
 * @version 1.0
 */

public class ProfilEigenschaft extends ProfilInformation {

	private static final long serialVersionUID = 1L;

	/**
	 * Deklaration der Attribute
	 */
	private Eigenschaft eigenschaft;
	private Info info;

	/**
	 * Auslesen der Eigenschaft
	 * 
	 * @return eigenschaft
	 */
	public Eigenschaft getEigenschaft() {
		return eigenschaft;
	}

	/**
	 * Setzen der Eigenschaft
	 * 
	 * @param eigenschaft
	 */
	public void setEigenschaft(Eigenschaft eigenschaft) {
		this.eigenschaft = eigenschaft;
	}

	/**
	 * Auslesen der Info
	 * 
	 * @return info
	 */
	public Info getInfo() {
		return this.info;
	}
	
	/**
	 * Setzen der Info
	 * 
	 * @param info
	 */
	public void setInfo(Info info) {
		this.info = info;
	}


	/**
	 * Ueberschreiben der Super-Klasse
	 */
	public String getName() {
		return eigenschaft.getErlaeuterung();
	}

	/**
	 * Setzen des Eigenschaftsname
	 */
	public void setName(String name) {
		this.eigenschaft.setErlaeuterung(name);
	}

	/**
	 * Wert aufrufen
	 * 
	 * @return Inhalt
	 */
	public String getWert() {
		return info.getValue();
	}

	/**
	 * Wert setzen
	 */
	public void setWert(String wert) {
		this.info.setValue(wert);
	}

}
