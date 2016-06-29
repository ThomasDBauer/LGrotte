package de.shared.RO;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;

public class ProfilEigenschaft extends ProfilInformation {

	/**
	 * @author Thomas Bauer, Sedat Akar
	 * 
	 *         Kombiniert als Report-Object die @class ProfilInfo. Tr�gt also
	 *         eine Kombination aus @class Eigenschaft und @class Info,
	 *         zugeh�rig zu einem Profil. Folgt dem Muster der Superklasse:
	 *         Eigenschaft : Info
	 */
	private static final long serialVersionUID = 1L;

	private Eigenschaft eigenschaft;
	private Info info;

	public Eigenschaft getEigenschaft() {
		return eigenschaft;
	}

	public void setEigenschaft(Eigenschaft eigenschaft) {
		this.eigenschaft = eigenschaft;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Info getInfo() {
		return this.info;
	}

	// �berschreiben der super-Klasse
	public String getName() {
		return eigenschaft.getErlaeuterung();
	}

	public void setName(String name) {
		this.eigenschaft.setErlaeuterung(name);
	}

	public String getWert() {
		// null check
		return info.getValue();
	}

	public void setWert(String wert) {
		this.info.setValue(wert);
	}

}
