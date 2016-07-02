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
 * 
 * @author Thomas Bauer und Sedat Akar
 * 
 * @version 1.0
 */
@RemoteServiceRelativePath("report")
public interface ReportService extends RemoteService {
	
	/**
	 * @see de.server.report.ReportServiceImpl#getProfilReport(Profil); 
	 */
	ProfilReport getProfilReport(Profil p) throws Exception;
	
	/**
	 * @see de.server.report.ReportServiceImpl#setUser(Profil); 
	 */
	void setUser(Profil p);
	
	/**
	 * @see de.server.report.ReportServiceImpl#getReports(); 
	 */
	Vector<ProfilReport> getReports() throws Exception;
	
	/**
	 * @see de.server.report.ReportServiceImpl#getReports(Suchprofil) 
	 */
	Vector<ProfilReport> getReports(Suchprofil sp) throws Exception;

	/**
	 * @see de.server.report.ReportServiceImpl#getSuchprofile();
	 */
	Vector<Suchprofil> getSuchprofile() throws Exception;
	
	/**
	 * @see de.server.report.ReportServiceImpl#getNotVisitedReports(); 
	 */
	Vector<ProfilReport> getNotVisitedReports() throws Exception;
	
	/**
	 * @see de.server.report.ReportServiceImpl#getNotVisitedReports(Suchprofil) 
	 */
	Vector<ProfilReport> getNotVisitedReports(Suchprofil sp) throws Exception;
	
	
}
