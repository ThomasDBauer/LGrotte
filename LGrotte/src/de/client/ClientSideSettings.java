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
	
	/**Nullsetzen der Variablen
	 * 
	 */
	private static EditorServiceAsync editorService = null;
	private static ReportServiceAsync reportService = null;
	private static LoginServiceAsync loginService = null;
	private static Profil profil = null;

	/**
	 * Getter und Setter für das UserProfil
	 * @return
	 */
	public static Profil getUserProfil(){
		return profil;
	}
	
	public static void setUserProfil(Profil p){
		profil = p;
	}
	
	/**
	 * Abfrage ob LoginService = null ist 
	 * @return
	 */
	public static LoginServiceAsync getLoginService(){
		if(loginService == null){
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}
	/**
	 * Abfrage ob editorService = null ist 
	 * @return
	 */
	public static EditorServiceAsync getEditorService() {
		if (editorService == null) {
			editorService = GWT.create(EditorService.class);
		}
		return editorService;
	}
	/**
	 * Abfrage ob reportService = null ist 
	 * @return
	 */
	public static ReportServiceAsync getReportService() {
		if (reportService == null) {
			reportService = GWT.create(ReportService.class);
		}
		return reportService;
	}
	
}
