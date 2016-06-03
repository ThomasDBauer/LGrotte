package de.shared.RO;

import java.util.Vector;

public class ProfilReport extends Report {

	/**
	 * @author Thomas Bauer
	 * 
	 *         Jedes Report-Object rägt Informationen über ein einzelnes, detailliertes 
	 *         Profil, das zur Darstellung kommen soll. Es findet eine Unterscheidung
	 *         statt zwischen @class WeicheInformation und @class HarteInformation, die
	 *         ProfilAttribute und ProfilInfos repräsentieren.
	 *         
	 *         Von der Superklasse @class Report erbt die Klasse einen Titel.
	 *         
	 *         Die Subklasse @class FremdesProfilReport erweitert diese Klasse
	 *         um ein Matching-Ergebis.
	 *         Die Subklasse @class MeinProfilReport dient der objektorientierten
	 *         Vorgehensweise
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Eine unbestimmte Anzahl an @class WeicheInformation und @class HarteInformation.
	 * 
	 */
	private Vector<WeicheInformation> weicheInfos = new Vector<WeicheInformation>();
	private Vector<HarteInformation> harteInfos = new Vector<HarteInformation>();
	
	/*
	 * Hinzufügen einzelner weicher Infos
	 */
	public void addInfo(WeicheInformation info) {
		weicheInfos.add(info);
	}

	/*
	 * Entfernen von weichen Infos
	 */
	public void removeInfo(WeicheInformation info) {
		weicheInfos.removeElement(info);
	}

	/*
	 * get() Methode zum Auslesen aller weicher Infos
	 */
	public Vector<WeicheInformation> getWeicheInfos() {
		return weicheInfos;
	}
	
	/*
	 * Hinzufügen einzelner harter Infos
	 */
	public void addInfo(HarteInformation info) {
		harteInfos.add(info);
	}

	/*
	 * Entfernen von weichen Infos
	 */
	public void removeInfo(HarteInformation info) {
		harteInfos.removeElement(info);
	}

	/*
	 * get() Methode zum Auslesen aller weicher Infos
	 */
	public Vector<HarteInformation> getHarteInfos() {
		return harteInfos;
	}
}
