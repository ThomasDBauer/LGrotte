package de.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.BesucheMapper;
import de.server.db.EigenschaftMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.SuchprofilMapper;
import de.shared.ReportService;
import de.shared.BO.Aehnlichkeitsmass;
import de.shared.BO.Besuch;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.Match;
import de.shared.RO.ProfilAttribut;
import de.shared.RO.ProfilEigenschaft;
import de.shared.RO.ProfilInformation;
import de.shared.RO.ProfilReport;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	private ProfilMapper profilMapper = ProfilMapper.profilMapper();
	private EigenschaftMapper eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
	private ProfilinfoMapper profilInfoMapper = ProfilinfoMapper.profilinfoMapper();
	private BesucheMapper besucheMapper = BesucheMapper.besucheMapper();
	private SuchprofilMapper spMapper = SuchprofilMapper.suchprofilMapper();
	
	private Profil user;

	public void setUser(Profil p) {
		user = p;
	}

	public String hallo() {
		return "Hallo, ich bin der ReportService";
	}

	/*
	 * Alle Suchprofile auslesen
	 */
	public Vector<Suchprofil> getSuchprofile() throws Exception {
		return SuchprofilMapper.suchprofilMapper().getSuchprofileByEmail(user.getEmail());
	}

	/*
	 * Erzeugt den ProfilReport für ein einzelnes Profil
	 */
	public ProfilReport getProfilReport(Profil p) throws Exception {
		// Auslesen des Profils aus der DB
		// Erstellen des Reports
		// 1. Attribute:
		ProfilReport report = new ProfilReport();
		report.setHeader(p.getFname() + " " + p.getLname());
		ProfilAttribut geschlecht = new ProfilAttribut();
		geschlecht.setName("Geschlecht");
		geschlecht.setWert(p.getGeschlecht());
		ProfilAttribut haarFarbe = new ProfilAttribut();
		haarFarbe.setName("Haarfarbe");
		haarFarbe.setWert(p.getHaarfarbe());
		ProfilAttribut religion = new ProfilAttribut();
		religion.setName("Religion");
		religion.setWert(p.getReligion());
		ProfilAttribut raucher = new ProfilAttribut();
		raucher.setName("Raucher");
		raucher.setWert(p.getRaucher());
		report.addAttribut(geschlecht);
		report.addAttribut(haarFarbe);
		report.addAttribut(religion);
		report.addAttribut(raucher);

		// 2. Eigenschaften
		Vector<ProfilEigenschaft> profilinfos = 
				profilInfoMapper.getProfilInfosByEmail(p.getEmail());
		for (int i = 0; i < profilinfos.size(); i++) {
			report.addEigenschaft(profilinfos.elementAt(i));
		}
		return report;
	}

	/*
	 * Alle Reports nach Suchprofil
	 */
	public Vector<ProfilReport> getReports(Suchprofil sp) throws Exception {
		// Alle Profile
		Vector<Profil> profile = profilMapper.getAll();
		Vector<Profil> results = new Vector<Profil>();
		for (int i = 0; i < profile.size(); i++) {
			Profil p = profile.elementAt(i);
			// Einziges No-Go Kriterium: Geschlecht
			if (!p.getGeschlecht().equals(sp.getGeschlecht())) {
			} else {
				results.add(p);
			}
		}
		// ProfilReports erstellen
		// Speicher für ProfilReports
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		for (int i = 0; i < results.size(); i++) {
			ProfilReport report = getProfilReport(results.elementAt(i));

			// Das Match anhand des Suchprofils errechnen
			Match m = new Match(aehnlichkeitBerechnen(profile.elementAt(i), sp));
			report.setMatch(m);

			// final den ProfilReport zu den Ergebnissen hinzufügen
			reports.add(report);
		}
		return reports;
	}

	/*
	 * Alle Reports
	 */
	public Vector<ProfilReport> getReports() throws Exception {
		Vector<Profil> profile = profilMapper.getAll();
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		for (int i = 0; i < profile.size(); i++) {
			ProfilReport report = getProfilReport(profile.elementAt(i));

			// Das Match; im Vergleich zw. dem Profil des Users (nicht
			// Suchprofil!)
			Match m = new Match(aehnlichkeitBerechnen(profile.elementAt(i)));
			report.setMatch(m);
			// Hinzufügen
			reports.add(report);
		}
		return reports;
	}

	public Vector<ProfilReport> getNotVisitedReports() throws Exception {
		Vector<Profil> profiles = profilMapper.getAll();
		Vector<Profil> results = new Vector<Profil>();
		Vector<Besuch> visits = besucheMapper.getBesuche(user);
		// Aussortieren
		for (int i = 0; i < profiles.size(); i++) {
			boolean ok = true;
			for (int o = 0; o < visits.size(); o++) {
				if (profiles.elementAt(i).getEmail().equals(
						visits.elementAt(o).getBesuchtesProfil().getEmail())) {
					ok = false;
					break;
				}
			}
			if (ok) results.add(profiles.elementAt(i));
		}
		// Reports erstellen
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		for (int i = 0; i < results.size(); i++) {
			ProfilReport report = getProfilReport(results.elementAt(i));

			// Das Match; im Vergleich zw. dem Profil des Users (nicht
			// Suchprofil!)
			Match m = new Match(aehnlichkeitBerechnen(results.elementAt(i)));
			report.setMatch(m);
			// Hinzufügen
			reports.add(report);
		}
		return reports;
	}


	
	// nach Suchprofil suchen
	public Vector<ProfilReport> getReportsBySuchprofil(Suchprofil sp) throws Exception {
		// //Aussortieren
		// Vector<Profil> profile = ProfilMapper.profilMapper().getAll();
		// }
		// //ProfilReports erzeugen
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		// for (int i = 0; i < results.size(); i++) {
		// reports.add(getReports(sp));
		// }
		return reports;
	}

	/*
	 * Ähnlichkeit: Profil vs.Profile
	 */
	public int aehnlichkeitBerechnen(Profil vergleich) throws Exception {
		int aehnlichkeit = 0;
		if (user.getRaucher().equals(vergleich.getRaucher()))
			aehnlichkeit += 10;
		// if(user.getMinGroesse() < vergleich.getKoerpergroesse() &&
		// suchprofil.getMaxGroesse() >
		// vergleich.getKoerpergroesse())aehnlichkeit += 10;
		if (user.getReligion().equals(vergleich.getReligion()))
			aehnlichkeit += 10;
		// if(suchprofil.getMinAlter())

		return aehnlichkeit;
	}

	/*
	 * Ähnlichkeit: Suchprofil vs. Profile
	 */
	public int aehnlichkeitBerechnen(Profil vergleich, Suchprofil suchprofil) throws Exception {
		int aehnlichkeit = 0;
		if (suchprofil.getRaucher().equals(vergleich.getRaucher()))
			aehnlichkeit += 10;
		if (suchprofil.getMinGroesse() < vergleich.getKoerpergroesse()
				&& suchprofil.getMaxGroesse() > vergleich.getKoerpergroesse())
			aehnlichkeit += 10;
		if (suchprofil.getReligion().equals(vergleich.getReligion()))
			aehnlichkeit += 10;
		// if(suchprofil.getMinAlter())

		return aehnlichkeit;
	}

}
