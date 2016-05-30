package de.server;

import de.shared.CommonSettings;
import de.shared.BO.Profil;

public class ServersideSettings extends CommonSettings{
	
	private static ServersideSettings settings = null;
	private static Profil profil = null;
	
	private ServersideSettings(){
		
	}
	
	public static ServersideSettings settings() {
		if (settings == null) {
			settings = new ServersideSettings();
		}
		return settings;
	}
	
	public static Profil getUserProfil(){
		return profil;
	}
	
	public static void setUserProfil(Profil p){
		profil = p;
	}
	
}
