package de.server.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.client.ClientSideSettings;
import de.server.db.ProfilMapper;
import de.shared.LoginService;
import de.shared.BO.Profil;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{
	
	//TestMethode
	public String hallo(){
		return "Halllooo ich bin dein Server";
	}
	
	
	public Profil login(String requestUri) throws Exception{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Profil p = ProfilMapper.profilMapper().getProfilByEmail(user.getEmail());
		p.setLoggedIn(true);
		ClientSideSettings.setUserProfil(p);
		return p;
	}
	
}
