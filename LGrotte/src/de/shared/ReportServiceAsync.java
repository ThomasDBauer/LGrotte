package de.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Profil;

public interface ReportServiceAsync {

	public void showProfilReport(String email, AsyncCallback<Profil> callback) throws Exception;
}
