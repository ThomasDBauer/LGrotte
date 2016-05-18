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
	
	public void insertProfil(String fname, String lname, String geschlecht, String haarfarbe, int koerpergroesse, String religion, String raucher, Date geburtsdatum) throws IllegalArgumentException{
		Profil p = new Profil();
		p.setFname(fname);
		p.setLname(lname);
		p.setGeschlecht(geschlecht);
		p.setHaarfarbe(haarfarbe);
		p.setKoerpergroesse(koerpergroesse);
		p.setReligion(religion);
		p.setRaucher(raucher);
		p.setGeburtsdatum(geburtsdatum);
		
		try {
			ProfilMapper.profilMapper().insertProfil(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
