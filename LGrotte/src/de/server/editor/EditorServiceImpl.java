package de.server.editor;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.client.TestService;
import de.server.db.ProfilMapper;
import de.shared.EditorService;
import de.shared.BO.Profil;
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements EditorService{
	
	private ProfilMapper pMapper = null;
	
	public void init() throws IllegalArgumentException {
		this.pMapper = ProfilMapper.profilMapper();
	}
	
	public void insertProfil(String email, String fname, String lname, int koerpergroesse, String geschlecht, String religion, String haarfarbe, String raucher, Date geburtsdatum) throws IllegalArgumentException{
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
}
