package de.server.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.client.ClientSideSettings;
import de.client.TestService;
import de.server.db.EigenschaftMapper;
import de.server.db.InfoMapper;
import de.server.db.ProfilMapper;
import de.server.db.SuchprofilMapper;
import de.shared.EditorService;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements EditorService{
	
	private ProfilMapper pMapper = null;
	private EigenschaftMapper eMapper = null;
	
	public void init() throws IllegalArgumentException {
		this.pMapper = ProfilMapper.profilMapper();
		this.eMapper = EigenschaftMapper.eigenschaftMapper();
	}
	// Methoden rund um das Profil
	// Profil erstellen
	public void insertProfil(String email, String fname, String lname, int koerpergroesse, 
			String geschlecht, String religion, String haarfarbe, String raucher, Date geburtsdatum) 
					throws IllegalArgumentException{
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
	public void insertSuchprofil(String suchprofilname, String geschlecht, 
			String raucher, String religion, int minAlter, int maxAlter, 
			int koerpergroesse, String haarfarbe) throws Exception{
		Suchprofil sp = new Suchprofil();
		sp.setSuchprofilname(suchprofilname);
		sp.setGeschlecht(geschlecht);
		sp.setRaucher(raucher);
		sp.setReligion(religion);
		sp.setMinAlter(minAlter);
		sp.setMaxAlter(maxAlter);
		sp.setKoerpergroesse(koerpergroesse);
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
	public void insertInfo(Info info) throws Exception{
		InfoMapper.infoMapper().insertInfo(info);
	}
	
	public Vector<Suchprofil> getSuchprofile() throws Exception{
		String email = ClientSideSettings.getUserProfil().getEmail();
		return SuchprofilMapper.suchprofilMapper().getSuchprofileByEmail(email);
	}
	
	
	
}
