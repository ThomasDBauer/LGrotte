package de.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.shared.RO.AlleProfileReport;

public interface ReportServiceAsync {

	public void showProfilReport(String email, AsyncCallback<String> callback) throws Exception;

	public void showAllProfiles(AsyncCallback<String> callback) throws Exception;

	public void showImpressum(AsyncCallback<String> callback)throws Exception;

	public void showMyProfile(String email, AsyncCallback<String> callback) throws Exception;
	
	void createAlleProfileReport(AsyncCallback<AlleProfileReport> callback);
}
