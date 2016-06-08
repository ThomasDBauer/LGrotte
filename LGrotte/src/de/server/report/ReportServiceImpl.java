package de.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.EigenschaftMapper;
import de.server.db.ProfilMapper;
import de.shared.ReportService;
import de.shared.BO.Profil;
import de.shared.RO.ProfilAttribut;
import de.shared.RO.ProfilEigenschaft;
import de.shared.RO.ProfilReport;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	private ProfilMapper profilMapper = null;
	private EigenschaftMapper eigenschaftMapper = null;
	private Profil user;

	public void setUser(Profil p) {
		user = p;
	}

	public void init() throws IllegalArgumentException {
		this.profilMapper = ProfilMapper.profilMapper();
		this.eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
	}

	public String hallo() {
		return "Hallo, ich bin der ReportService";
	}

	public ProfilReport getProfilReport(String email) throws Exception{

		Profil p = ProfilMapper.profilMapper().getProfilByEmail("Anna@LG");
		
		ProfilReport report = new ProfilReport();
		
		report.setHeader(p.getFname()+ " " + p.getLname());
		
		ProfilAttribut geschlecht = new ProfilAttribut();
		geschlecht.setName("Geschlecht");
		geschlecht.setWert(p.getGeschlecht());
		
		ProfilAttribut haarFarbe = new ProfilAttribut();
		haarFarbe.setName("Haarfarbe");
		haarFarbe.setWert(p.getHaarfarbe());
		
		ProfilAttribut religion = new ProfilAttribut();
		religion.setName("Religion");
		religion.setWert(p.getReligion());
		
		report.addAttribut(geschlecht);
		report.addAttribut(haarFarbe);
		report.addAttribut(religion);
		
		
		ProfilEigenschaft pe = new ProfilEigenschaft();
		pe.setName("");
		pe.getWert();
	
		
		return report;

	}

	@Override
	public String showProfilReport(String email) throws Exception {
		Profil p = ProfilMapper.profilMapper().getProfilByEmail(email);
		StringBuffer sb = new StringBuffer();
		sb.append("<div style = \"border:1px solid black; margin: 10px; background-color: #F6CED8;\">");
		sb.append("<h2>" + p.getFname() + " " + p.getLname() + "<br/>" + "</h2>");
		sb.append("<b>" + "Email: " + "</b>" + p.getEmail() + " ");
		sb.append("<b>" + "Raucher: " + "</b>" + p.getRaucher() + " ");
		sb.append("<b>" + "Religion: " + "</b>" + p.getReligion());
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
		// sb.append("<h2 style = \"color: black\">Impressum</h2>");
		sb.append("<div class>" + "<b>Angaben gem‰ﬂ ß5 TMG:</b>" + "<p>LiebesGrotte<br />" + "Nobelstraﬂe 10<br />"
				+ "70569 Stuttgart" + "</p>" + "<b>Kontakt:</b>" + "<table><tr>" + "<td>Telefon:</td>"
				+ "<td>+49 711 8923 10</td></tr>" + "<tr><td>E-Mail:</td>" + "<td>LG(at)hdm-stuttgart.de</td>"
				+ "</tr></table><p></div>");
		return sb.toString();

	}

	@Override
	public String showMyProfile(String email) throws Exception {
		Profil profil = ProfilMapper.profilMapper().getProfilByEmail(email);
		StringBuffer sb = new StringBuffer();
		sb.append(showProfilReport(profil.getEmail()));
		return sb.toString();
	}
}