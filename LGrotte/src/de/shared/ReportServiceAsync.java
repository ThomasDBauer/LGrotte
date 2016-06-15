package de.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;

public interface ReportServiceAsync {
	
	void hallo(AsyncCallback<String>callback);

	void getProfilReport(String email, AsyncCallback<ProfilReport>callback) throws Exception;
	
	void setUser(Profil p, AsyncCallback callback);
	
//	void getProfilInfos(AsyncCallback<Vector<ProfilInformation>>callback) throws Exception;

	void getAllReports(AsyncCallback<Vector<ProfilReport>> callback) throws Exception;
	
//	void createAlleProfileReport(AsyncCallback<AlleProfileReport> callback);
	
	void getReportsBySuchprofil(Suchprofil sp, AsyncCallback<Vector<ProfilReport>>callback) throws Exception;
	
	void getSuchprofile(AsyncCallback<Vector<Suchprofil>>callback) throws Exception;
	
}
