package de.server.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.client.ClientSideSettings;
import de.server.db.ProfilMapper;
import de.shared.LoginService;
import de.shared.BO.Profil;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {


	public Profil login(String requestUri) throws Exception {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Profil p = new Profil();
		if (user != null) {
			//EXISTING PROFILE
			Profil bestehendesProfil = ProfilMapper.profilMapper().getProfilByEmail(user.getEmail());
			if(bestehendesProfil != null){
			p.setLoggedIn(true);
				bestehendesProfil.setLoggedIn(true);
				bestehendesProfil.setLogoutUrl(userService.createLogoutURL(requestUri));
//				bestehendesProfil.setEmail(user.getEmail());
				ClientSideSettings.setUserProfil(bestehendesProfil);
				return bestehendesProfil;
			}
			//NO PROFILE
			p.setLoggedIn(true);
			p.setEmail(user.getEmail());
		//USER = NULL
		}else{
		p.setLoggedIn(false);
		}
		p.setLoginUrl(userService.createLoginURL(requestUri));
		return p;
	}
	
}
