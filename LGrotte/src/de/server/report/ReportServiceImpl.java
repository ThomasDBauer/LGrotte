package de.server.report;

import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.BesucheMapper;
import de.server.db.EigenschaftMapper;
import de.server.db.KontaktsperreMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.SuchprofilInfoMapper;
import de.server.db.SuchprofilMapper;
import de.shared.ReportService;
import de.shared.BO.Besuch;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Kontaktsperre;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.Match;
import de.shared.RO.ProfilAttribut;
import de.shared.RO.ProfilEigenschaft;
import de.shared.RO.ProfilReport;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	private ProfilMapper profilMapper = ProfilMapper.profilMapper();
	private ProfilinfoMapper profilInfoMapper = ProfilinfoMapper.profilinfoMapper();
	private BesucheMapper besucheMapper = BesucheMapper.besucheMapper();
	private SuchprofilMapper spMapper = SuchprofilMapper.suchprofilMapper();
	private SuchprofilInfoMapper spiMapper = SuchprofilInfoMapper.suchprofilInfoMapper();
	private EigenschaftMapper eMapper = EigenschaftMapper.eigenschaftMapper();
	private KontaktsperreMapper ksMapper = KontaktsperreMapper.kontaktsperreMapper();

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
		Vector<Suchprofil> suchprofile = spMapper.getSuchprofileByEmail(user.getEmail());
		for (Suchprofil sp : suchprofile) {
			sp.setProfileigenschaften(spiMapper.getSuchprofilInfosByEmail(user.getEmail(), sp.getSuchprofilname()));
		}
		return suchprofile;
	}

	/*
	 * Erzeugt den ProfilReport f�r ein einzelnes Profil
	 */
	@SuppressWarnings("deprecation")
	public ProfilReport getProfilReport(Profil p) throws Exception {

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
		if(p.getGeburtsdatum()!=null){
			ProfilAttribut geburtsdatum = new ProfilAttribut();
			geburtsdatum.setName("Geburtsdatum");
			geburtsdatum.setWert(String.valueOf(p.getGeburtsdatum()));
			ProfilAttribut alter = new ProfilAttribut();
			alter.setName("Alter");
			alter.setWert(Integer.toString(getAlter(p.getGeburtsdatum())));
			report.addAttribut(geburtsdatum);
			report.addAttribut(alter);
		}
		report.addAttribut(geschlecht);
		report.addAttribut(haarFarbe);
		report.addAttribut(religion);
		report.addAttribut(raucher);
		report.addAttribut(koerpergroesse);
		// 2. Eigenschaften
		Vector<ProfilEigenschaft> profilinfos = profilInfoMapper.getProfilInfosByEmail(p.getEmail());
		for (int i = 0; i < profilinfos.size(); i++) {
			report.addEigenschaft(profilinfos.elementAt(i));
		}
		return report;
	}

	/*
	 * Reports zusammensetzen
	 */
	public Vector<ProfilReport> reportErstellen(Vector<Profil> results) throws Exception {
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

	/**
	 * Alle Reports
	 */
	public Vector<ProfilReport> getReports() throws Exception {
		Vector<Profil> profile = profilMapper.getAll();
		userAussortieren(profile);
		profile = aussortierenNachKontaktsperren(profile);
		return reportErstellen(profile);
	}

	/**
	 * Alle Reports nach Suchprofil
	 */
	public Vector<ProfilReport> getReports(Suchprofil sp) throws Exception {
		Vector<Profil> profile = profilMapper.getAll();
		userAussortieren(profile);
		profile = aussortierenNachKontaktsperren(profile);
		profile = aussortierenNachSP(profile, sp);
		return reportErstellen(profile);
	}

	/**
	 * nicht besuchte Profile nach Suchprofil
	 */

	public Vector<ProfilReport> getNotVisitedReports(Suchprofil sp) throws Exception {
		Vector<Profil> profiles = profilMapper.getAll();
		userAussortieren(profiles);
		profiles = aussortierenNachKontaktsperren(profiles);
		profiles = aussortierenNachSP(profiles, sp);
		profiles = besucheAussortieren(profiles);
		return reportErstellen(profiles);

	}

	/**
	 * nicht besuchte Profile ohne Filter
	 */
	public Vector<ProfilReport> getNotVisitedReports() throws Exception {
		Vector<Profil> profiles = profilMapper.getAll();
		userAussortieren(profiles);
		profiles = aussortierenNachKontaktsperren(profiles);
		profiles = besucheAussortieren(profiles);
		return reportErstellen(profiles);
	}

	/*
	 * Hilfsmethode Besuche aussortieren
	 */
	public Vector<Profil> besucheAussortieren(Vector<Profil> profiles) throws Exception {
		Vector<Profil> results = new Vector<Profil>();
		Vector<Besuch> visits = besucheMapper.getBesuche(user);
		// Aussortieren
		for (int i = 0; i < profiles.size(); i++) {
			boolean ok = true;
			for (int o = 0; o < visits.size(); o++) {
				if (profiles.elementAt(i).getEmail().equals(visits.elementAt(o).getBesuchtesProfil().getEmail())) {
					ok = false;
					break;
				}
			}
			if (ok)
				results.add(profiles.elementAt(i));
		}
		return results;
	}

	/*
	 * Hilfsmethode Aussortieren nach Suchprofil
	 */
	public Vector<Profil> aussortierenNachSP(Vector<Profil> profile, Suchprofil sp) throws Exception {
		Vector<Profil> results = new Vector<Profil>();

		// Aussortieren nach Suchprofil
		// Check von jedem Profil:
		for (int i = 0; i < profile.size(); i++) {
			boolean ok = true;
			Profil p = profile.elementAt(i);
			// Eigene Identität
			if (user.getEmail().equals(p.getEmail())) {
				ok = false;
			}
			// Geschlecht
			if (!sp.getGeschlecht().equals("Egal")) {
				if (!sp.getGeschlecht().equals(p.getGeschlecht()) || p.getGeschlecht() == null) {
					ok = false;
				}
			}
			// Raucher
			if (!sp.getRaucher().equals("Egal")) {
				if (!sp.getRaucher().equals(p.getRaucher()) || p.getRaucher() == null) {
					ok = false;
				}
			}
			// Religion
			if (!sp.getReligion().equals("Egal")) {
				if (!sp.getReligion().equals(p.getReligion())) {
					ok = false;
				}
			}
			// Haarfarbe
			if (!sp.getHaarfarbe().equals("Egal")) {
				if (!sp.getHaarfarbe().equals(p.getHaarfarbe())) {
					ok = false;
				}
			}
			//Alter
			if(sp.getMinAlter() > getAlter(p.getGeburtsdatum()) || 
					sp.getMaxAlter() < getAlter(p.getGeburtsdatum())){
				ok = false;
			}
			//Koerpergroesse
			if(sp.getMinGroesse() > p.getKoerpergroesse() ||
					sp.getMaxGroesse() < p.getKoerpergroesse()){
				ok = false;
			}

			// ProfilEigenschaften aussortieren
			Vector<ProfilEigenschaft> suchPEs = spiMapper.getSuchprofilInfosByEmail(user.getEmail(),
					sp.getSuchprofilname());
			Vector<ProfilEigenschaft> fremdPEs = profilInfoMapper.getProfilInfosByEmail(p.getEmail());
			if (suchPEs != null & suchPEs.size() != 0) {
				boolean peOK = false;
				for (int u = 0; u < suchPEs.size(); u++) {
					if (fremdPEs != null) {
						ProfilEigenschaft suchPE = suchPEs.elementAt(u);
						for (int z = 0; z < fremdPEs.size(); z++) {
							ProfilEigenschaft fremdPE = fremdPEs.elementAt(z);
							if (suchPE.getEigenschaft().getId() == fremdPE.getEigenschaft().getId()) {
								if (fremdPE.getWert().equals(suchPE.getWert())) {
									peOK = true;
								}
							}
						}
					}
				}
				if (peOK == false)
					ok = false;
			}

			// TODO Größe und Alter
			if (ok)
				results.add(p);
		}
		return results;
	}

	/*
	 * Hilfsmethode User aussortieren Hilfsmethode
	 */
	public void userAussortieren(Vector<Profil> profiles) {
		for (int i = 0; i < profiles.size(); i++) {
			Profil p = profiles.elementAt(i);
			if (p.getEmail().equals(user.getEmail()))
				profiles.remove(p);
		}
	}
	
	/*
	 *  Aussortieren von Kontaktsperren
	 */
	public Vector<Profil> aussortierenNachKontaktsperren(Vector<Profil>profile) throws Exception{
		Vector<Kontaktsperre> sperren = ksMapper.
				getKontaktsperreByOwner(user.getEmail());
		Vector<Kontaktsperre> gesperrtVon = ksMapper.
				getKontaktsperren(user.getEmail());
		//Profil für Profil..
		for(int i = 0; i < profile.size(); i++){
			//Sperren des Users selbst:
			for(int o = 0; o < sperren.size(); o++){
				if(profile.elementAt(i).getEmail().equals(
						sperren.elementAt(o).getGesperrtesProfil())){
					profile.remove(profile.elementAt(i));
					break;
				};
			}
			//Andere Profile, die unsern User gesperrt haben:
			for(int o = 0; o < gesperrtVon.size(); o++){
				if(profile.elementAt(i).getEmail().equals(
						gesperrtVon.elementAt(o).getSperrendesProfil())){
					profile.remove(profile.elementAt(i));
					break;
				};
			}
		}
		return profile;
	}

	/*
	 * Hilfsmethode Ähnlichkeit: Profil vs.Profile
	 */
	public int aehnlichkeitBerechnen(Profil vergleich) throws Exception {
		// Die Ähnlichkeit
		int aehnlichkeit = 0;

		// sinnvolle und vergleichbare Attribute
		if (user.getRaucher().equals(vergleich.getRaucher()))
			aehnlichkeit += 10;
		if (user.getReligion().equals(vergleich.getReligion()))
			aehnlichkeit += 10;

		// ProfilInfos
		Vector<ProfilEigenschaft> vergleichinfos = profilInfoMapper.getProfilInfosByEmail(vergleich.getEmail());
		Vector<ProfilEigenschaft> userinfos = profilInfoMapper.getProfilInfosByEmail(user.getEmail());
		if (vergleichinfos != null && userinfos != null) {
			for (int i = 0; i < vergleichinfos.size(); i++) {
				for (int u = 0; u < userinfos.size(); u++) {
					ProfilEigenschaft userPE = userinfos.elementAt(u);
					ProfilEigenschaft vergleichPE = vergleichinfos.elementAt(i);
					if (userPE.getEigenschaft().getId() == vergleichPE.getEigenschaft().getId()) {
						if (userPE.getWert().equals(vergleichPE.getWert())) {
							aehnlichkeit += 10;
						}
					}
				}
			}
		}
		/*
		 * die Rechnung lautet: Punkte / (zwei Attribute + Eigenschaften)
		 */
		Vector<Eigenschaft> eigenschaften = eMapper.getEigenschaften();
		return (aehnlichkeit * 10) / (eigenschaften.size() + 2);
	}

	public Vector<ProfilReport> sortierenNachAehn(Vector<ProfilReport> reports) {

		ProfilReport[] array = new ProfilReport[reports.size()];
		for (int i = 0; i < reports.size(); i++) {
			array[i] = reports.elementAt(i);
		}
		Arrays.sort(array);
		reports.clear();
		for (int i = 0; i < array.length; i++) {
			reports.add(array[i]);
		}
		return reports;
	}
	/*
	 * Hier wird das Alter anhand des Geburtsdatums berechnet.
	 */
	 public int getAlter(Date geburtsdatum) {
		 String s = geburtsdatum.toString();
		    String[] gebDaten = s.split("-");
		    Date now = new Date();
		    int nowMonth = now.getMonth() + 1;
		    int nowYear = now.getYear() + 1900;
		    int year = Integer.valueOf(gebDaten[0]);
		    int month = Integer.valueOf(gebDaten[1]);
		    int day = Integer.valueOf(gebDaten[2]);

		    int result = nowYear - year;

		    if (month > nowMonth) {
		      result--;
		    } else if (month == nowMonth) {
		      int nowDay = now.getDate();

		      if (day > nowDay) {
		        result--;
		      }
		    }
		    return result;
		}

}
