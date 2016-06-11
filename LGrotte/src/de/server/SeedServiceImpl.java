package de.server;

import de.client.TestService;
import de.server.db.InfoMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.Seeder;
import de.server.db.SuchprofilInfoMapper;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;
import de.shared.BO.SuchprofilInfo;
import de.shared.RO.ProfilInformation;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

//huuuuuhuuu
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SeedServiceImpl extends RemoteServiceServlet implements TestService {

	private Profil user;

	public void setUser(Profil p) {
		user = p;
	}

	// die hier geht nicht. die ruft die methode im merkzettelmapper auf:
	public void seed() throws Exception {
		Seeder seeder = new Seeder();
		seeder.init();
	}

	public Vector<Profil> getAllProfiles() throws Exception {
		return ProfilMapper.profilMapper().getAll();
	}

	public Vector<ProfilInformation> getProfilInfos() throws Exception {

		return null;
	}
	
}
