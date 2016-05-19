package de.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.server.db.ProfilMapper;
import de.shared.ReportService;
import de.shared.BO.Profil;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	@Override
	public String showProfilReport(String email) throws Exception {
		Profil p = ProfilMapper.profilMapper().getProfilByEmail(email);
		StringBuffer sb = new StringBuffer();
		sb.append("<div style = \"color: black\">");
		sb.append("<h1 style = \"color: black\">Mein Profil</h1>");
		sb.append("Name: " + p.getFname() + " " + p.getLname());
		sb.append("Email: " + p.getEmail());
		sb.append("Raucher: " + p.getRaucher());
		sb.append("Religion:" + p.getRaucher());
		sb.append("</div>");
		return sb.toString();
	}

}
