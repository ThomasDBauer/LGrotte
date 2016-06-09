package de.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Profil;
import de.shared.RO.ProfilInformation;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface TestServiceAsync {
	void seed(AsyncCallback callback) throws Exception;
	void getAllProfiles(AsyncCallback<Vector<Profil>>callback) throws Exception;
	
	void getProfilInfos(AsyncCallback<Vector<ProfilInformation>>callback) throws Exception;
	
	void setUser(Profil p, AsyncCallback callback);
}
