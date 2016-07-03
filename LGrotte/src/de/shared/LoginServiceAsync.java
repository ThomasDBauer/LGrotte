package de.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Profil;

/**
 * Das asynchrone Gegenstück des Interface @link {@link LoginService}
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */


public interface LoginServiceAsync {
	
	
	
	/**
	 * @see de.server.login.LoginServiceImpl#login(String);
	 */
	void login(String requestUri, AsyncCallback<Profil>callback);
	
}
