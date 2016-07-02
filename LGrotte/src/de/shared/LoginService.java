package de.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Profil;

/**
 * 
 * @author Thomas Bauer
 * 
 * The client-side stub for the RPC service.
 *
 */

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	
	/**
	 * @see de.server.login.LoginServiceImpl#login(String);
	 */
	Profil login(String requestUri) throws Exception;
}
