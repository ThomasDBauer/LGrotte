package de.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Profil;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	Profil login(String requestUri) throws Exception;
}
