package de.server.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.shared.LoginService;
import de.shared.BO.Profil;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{
	
	//TestMethode
	public String hallo(){
		return "Halllooo ich bin dein Server";
	}
	
	
	public Profil login(){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		return null;
	}
	
}
