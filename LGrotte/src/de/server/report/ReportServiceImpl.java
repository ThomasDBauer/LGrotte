package de.server.report;

import java.util.Vector;

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
		sb.append("<h1 style = \"color: black\">Deine Grotte</h1>");
		sb.append("Name: " + p.getFname() + " " + p.getLname());
		sb.append("Email: " + p.getEmail());
		sb.append("Raucher: " + p.getRaucher());
		sb.append("Religion:" + p.getReligion());
		sb.append("</div>");
		return sb.toString();
	}
	
	public String showAllProfiles() throws Exception{
		
		Vector<Profil> profile = ProfilMapper.profilMapper().getAll();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < profile.size(); i++){
			sb.append(showProfilReport(profile.elementAt(i).getEmail()));
		}
		return sb.toString();
	}
}
