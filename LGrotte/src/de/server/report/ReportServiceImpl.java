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
		sb.append("<h2>" + p.getFname() +" "+ p.getLname() + "</h2>");
		sb.append("Email: " + p.getEmail());
		sb.append("Raucher: " + p.getRaucher());
		sb.append("Religion:" + p.getReligion());
		sb.append("</div>");
		return sb.toString();
	}

	public String showAllProfiles() throws Exception {

		Vector<Profil> profile = ProfilMapper.profilMapper().getAll();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < profile.size(); i++) {
			sb.append(showProfilReport(profile.elementAt(i).getEmail()));
		}
		return sb.toString();
	}

	public String showImpressum() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<div style = \"color: black\">");
		sb.append("<h2 style = \"color: black\">Impressum</h2>");
		sb.append("<div class>" + "<b>Angaben gem‰ﬂ ß5 TMG:</b>"
				+ "<p>LiebesGrotte<br />" + "Nobelstraﬂe 10<br />" + "70569 Stuttgart" + "</p>" + "<b>Kontakt:</b>"
				+ "<table><tr>" + "<td>Telefon:</td>" + "<td>+49 711 8923 10</td></tr>" + "<tr><td>E-Mail:</td>"
				+ "<td>LG(at)hdm-stuttgart.de</td>" + "</tr></table><p></div>");
		return sb.toString();

	}
}
