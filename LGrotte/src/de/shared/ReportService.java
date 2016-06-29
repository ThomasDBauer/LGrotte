package de.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("report")
public interface ReportService extends RemoteService {
	
	String hallo();

	ProfilReport getProfilReport(Profil p) throws Exception;
	
	void setUser(Profil p);

	Vector<ProfilReport> getReports() throws Exception;
	
	Vector<ProfilReport> getReports(Suchprofil sp) throws Exception;

	Vector<Suchprofil> getSuchprofile() throws Exception;
	
	Vector<ProfilReport> getNotVisitedReports() throws Exception;
	Vector<ProfilReport> getNotVisitedReports(Suchprofil sp) throws Exception;
	
	
}
