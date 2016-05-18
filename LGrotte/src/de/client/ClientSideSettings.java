package de.client;

import com.google.gwt.core.client.GWT;

import de.shared.EditorService;
import de.shared.EditorServiceAsync;
import de.shared.LoginService;
import de.shared.LoginServiceAsync;
import de.shared.ReportService;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;

public class ClientSideSettings {
	
	private static EditorServiceAsync editorService = null;
	private static ReportServiceAsync reportService = null;
	private static LoginServiceAsync loginService = null;
	private static Profil profil = null;
	
	public static Profil getUserProfil(){
		return profil;
	}
	
	public static void setUserProfil(Profil p){
		profil = p;
	}
	
	public static LoginServiceAsync getLoginService(){
		if(loginService == null){
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}
	
	public static EditorServiceAsync getEditorService() {
		if (editorService == null) {
			editorService = GWT.create(EditorService.class);
		}
		return editorService;
	}
	
	public static ReportServiceAsync getReportService() {
		if (reportService == null) {
			reportService = GWT.create(ReportService.class);
		}
		return reportService;
	}
	
}
