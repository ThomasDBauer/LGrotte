package de.server;
import de.client.TestService;
import de.server.db.ProfilMapper;
import de.server.db.Seeder;
import de.shared.BO.Profil;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

//huuuuuhuuu
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SeedServiceImpl extends RemoteServiceServlet implements TestService {
	
	//die hier geht nicht. die ruft die methode im merkzettelmapper auf:
	public void seed() throws Exception{
		Seeder seeder = new Seeder();
		seeder.init();
	}
	
	public Vector<Profil> getAllProfiles() throws Exception{
		return ProfilMapper.profilMapper().getAll();
	}
	
} 
