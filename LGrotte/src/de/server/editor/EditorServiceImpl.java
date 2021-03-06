package de.server.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.AuswahlMapper;
import de.server.db.BesucheMapper;
import de.server.db.EigenschaftMapper;
import de.server.db.InfoMapper;
import de.server.db.KontaktsperreMapper;
import de.server.db.MerkzettelMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.SuchprofilInfoMapper;
import de.server.db.SuchprofilMapper;
import de.shared.EditorService;
import de.shared.BO.Auswahl;
import de.shared.BO.Besuch;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Kontaktsperre;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;
import de.shared.BO.SuchprofilInfo;
import de.shared.RO.ProfilEigenschaft;

/**
 * The server-side implementation of the RPC service.
 * 
 * @author Thomas Bauer und Enrico Popaj und Nicolai Ehrmanntraut
 *
 * @version 1.0
 */
@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements EditorService {

	// Instanzen der Datenbank-Mappern
	private ProfilMapper pMapper = ProfilMapper.profilMapper();
	private EigenschaftMapper eMapper = EigenschaftMapper.eigenschaftMapper();
	private SuchprofilMapper spMapper = SuchprofilMapper.suchprofilMapper();
	private KontaktsperreMapper ksMapper = 
			KontaktsperreMapper.kontaktsperreMapper();
	private MerkzettelMapper merkMapper = MerkzettelMapper.merkzettelMapper();
	private SuchprofilInfoMapper spiMapper = 
			SuchprofilInfoMapper.suchprofilInfoMapper();
	private AuswahlMapper auswahlMapper = AuswahlMapper.auswahlMapper();
	private InfoMapper infoMapper = InfoMapper.infoMapper();
	private ProfilinfoMapper piMapper = ProfilinfoMapper.profilinfoMapper();
	private BesucheMapper besuchMapper = BesucheMapper.besucheMapper();

	/**
	 * Das eingeloggte Profil
	 */
	private Profil user;

	/***************************************************************
	 * Profil
	 ***************************************************************/

	/**
	 * @param p
	 *            Profil
	 */
	public void setUser(Profil p) {
		user = p;
	}

	/**
	 * Ein Profil in das System eintragen.
	 * 
	 * @param Profilattribute
	 */
	public void insertProfil(String email, String fname, String lname, int koerpergroesse, String geschlecht,
			String religion, String haarfarbe, String raucher, Date geburtsdatum) throws Exception {
		Profil p = new Profil();
		p.setFname(fname);
		p.setLname(lname);
		p.setKoerpergroesse(koerpergroesse);
		p.setGeschlecht(geschlecht);
		p.setReligion(religion);
		p.setHaarfarbe(haarfarbe);
		p.setGeburtsdatum(geburtsdatum);
		p.setRaucher(raucher);
		p.setEmail(email);
		pMapper.insertProfil(p);
	}

	/**
	 * Ein Profil bearbeiten / updaten
	 * 
	 * @param Alle
	 */

	public void updateProfil(String fname, String lname, int koerpergroesse, String geschlecht, String religion,
			String haarfarbe, String raucher, Date geburtsdatum, String email) throws Exception {
		Profil p = new Profil();
		System.out.println("Der neue coole Name ist : " + fname);
		p.setFname(fname);
		p.setLname(lname);
		p.setKoerpergroesse(koerpergroesse);
		p.setGeschlecht(geschlecht);
		p.setReligion(religion);
		p.setHaarfarbe(haarfarbe);
		p.setGeburtsdatum(geburtsdatum);
		p.setRaucher(raucher);
		p.setEmail(user.getEmail());
		pMapper.updateProfil(p);
	}

	/**
	 * User Profil löschen
	 */
	public void deleteProfil() throws Exception {
		pMapper.deleteProfil(user.getEmail());
	}

	/**
	 * Profil auslesen
	 */
	public Profil getProfil() throws Exception {
		return pMapper.getProfilByEmail(user.getEmail());
	}

	/**
	 * Profil auslesen #2 mit Fremd-Email
	 * 
	 * @param Email
	 * @return Profil-Objekt
	 */
	public Profil getProfil(String email) throws Exception {
		return pMapper.getProfilByEmail(email);
	}

	/**
	 * Alle Profile, die nicht auf Merkzetteln oder Kontaktsperren des Users
	 * stehen oder die gesperrt wurden!
	 * 
	 * @return Vector<Profil>
	 */
	public Vector<Profil> getProfilesForEditor() throws Exception {

		// Erstmal alle Profile auslesen:
		Vector<Profil> profile = pMapper.getAll();
		// Au0erdem alle Merkzettel und Kontaktsperren des Users:
		Vector<Merkzettel> merkzettel = merkMapper.getMerkzettelByOwner(user.getEmail());
		Vector<Kontaktsperre> sperren = ksMapper.getKontaktsperreByOwner(user.getEmail());
		Vector<Kontaktsperre> gesperrtVon = ksMapper.getKontaktsperren(user.getEmail());

		Vector<Profil> results = new Vector<Profil>();
		for (int i = 0; i < profile.size(); i++) {
			boolean ok = true;
			// User aussortieren
			if (profile.elementAt(i).getEmail().equals(user.getEmail())) {
				ok = false;
				continue;
			}

			// Merkzettel aussortieren
			for (int o = 0; o < merkzettel.size(); o++) {
				if (profile.elementAt(i).getEmail().equals(merkzettel.elementAt(o).getGemerktesProfil())) {
					ok = false;
					break;
				}
				;
			}
			// Kontaktsperren aussortieren
			for (int o = 0; o < sperren.size(); o++) {
				if (profile.elementAt(i).getEmail().equals(sperren.elementAt(o).getGesperrtesProfil())) {
					ok = false;
					break;
				}
				;
			}
			// User aussortieren, die den User gesperrt haben
			for (int o = 0; o < gesperrtVon.size(); o++) {
				if (profile.elementAt(i).getEmail().equals(gesperrtVon.elementAt(o).getSperrendesProfil())) {
					ok = false;
					break;
				}
				;
			}
			if (ok)
				results.add(profile.elementAt(i));
		}
		return results;
	}

	/***************************************************************
	 * Suchprofil
	 ***************************************************************/

	/**
	 * Suchprofil erstellen
	 * 
	 * @param Alle
	 */
	public void insertSuchprofil(String suchprofilname, String geschlecht, String raucher, String religion,
			int minAlter, int maxAlter, int minGroesse, int maxGroesse, String haarfarbe) throws Exception {
		Suchprofil sp = new Suchprofil();
		sp.setGeschlecht(geschlecht);
		sp.setRaucher(raucher);
		sp.setReligion(religion);
		sp.setMinAlter(minAlter);
		sp.setMaxAlter(maxAlter);
		sp.setMinGroesse(minGroesse);
		sp.setMaxGroesse(maxGroesse);
		sp.setHaarfarbe(haarfarbe);
		sp.setSuchprofilname(suchprofilname);
		sp.setProfil(user.getEmail());
		spMapper.insertSuchprofil(sp);
	}

	/**
	 * Suchprofil bearbeiten
	 * 
	 * @param Alle
	 */
	public void updateSuchprofil(String geschlecht, int minAlter, int maxAlter, String religion, String haarfarbe,
			String raucher, int minGroesse, int maxGroesse, String suchprofilname) throws Exception {
		Suchprofil sp = new Suchprofil();
		sp.setGeschlecht(geschlecht);
		sp.setMinAlter(minAlter);
		sp.setMaxAlter(maxAlter);
		sp.setReligion(religion);
		sp.setHaarfarbe(haarfarbe);
		sp.setRaucher(raucher);
		sp.setMinGroesse(minGroesse);
		sp.setMaxGroesse(maxGroesse);
		sp.setSuchprofilname(suchprofilname);
		sp.setProfil(user.getEmail());
		spMapper.updateSuchprofil(sp);
	}

	/**
	 * Suchprofil löschen
	 * 
	 * @param Name
	 */
	public void deleteSuchprofil(String suchprofilname) throws Exception {
		Suchprofil deletesp = new Suchprofil();
		deletesp.setSuchprofilname(suchprofilname);
		spMapper.deleteSuchprofil(deletesp);
	}

	/**
	 * Ruft alle Suchprofile eines Profils auf
	 * 
	 * @return Vector<Suchprofil> für das eingeloggte Profil;
	 */
	public Vector<Suchprofil> getSuchprofile() throws Exception {
		return spMapper.getSuchprofileByEmail(user.getEmail());
	}

	/**
	 * Suchprofil Anzeigen nach Suchprofilname
	 * 
	 * @param Name
	 * @return Suchprofil
	 */
	public Suchprofil getSuchprofileByName(String suchprofilname) throws Exception {
		return spMapper.getSuchprofiByName(suchprofilname);
	}

	/*********************************************************************
	 * Eigenschaften und Auwahl
	 ********************************************************************/

	/**
	 * Eigenschaften auslesen
	 * 
	 * @return Vector<Eigenschaft> alle Eigenschaften
	 */
	public Vector<Eigenschaft> getEigenschaften() throws Exception {
		return this.eMapper.getEigenschaften();
	}

	/**
	 * Gibt die Auswahlmöglichkeiten einer Eigenschaft aus
	 * 
	 * @param Eigenschaft
	 * @return Vector<Auswahl> alle AuswahlObjekte zu einer Eigenschaft
	 */
	public Vector<Auswahl> getAuswahlForEigenschaft(Eigenschaft e) throws Exception {
		return auswahlMapper.getAuswahlForEigenschaft(e);
	}

	/*********************************************************************
	 * Besuche
	 ********************************************************************/

	/**
	 * Profil als besucht markieren
	 * 
	 * @param Profil
	 */
	public void insertBesuch(Profil besuchtesProfil) throws Exception {
		Besuch b = new Besuch();
		b.setBesuchendesProfil(user);
		b.setBesuchtesProfil(besuchtesProfil);
		besuchMapper.insertBesuch(b);
	}

	/*********************************************************************
	 * Merkzettel und Kontaktsperren
	 ********************************************************************/

	/**
	 * Profile auf Merkzettel setzen
	 * 
	 * @param Vector
	 */
	public void insertMerkzettel(Vector<String> emails) throws Exception {
		for (int i = 0; i < emails.size(); i++) {
			Merkzettel mz = new Merkzettel();
			mz.setGemerktesProfil(emails.elementAt(i));
			mz.setMerkendesProfil(user.getEmail());
			merkMapper.insertMerkzettel(mz);
		}
	}

	/**
	 * Gibt den Merkzetel eines Profils aus
	 * 
	 * @return Vector<Merkzettel> für das eingeloggte Profil
	 */
	public Vector<Merkzettel> getMerkzettelByOwner() throws Exception {
		return merkMapper.getMerkzettelByOwner(user.getEmail());
	}

	/**
	 * Profile sperren
	 * 
	 * @param emails
	 */
	public void insertKontaktsperren(Vector<String> emails) throws Exception {
		for (int i = 0; i < emails.size(); i++) {
			Kontaktsperre k = new Kontaktsperre();
			k.setGesperrtesProfil(emails.elementAt(i));
			k.setSperrendesProfil(user.getEmail());
			ksMapper.insertKontaktsperre(k);
		}
	}

	/**
	 * Profil sperren
	 * 
	 * @param email
	 */
	public void insertKontaktsperre(String email) throws Exception {
		Kontaktsperre k = new Kontaktsperre();
		k.setGesperrtesProfil(email);
		k.setSperrendesProfil(user.getEmail());
		ksMapper.insertKontaktsperre(k);
	}

	/**
	 * Profil merken
	 * 
	 * @param email
	 */
	public void insertMerkzettel(String email) throws Exception {
		Merkzettel mz = new Merkzettel();
		mz.setGemerktesProfil(email);
		mz.setMerkendesProfil(user.getEmail());
		merkMapper.insertMerkzettel(mz);
	}

	/**
	 * gemerkte Profile eines Profils aufrufen
	 * 
	 * @return Vector<Profil>
	 */
	public Vector<Profil> getMerkzettelProfileByOwner() throws Exception {
		return merkMapper.getMerkzettelProfileByOwner(user.getEmail());
	}

	/**
	 * Profil vom Merkzettel entfernen
	 * 
	 * @param emails
	 */
	public void deleteMerkzettel(Vector<String> emails) throws Exception {
		for (int i = 0; i < emails.size(); i++) {
			Merkzettel mz = new Merkzettel();
			mz.setGemerktesProfil(emails.elementAt(i));
			mz.setMerkendesProfil(user.getEmail());
			merkMapper.deleteMerkzettel(mz);
		}
	}

	/**
	 *  Kontaktsperre von einem Profil aufheben
	 * @param emails
	 */
	public void deleteKontaktsperre(Vector<String> emails) throws Exception {
		for (int i = 0; i < emails.size(); i++) {
			Kontaktsperre ks = new Kontaktsperre();
			ks.setGesperrtesProfil(emails.elementAt(i));
			ks.setSperrendesProfil(user.getEmail());
			;
			ksMapper.deleteKontaktsperre(ks);
		}
	}

	/**
	 *  Alle Profile mit Kontaktsperre von einem Profil bekommen
	 *  @return Vector<Profil>
	 */
	public Vector<Profil> getKontaktsperrenByOwner() throws Exception {
		return ksMapper.getKontaktsperreProfileByOwner(user.getEmail());
	}

	/*********************************************************************
	 * Infos, SuchprofilInfos und ProfilInfos
	 ********************************************************************/

	/**
	 *  Info löschen
	 *  
	 *  @param info
	 */
	public void deleteInfo(Info info) throws Exception {
		ProfilInfo pi = new ProfilInfo();
		pi.setInfoID(info.getId());
		pi.setProfilEmail(user.getEmail());
		deleteProfilInfo(pi);
	}

	/**
	 *  SuchprofilInfo löschen
	 *  
	 *  @param suchprofil
	 *  @param info
	 */
	public void deleteSuchprofilInfo(Suchprofil sp, Info info) throws Exception {
		SuchprofilInfo spi = new SuchprofilInfo();
		spi.setInfo(info);
		spi.setProfil(user);
		spi.setSp(sp);
		spiMapper.deleteSuchprofilInfo(spi);
	}

	/**
	 *  ProfilInfo löschen
	 *  
	 *  @param pi
	 */
	public void deleteProfilInfo(ProfilInfo pi) throws Exception {
		piMapper.deleteProfilInfo(pi);
	}

	/**
	 *  ProfilInfos Hauptmethode
	 *  
	 *  @param info
	 */
	public void insertProfilInfo(Info info) throws Exception {
		// Verarbeitung der Info und Auslesen der ID
		int infoID = insertInfo(info);
		// Aufbau des ProfilInfo-Objekts
		ProfilInfo pi = new ProfilInfo();
		pi.setInfoID(infoID);
		pi.setProfilEmail(user.getEmail());
		// Eintrag in die Datenbank
		piMapper.insertProfilInfo(pi);
	}

	/**
	 *  SuchprofilInfos Hauptmethode
	 *  
	 *  @param sp
	 *  @param info
	 */
	public void insertSuchprofilInfo(Suchprofil sp, Info info) throws Exception {
		System.out.println("Speichere " + info.getValue() + " für " + sp.getSuchprofilname());
		// Verarbeitung der Info und Auslesen der ID
		info.setId(insertInfo(info));
		// Aufbau des SuchprofilInfo-Objekts
		SuchprofilInfo spi = new SuchprofilInfo();
		spi.setInfo(info);
		spi.setProfil(user);
		spi.setSp(sp);
		// Eintrag in die DB
		spiMapper.insertSuchprofilInfo(spi);
	}

	/**
	 *  Hilfsmethode f�r insertSuchprofilInfo() und ProfilInfo()
	 *  Checkt, ob es die Info bereits gibt. Gibt in jedem Fall
	 *  die InfoID zur�ck, damit die beiden n:m Tabellen damit
	 *  best�ckt werden k�nnen.
	 *  
	 * @param info
	 * @return Info-ID
	 */
	private int insertInfo(Info info) throws Exception {
		if (info.getValue().equals("")) {
			info.setValue("Keine Angabe");
		}
		// check, ob die Info bereits besteht und Auslesen der ID
		int infoID = InfoMapper.infoMapper().getInfoIDByEigenschaftsIDAndValue(info.getEigenschaft(), info.getValue());
		// falls nicht, Schreiben der neuen Info und Auslesen der ID
		if (infoID == 0) {
			infoMapper.insertInfo(info);
			infoID = infoMapper.getInfoIDByEigenschaftsIDAndValue(info.getEigenschaft(), info.getValue());
		}
		// zur�ckgeben der ID
		return infoID;
	}

	/**
	 *  Eigenschaften von Suchprofil aufrufen
	 *  
	 *  @param Suchprofilname
	 *  @return Vector mit SuchProfilEigenschaften
	 */
	public Vector<ProfilEigenschaft> getSuchprofilEigenschaften(String spname) throws Exception {
		Vector<ProfilEigenschaft> results = spiMapper.getSuchprofilInfosByEmail(user.getEmail(), spname);
		Vector<Eigenschaft> eigenschaften = eMapper.getEigenschaften();
		if (results.size() == 0) {
			return fillEigenschaften(results, eigenschaften);
		}
		if (results.size() < eigenschaften.size()) {
			return fillEigenschaften(results, eigenschaften);
		}
		return results;
	}

	/**
	 *  Profil Eigenschaften aufrufen
	 *  
	 *  @return Vector mit ProfilEigenschaften für das eingeloggte Profil
	 */
	public Vector<ProfilEigenschaft> getProfilEigenschaften() throws Exception {
		Vector<ProfilEigenschaft> results = piMapper.getProfilInfosByEmail(user.getEmail());
		Vector<Eigenschaft> eigenschaften = eMapper.getEigenschaften();
		if (results.size() < eigenschaften.size()) {
			return fillEigenschaften(results, eigenschaften);
		}
		return results;
	}

	/**
	 *  Profil Eigengschaften füllen, falls noch keine in der Datenbank vorhanden sind.
	 *  
	 * @param peigenschaften
	 * @param eigenschaften
	 */
	private Vector<ProfilEigenschaft> fillEigenschaften(Vector<ProfilEigenschaft> peigenschaften,
			Vector<Eigenschaft> eigenschaften) {
		Vector<ProfilEigenschaft> results = new Vector<ProfilEigenschaft>();
		for (int i = 0; i < eigenschaften.size(); i++) {
			boolean found = false;
			for (int o = 0; o < peigenschaften.size(); o++) {
				if (peigenschaften.elementAt(o).getEigenschaft().getId() == eigenschaften.elementAt(i).getId()) {
					results.add(peigenschaften.elementAt(o));
					found = true;
				}
			}
			if (!found) {
				ProfilEigenschaft pe = new ProfilEigenschaft();
				pe.setEigenschaft(eigenschaften.elementAt(i));
				Info info = new Info();
				info.setEigenschaft(eigenschaften.elementAt(i).getId());
				info.setValue("Keine Angabe");
				pe.setInfo(info);
				results.add(pe);
			}
		}
		return results;
	}

	/**
	 *  Alle Infos löschen von einem Profil
	 */
	public void deleteProfilInfosForUser() throws Exception {
		piMapper.deleteAllInfos(user.getEmail());
	}

	/**
	 *  Alles Infos löschen von einem Suchprofil
	 *  
	 *  @param sp
	 */
	public void deleteSuchprofilInfosForUser(Suchprofil sp) throws Exception {
		spiMapper.deleteAllSuchprofilInfos(sp, user);
	}

	/**
	 *  Profileigenschaften anhand der Email aufrufen
	 *  
	 *  @param email
	 *  @return Vector mit ProfilEigenschaften zu diesem Profil
	 */
	public Vector<ProfilEigenschaft> getProfilEigenschaften(String email) throws Exception {
		return piMapper.getProfilInfosByEmail(email);
	}
}
