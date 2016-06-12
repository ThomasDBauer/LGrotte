package de.server.login;

import java.sql.Date;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.server.db.ProfilMapper;
import de.shared.LoginService;
import de.shared.BO.Profil;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private static final long serialVersionUID = 1L;


	public String hallo(){
		return "Ich bin Lukes Server";
	}
	

	@SuppressWarnings("deprecation")
	public Profil login(String requestUri) throws Exception {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Profil p = new Profil();
		
		if (user != null) {
			//EXISTING PROFILE
			Profil bestehendesProfil = ProfilMapper.profilMapper().getProfilByEmail(user.getEmail());
			if(bestehendesProfil.getEmail() != null){
				bestehendesProfil.setLoggedIn(true);
				bestehendesProfil.setLogoutUrl(userService.createLogoutURL(requestUri));
				bestehendesProfil.setEmail(user.getEmail());
				return bestehendesProfil;
			}
			//if (bestehendesProfil == null && user != null)
			p.setLoggedIn(true);
			p.setGeburtsdatum(new Date(1, 1, 1));
			p.setEmail(user.getEmail());
			p.setLogoutUrl(userService.createLogoutURL(requestUri));
			ProfilMapper.profilMapper().insertProfil(p);
		//if(user == null)
		}else{
			p.setLoggedIn(false);
		}
		p.setLoginUrl(userService.createLoginURL(requestUri));
		return p;
	}
	
}
