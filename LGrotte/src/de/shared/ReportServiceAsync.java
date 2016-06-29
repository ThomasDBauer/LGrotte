package de.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;

public interface ReportServiceAsync {
	
	void hallo(AsyncCallback<String>callback);

	void getProfilReport(Profil p, AsyncCallback<ProfilReport>callback) throws Exception;
	
	void setUser(Profil p, AsyncCallback callback);
	
	void getReports(AsyncCallback<Vector<ProfilReport>> callback) throws Exception;
	
	void getReports(Suchprofil sp, AsyncCallback<Vector<ProfilReport>> callback) throws Exception;
	
	void getSuchprofile(AsyncCallback<Vector<Suchprofil>>callback) throws Exception;
	
	void getNotVisitedReports(AsyncCallback<Vector<ProfilReport>>callback) throws Exception;
	
	void getNotVisitedReports(Suchprofil sp, AsyncCallback<Vector<ProfilReport>>callback) throws Exception;
}
