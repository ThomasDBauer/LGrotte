package de.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Profil;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;

public interface ReportServiceAsync {
	
	void hallo(AsyncCallback<String>callback);

	public void showProfilReport(String email, AsyncCallback<String> callback) throws Exception;

	public void showAllProfiles(AsyncCallback<String> callback) throws Exception;

	public void showImpressum(AsyncCallback<String> callback)throws Exception;

	public void showMyProfile(String email, AsyncCallback<String> callback) throws Exception;
	
	void getProfilReport(String email, AsyncCallback<ProfilReport>callback) throws Exception;
	
	
	void setUser(Profil p, AsyncCallback callback);
	
//	void getProfilInfos(AsyncCallback<Vector<ProfilInformation>>callback) throws Exception;

	void getAllProfiles(AsyncCallback<Vector<ProfilReport>> callback) throws Exception;
	
//	void createAlleProfileReport(AsyncCallback<AlleProfileReport> callback);
}
