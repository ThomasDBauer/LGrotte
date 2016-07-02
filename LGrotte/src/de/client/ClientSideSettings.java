package de.client;

import com.google.gwt.core.client.GWT;

import de.shared.EditorService;
import de.shared.EditorServiceAsync;
import de.shared.LoginService;
import de.shared.LoginServiceAsync;
import de.shared.ReportService;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;

/**
 * Die Service-Objekte 
 * 
 * @author Thomas Bauer
 *
 */

public class ClientSideSettings {
	
	//Nullsetzen der Variablen
	private static EditorServiceAsync editorService = null;
	private static ReportServiceAsync reportService = null;
	private static LoginServiceAsync loginService = null;
	private static Profil profil = null;
	
	/**
	 * Gibt das eingeloggte Profil zurück
	 * @return Profil des eingeloggten Users
	 */
	public static Profil getUserProfil(){
		return profil;
	}
	
	/**
	 * aktuelles Profil setzen
	 * @param p Profil
	 */
	public static void setUserProfil(Profil p){
		profil = p;
	}
	
	/**
	 * Singleton-Methode
	 * @return LoginService Objekt
	 */
	public static LoginServiceAsync getLoginService(){
		if(loginService == null){
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}
	
	/**
	 * Singleton-Methode
	 * @return EditorService Objekt
	 */
	public static EditorServiceAsync getEditorService() {
		if (editorService == null) {
			editorService = GWT.create(EditorService.class);
		}
		return editorService;
	}
	
	/**
	 * Singleton-Methode
	 * @return ReportService Objekt
	 */
	public static ReportServiceAsync getReportService() {
		if (reportService == null) {
			reportService = GWT.create(ReportService.class);
		}
		return reportService;
	}
	
}
