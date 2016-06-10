package de.server.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.client.ClientSideSettings;
import de.client.TestService;
import de.server.db.EigenschaftMapper;
import de.server.db.InfoMapper;
import de.server.db.KontaktsperreMapper;
import de.server.db.MerkzettelMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.SuchprofilMapper;
import de.shared.EditorService;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Kontaktsperre;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements EditorService {

	private ProfilMapper pMapper = null;
	private EigenschaftMapper eMapper = null;
	private Profil user;

	public void setUser(Profil p) {
		user = p;
	}

	public void init() throws IllegalArgumentException {
		this.pMapper = ProfilMapper.profilMapper();
		this.eMapper = EigenschaftMapper.eigenschaftMapper();
	}

	// Methoden rund um das Profil
	// Profil erstellen
	public void insertProfil(String email, String fname, String lname, int koerpergroesse, String geschlecht,
			String religion, String haarfarbe, String raucher, Date geburtsdatum) throws IllegalArgumentException {
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

		try {
			ProfilMapper.profilMapper().insertProfil(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Profil bearbeiten
	public void updateProfil(String fname, String lname, int koerpergroesse, String geschlecht,
			String religion, String haarfarbe, String raucher, Date geburtsdatum, String email) throws Exception {
		Profil p = new Profil();
		p.setFname(fname);
		p.setLname(lname);
		p.setKoerpergroesse(koerpergroesse);
		p.setGeschlecht(geschlecht);
		p.setReligion(religion);
		p.setHaarfarbe(haarfarbe);
		p.setGeburtsdatum(geburtsdatum);
		p.setRaucher(raucher);
		p.setEmail(user.getEmail());
		ProfilMapper.profilMapper().updateProfil(p);
	}

	// Profil löschen
	public void deleteProfil(String email) throws IllegalArgumentException {

		Profil deletep = new Profil();
		deletep.setEmail(email);

		try {
			ProfilMapper.profilMapper().deleteProfil(deletep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Profile anzeigen lassen im Bearbeiten Modus
	public Profil getProfilEintraege(String Email) throws Exception {
		String email = user.getEmail();
		return ProfilMapper.profilMapper().getProfilByEmail(email);
	}

	// Methoden zum Suchprofil
	// Suchprofil erstellen
	public void insertSuchprofil(String suchprofilname, String geschlecht, String raucher, String religion,
			int minAlter, int maxAlter, int minGroesse, int maxGroesse, String haarfarbe) throws Exception {
		Suchprofil sp = new Suchprofil();
		sp.setSuchprofilname(suchprofilname);
		sp.setGeschlecht(geschlecht);
		sp.setRaucher(raucher);
		sp.setReligion(religion);
		sp.setMinAlter(minAlter);
		sp.setMaxAlter(maxAlter);
		sp.setMinGroesse(minGroesse);
		sp.setMaxGroesse(maxGroesse);
		sp.setHaarfarbe(haarfarbe);
		sp.setProfil(user.getEmail());
		SuchprofilMapper.suchprofilMapper().insertSuchprofil(sp);
	}
	
	// Suchprofil bearbeiten
	public void updateSuchprofil(String geschlecht, String raucher, String religion,
			int minAlter, int maxAlter, int minGroesse, int maxGroesse, String haarfarbe, String suchprofilname) throws Exception {
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
		SuchprofilMapper.suchprofilMapper().updateSuchprofil(sp);
	}

	// Suchprofillöschen
	public void deleteSuchprofil(String suchprofilname) throws Exception {
		Suchprofil deletesp = new Suchprofil();
		deletesp.setSuchprofilname(suchprofilname);
		SuchprofilMapper.suchprofilMapper().deleteSuchprofil(deletesp);
	}

	// Methoden rund um die Eigenschaften und Infos
	// Eigenschaften auslesen
	public Vector<Eigenschaft> getEigenschaften() throws Exception {
		return this.eMapper.getEigenschaften();
	}

	
	
	public Vector<Suchprofil> getSuchprofile() throws Exception {
		String email = user.getEmail();
		return SuchprofilMapper.suchprofilMapper().getSuchprofileByEmail(email);
	}
	

	/*
	 * Die Methode liefert alle Profile zur�ck, die nicht auf Merkzetteln oder
	 * Kontaktsperren des Users stehen.
	 */
	public Vector<Profil> getProfilesForEditor() throws Exception {

		// Erstmal alle Profile auslesen:
		Vector<Profil> profile = ProfilMapper.profilMapper().getAll();
		// Au0erdem alle Merkzettel und Kontaktsperren des Users:
		Vector<Merkzettel> merkzettel = MerkzettelMapper.merkzettelMapper().
				getMerkzettelByOwner(user.getEmail());
		Vector<Kontaktsperre> sperren = KontaktsperreMapper.kontaktsperreMapper().
				getKontaktsperreByOwner(user.getEmail());

		for(int i = 0; i < profile.size(); i++){
			
			//User aussortieren
//			if(profile.elementAt(i).getEmail().equals(user.getEmail())){
//				profile.remove(i);
//			}
			
			//Merkzettel aussortieren
			for(int o = 0; o < merkzettel.size(); o++){
				if(profile.elementAt(i).getEmail().equals(
						merkzettel.elementAt(o).getGemerktesProfil())){
					profile.remove(i);
				};
			}
			//Kontaktsperren aussortieren
			for(int o = 0; o < sperren.size(); o++){
				if(profile.elementAt(i).getEmail().equals(
						sperren.elementAt(o).getGesperrtesProfil())){
					profile.remove(i);
				};
			}
		}
			return profile;
	}		
	
	// Suchprofil anzeigen by name
		public Suchprofil getSuchprofileByName(String suchprofilname) throws Exception{
			return SuchprofilMapper.suchprofilMapper().getSuchprofiByName(suchprofilname);
		}

	public void insertMerkzettel(Vector<String> emails) throws Exception {
		for (int i = 0; i < emails.size(); i++) {
			Merkzettel mz = new Merkzettel();
			mz.setGemerktesProfil(emails.elementAt(i));
			mz.setMerkendesProfil(user.getEmail());
			MerkzettelMapper.merkzettelMapper().insertMerkzettel(mz);
		}
	}
	
	public Vector<Merkzettel> getMerkzettelByOwner() throws Exception{
			String email = user.getEmail();
			return MerkzettelMapper.merkzettelMapper().getMerkzettelByOwner(email);
	}
	
	public void insertKontaktsperren(Vector<String>emails) throws Exception {
		for(int i = 0; i < emails.size(); i++){
			Kontaktsperre k = new Kontaktsperre();
			k.setGesperrtesProfil(emails.elementAt(i));
			k.setSperrendesProfil(user.getEmail());
			KontaktsperreMapper.kontaktsperreMapper().insertKontaktsperre(k);
		}
	}
	// Infos einfürgen
	public void insertInfo(Info info) throws Exception {
		
		int infoID = InfoMapper.infoMapper().getInfoIDByEigenschaftsIDAndValue(
				info.getEigenschaft(), info.getValue());
		
		if(infoID == 0){
			InfoMapper.infoMapper().insertInfo(info);
			infoID = InfoMapper.infoMapper().getInfoIDByEigenschaftsIDAndValue(
					info.getEigenschaft(), info.getValue());
		}
		
		ProfilInfo pi = new ProfilInfo();
		pi.setInfoID(infoID);
		pi.setProfilEmail(user.getEmail());
		insertProfilInfo(pi);		
	}

	// ProfilInfos einfügen
	public void insertProfilInfo(ProfilInfo pi) throws Exception {
		ProfilinfoMapper.profilinfoMapper().insertProfilInfo(pi);
	}
	
	public void deleteInfo(Info info) throws Exception {
		InfoMapper.infoMapper().deleteInfo(info);
		ProfilInfo pi = new ProfilInfo();
		pi.setInfoID(info.getId());
		pi.setProfilEmail(user.getEmail());
		deleteProfilInfo(pi);
	}
	public void deleteProfilInfo(ProfilInfo pi) throws Exception {
		ProfilinfoMapper.profilinfoMapper().deleteProfilInfo(pi);
	}
	

}

