package de.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Profil;
import de.shared.RO.ProfilInformation;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface TestService extends RemoteService {
	void seed() throws Exception;
	Vector<Profil> getAllProfiles() throws Exception;
	
	Vector<ProfilInformation> getProfilInfos() throws Exception;
	
	void setUser(Profil p);
}
