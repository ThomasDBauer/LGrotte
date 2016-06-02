package de.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.server.db.ProfilMapper;
import de.shared.ReportService;
import de.shared.BO.Profil;
import de.shared.RO.AlleProfileReport;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	@Override
	public String showProfilReport(String email) throws Exception {
		Profil p = ProfilMapper.profilMapper().getProfilByEmail(email);
		StringBuffer sb = new StringBuffer();
		sb.append("<div style = \"border:1px solid black; margin: 10px; background-color: #F6CED8;\">");
		sb.append("<h2>" + p.getFname() +" "+ p.getLname() + "<br/>" + "</h2>");
		sb.append("<b>" + "Email: " + "</b>" + p.getEmail()+ " ");
		sb.append("<b>" + "Raucher: " + "</b>" + p.getRaucher()+ " ");
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
//		sb.append("<h2 style = \"color: black\">Impressum</h2>");
		sb.append("<div class>" + "<b>Angaben gemäß §5 TMG:</b>"
				+ "<p>LiebesGrotte<br />" + "Nobelstraße 10<br />" + "70569 Stuttgart" + "</p>" + "<b>Kontakt:</b>"
				+ "<table><tr>" + "<td>Telefon:</td>" + "<td>+49 711 8923 10</td></tr>" + "<tr><td>E-Mail:</td>"
				+ "<td>LG(at)hdm-stuttgart.de</td>" + "</tr></table><p></div>");
		return sb.toString();

	}

	@Override
	public String showMyProfile(String email) throws Exception {
		Profil profil = ProfilMapper.profilMapper().getProfilByEmail(email);
		StringBuffer sb = new StringBuffer();
		sb.append(showProfilReport(profil.getEmail()));
		return sb.toString();
	}

	@Override
	public AlleProfileReport createAlleProfileReport() throws Exception {
	    /*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
	    AlleProfileReport result = new AlleProfileReport();

	    // Jeder Report hat einen Titel (Bezeichnung / überschrift).
	    result.setTitle("Report aller Teilnehmer");

	       /*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
	    result.setCreated(new Date());

	    /*
	     * Da AllAccountsOfAllCustomersReport-Objekte aus einer Sammlung von
	     * AllAccountsOfCustomerReport-Objekten besteht, benötigen wir keine
	     * Kopfdaten für diesen Report-Typ. Wir geben einfach keine Kopfdaten an...
	     */

	    /*
	     * Nun müssen sämtliche Kunden-Objekte ausgelesen werden. Anschließend wir
	     * für jedes Kundenobjekt c ein Aufruf von
	     * createAllAccountsOfCustomerReport(c) durchgeführt und somit jeweils ein
	     * AllAccountsOfCustomerReport-Objekt erzeugt. Diese Objekte werden
	     * sukzessive der result-Variable hinzugefügt. Sie ist vom Typ
	     * AllAccountsOfAllCustomersReport, welches eine Subklasse von
	     * CompositeReport ist.
	     */
	    Vector<Profil> alleProfile = ProfilMapper.profilMapper().getAll();

	    for (Profil c : alleProfile) {
	      /*
	       * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
	       */
	      result.addSubReport(createAlleProfileReport());
	    }

	    /*
	     * Zu guter Letzt müssen wir noch den fertigen Report zurückgeben.
	     */
	    return result;
	  }
	}


