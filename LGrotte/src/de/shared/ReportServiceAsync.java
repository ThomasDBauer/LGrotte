package de.shared;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;

/**
 * 
 * asynchroner Teil des Interface @link {@link ReportService}
 * 
 * @author Thomas Bauer und Sedat Akar
 * 
 * @version 1.0
 */
public interface ReportServiceAsync {
	
	/**
	 * @see de.server.report.ReportServiceImpl#getProfilReport(Profil);
	 */
	void getProfilReport(Profil p, AsyncCallback<ProfilReport>callback) throws Exception;
	
	
	/**
	 * @see de.server.report.ReportServiceImpl#setUser(Profil)
	 */
	void setUser(Profil p, AsyncCallback callback);
	
	
	/**
	 * @see de.server.report.ReportServiceImpl#getReports();
	 */
	void getReports(AsyncCallback<Vector<ProfilReport>> callback) throws Exception;
	
	
	/**
	 * @see de.server.report.ReportServiceImpl#getReports(Suchprofil)
	 */
	void getReports(Suchprofil sp, AsyncCallback<Vector<ProfilReport>> callback) throws Exception;
	
	
	/**
	 * @see de.server.report.ReportServiceImpl#getSuchprofile();
	 */
	void getSuchprofile(AsyncCallback<Vector<Suchprofil>>callback) throws Exception;
	
	
	/**
	 * @see de.server.report.ReportServiceImpl#getNotVisitedReports();
	 */
	void getNotVisitedReports(AsyncCallback<Vector<ProfilReport>>callback) throws Exception;
	
	
	/**
	 * @see de.server.report.ReportServiceImpl#getNotVisitedReports(Suchprofil);
	 */
	void getNotVisitedReports(Suchprofil sp, AsyncCallback<Vector<ProfilReport>>callback) throws Exception;
}
