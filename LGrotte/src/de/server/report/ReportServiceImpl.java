package de.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.BesucheMapper;
import de.server.db.EigenschaftMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.SuchprofilInfoMapper;
import de.server.db.SuchprofilMapper;
import de.shared.ReportService;
import de.shared.BO.Auswahl;
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
		Vector<Suchprofil> suchprofile = SuchprofilMapper.suchprofilMapper().getSuchprofileByEmail(user.getEmail());
		for(Suchprofil sp : suchprofile){
			sp.setProfileigenschaften(
					SuchprofilInfoMapper.suchprofilInfoMapper().getSuchprofilInfosByEmail(
							user.getEmail(), sp.getSuchprofilname()));
		}
		return suchprofile;
	}

	/*
	 * Erzeugt den ProfilReport f�r ein einzelnes Profil
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
		ProfilAttribut koerpergroesse = new ProfilAttribut();
		koerpergroesse.setName("K&oumlrpergr&oumlsse");
		koerpergroesse.setWert(Integer.toString(p.getKoerpergroesse()));
		ProfilAttribut geburtsdatum = new ProfilAttribut();
		geburtsdatum.setName("Geburtsdatum");
		geburtsdatum.setWert(String.valueOf(p.getGeburtsdatum()));
		report.addAttribut(geschlecht);
		report.addAttribut(haarFarbe);
		report.addAttribut(religion);
		report.addAttribut(raucher);
		report.addAttribut(koerpergroesse);
		report.addAttribut(geburtsdatum);

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
		
		//Aussortieren nach Suchprofil
		//Check von jedem Profil:
		for (int i = 0; i < profile.size(); i++) {
			boolean ok = true;
			Profil p = profile.elementAt(i);
			//Eigene Identität
			if(user.getEmail().equals(p.getEmail())){
				ok = false;
			}
			//Geschlecht
			if(!sp.getGeschlecht().equals("egal")){
				if(!sp.getGeschlecht().equals(p.getGeschlecht())
						|| p.getGeschlecht() == null){
					ok = false;
				}
			}
			//Raucher
			if(!sp.getRaucher().equals("egal")){
				if(!sp.getRaucher().equals(p.getRaucher()) 
						|| p.getRaucher() == null){
					ok = false;
				}
			}
			//Religion
			if(!sp.getReligion().equals("egal")){
				if(!sp.getReligion().equals(p.getReligion())){
					profile.remove(p);
				}
			}
			//Haarfarbe
			if(!sp.getHaarfarbe().equals("egal")){
				if(!sp.getHaarfarbe().equals(p.getHaarfarbe())){
					profile.remove(p);
				}
			}
			//TODO Größe und Alter
			if (ok)results.add(p);
		}
		// ProfilReports erstellen
		// Speicher für ProfilReports
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		for (int i = 0; i < results.size(); i++) {
			ProfilReport report = getProfilReport(results.elementAt(i));
			// Das Match anhand des Suchprofils errechnen
			Match m = new Match(aehnlichkeitBerechnen(results.elementAt(i)));
			report.setMatch(m);
			// final den ProfilReport zu den Ergebnissen hinzuf�gen
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
			// Hinzuf�gen
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
			// Hinzuf�gen
			reports.add(report);
		}
		return reports;
	}

	/*
	 * �hnlichkeit: Profil vs.Profile
	 */
	public int aehnlichkeitBerechnen(Profil vergleich) throws Exception {
		//Die Ähnlichkeit
		int aehnlichkeit = 0;
		
		//sinnvolle und vergleichbare Attribute
		if (user.getRaucher().equals(vergleich.getRaucher()))
			aehnlichkeit += 10;
		if (user.getReligion().equals(vergleich.getReligion()))
			aehnlichkeit += 10;
		//TODO Alter mit Sedats getAlter()
		
		//ProfilInfos
		Vector<ProfilEigenschaft> vergleichinfos = 
				profilInfoMapper.getProfilInfosByEmail(vergleich.getEmail());
		Vector<ProfilEigenschaft> userinfos =
				profilInfoMapper.getProfilInfosByEmail(user.getEmail());
		if(vergleichinfos != null && userinfos != null){
			for(int i = 0; i < vergleichinfos.size(); i++){
				for(int u = 0; u < userinfos.size(); u++){
					ProfilEigenschaft userPE = userinfos.elementAt(u);
					ProfilEigenschaft vergleichPE = vergleichinfos.elementAt(i);
					if(userPE.getEigenschaft().getId() == 
							vergleichPE.getEigenschaft().getId()){
						if(userPE.getWert().equals(vergleichPE.getWert())){
							aehnlichkeit += 10;
						}
					}
				}
			}
		}
		return aehnlichkeit;
	}
}
