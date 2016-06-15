package de.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.EigenschaftMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.SuchprofilMapper;
import de.shared.ReportService;
import de.shared.BO.Aehnlichkeitsmass;
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
	
	
	public Vector<Suchprofil> getSuchprofile() throws Exception{
		return SuchprofilMapper.suchprofilMapper().getSuchprofileByEmail(
				user.getEmail());
	}
	
	public ProfilReport getProfilReport(String email) throws Exception {
		
		//Auslesen des Profils aus der DB
		Profil p = ProfilMapper.profilMapper().getProfilByEmail(email);

		//Erstellen des Reports
		//1. Attribute:
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
		
		//2. Eigenschaften
		Vector<ProfilEigenschaft> profilinfos = ProfilinfoMapper.profilinfoMapper().
				getProfilInfosByEmail(email);

		for(int i = 0; i < profilinfos.size(); i++){
			report.addEigenschaft(profilinfos.elementAt(i));
		}
		
		//Das Match!
		Match m = new Match(aehnlichkeitBerechnen(p));
		report.setMatch(m);

		
		
		return report;
	}
	
	
	//wird aktuell aufgerufen
	public Vector<ProfilReport> getAllReports() throws Exception {
		
		Vector<Profil> profile = ProfilMapper.profilMapper().getAll();
		
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		for (int i = 0; i < profile.size(); i++) {
			reports.add(getProfilReport(profile.elementAt(i).getEmail()));		
		}
		return reports;
	}
	
	//nach Suchprofil suchen
	public Vector<ProfilReport> getReportsBySuchprofil(Suchprofil sp) throws Exception{
		
		//Aussortieren
		Vector<Profil> profile = ProfilMapper.profilMapper().getAll();
		Vector<Profil> results = new Vector<Profil>();
		
		for (int i = 0; i < profile.size(); i++) {
			Profil p = profile.elementAt(i);
			//Einziges No-Go Kriterium: Geschlecht
			if(!p.getGeschlecht().equals(sp.getGeschlecht())){
			}else{
				results.add(p);
			}
		}
		
		//ProfilReports erzeugen
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		for (int i = 0; i < results.size(); i++) {
			reports.add(getProfilReport(results.elementAt(i).getEmail()));		
		}
		return reports;
	}


	
	public int aehnlichkeitBerechnen(Profil vergleich) throws Exception{
		
		int aehnlichkeit = 0;
		
		Suchprofil suchprofil = SuchprofilMapper.suchprofilMapper().getSuchprofiByName("DickeFrauen");
		
		if(suchprofil.getRaucher().equals(vergleich.getRaucher())) aehnlichkeit += 10;
		if(suchprofil.getMinGroesse() < vergleich.getKoerpergroesse() &&
				suchprofil.getMaxGroesse() > vergleich.getKoerpergroesse())aehnlichkeit += 10;
		if(suchprofil.getReligion().equals(vergleich.getReligion()))aehnlichkeit += 10;
//		if(suchprofil.getMinAlter())
		
		return aehnlichkeit;
	}
	
	
	
}
