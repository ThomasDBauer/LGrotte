package de.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.shared.RO.AlleProfileReport;
/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("report")
public interface ReportService extends RemoteService {

	String showProfilReport(String email) throws Exception;

	String showAllProfiles()throws Exception;
	
	String showImpressum() throws Exception;

	String showMyProfile(String email) throws Exception;

	public abstract AlleProfileReport createAlleProfileReport() throws Exception;

	
}