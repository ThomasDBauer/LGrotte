package de.server.report;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.ProfilMapper;
import de.server.db.Seeder;
import de.shared.EditorService;
import de.shared.ReportService;
import de.shared.BO.Profil;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	@Override
	public Profil showProfilReport(int id) throws Exception{
		Profil p = ProfilMapper.profilMapper().getProfilByID(id);
		return p;
		
	}

	

}
