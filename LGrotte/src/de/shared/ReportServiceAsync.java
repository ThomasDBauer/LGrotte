package de.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.RO.ProfilReport;

public interface ReportServiceAsync {
	
	void hallo(AsyncCallback<String>callback);

	public void showProfilReport(String email, AsyncCallback<String> callback) throws Exception;

	public void showAllProfiles(AsyncCallback<String> callback) throws Exception;

	public void showImpressum(AsyncCallback<String> callback)throws Exception;

	public void showMyProfile(String email, AsyncCallback<String> callback) throws Exception;
	
	void getProfilReport(AsyncCallback<ProfilReport>callback) throws Exception;
	
//	void createAlleProfileReport(AsyncCallback<AlleProfileReport> callback);
}
