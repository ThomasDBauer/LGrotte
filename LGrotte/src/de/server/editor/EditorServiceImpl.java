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
import de.server.db.SuchprofilMapper;
import de.shared.EditorService;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Kontaktsperre;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;
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
		sp.setProfil(ClientSideSettings.getUserProfil().getEmail());
		SuchprofilMapper.suchprofilMapper().insertSuchprofil(sp);
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

	// Infos einfürgen
	public void insertInfo(Info info) throws Exception {
		InfoMapper.infoMapper().insertInfo(info);
	}

	public Vector<Suchprofil> getSuchprofile() throws Exception {
		String email = ClientSideSettings.getUserProfil().getEmail();
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
			//User aussortieren
			if (profile.elementAt(i).getEmail().equals(user.getEmail())){
				profile.remove(i);
				continue;
			}
		}
		
		return profile;
		
	}		
	
	// Suchprofil anzeigen by name
		public Vector<Suchprofil> getSuchprofileByName(String suchprofilname) throws Exception{
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
	
	public void insertKontaktsperren(Vector<String>emails) throws Exception {
		for(int i = 0; i < emails.size(); i++){
			Kontaktsperre k = new Kontaktsperre();
			k.setGesperrtesProfil(emails.elementAt(i));
			k.setSperrendesProfil(user.getEmail());
			KontaktsperreMapper.kontaktsperreMapper().insertKontaktsperre(k);
		}
	}

}
