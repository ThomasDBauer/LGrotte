package de.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Profil;

public interface LoginServiceAsync {
	void login(String requestUri, AsyncCallback<Profil>callback);
	
}
