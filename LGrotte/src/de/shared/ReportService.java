package de.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Profil;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("report")
public interface ReportService extends RemoteService {
	
	String hallo();

	String showProfilReport(String email) throws Exception;

	String showAllProfiles()throws Exception;
	
	String showImpressum() throws Exception;

	String showMyProfile(String email) throws Exception;
	
	ProfilReport getProfilReport(String email) throws Exception;
	
	void setUser(Profil p);
	
//	Vector<ProfilInformation> getProfilInfos() throws Exception;

	Vector<ProfilReport> getAllProfiles() throws Exception;

//	public abstract AlleProfileReport createAlleProfileReport() throws Exception;

	
}