package de.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.server.db.EigenschaftMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.shared.ReportService;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
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
	
	public ProfilReport getProfilReport(String email) throws Exception {

		Profil p = ProfilMapper.profilMapper().getProfilByEmail(email);

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

		// ProfilAttribut koerpergroesse = new ProfilAttribut();
		// koerpergroesse.setName("Körpergröße");
		// koerpergroesse.setWert(p.getKoerpergroesse());

		// ProfilAttribut geburtsdatum = new ProfilAttribut();
		// geburtsdatum.setName("Geburtsdatum");
		// geburtsdatum.setWert(p.getGeburtsdatum().toString());

		report.addAttribut(geschlecht);
		report.addAttribut(haarFarbe);
		report.addAttribut(religion);
		report.addAttribut(raucher);
		
		
		Vector<ProfilEigenschaft> profilinfos = ProfilinfoMapper.profilinfoMapper().
				getProfilInfosByEmail(email);
		
		
		for(int i = 0; i < profilinfos.size(); i++){
			report.addEigenschaft(profilinfos.elementAt(i));
		}
		
//		
//		
//		if(profilinfos != null){
//			for(int i = 0; i < profilinfos.size(); i++){
//				ProfilEigenschaft pe = new ProfilEigenschaft();
//				Info info = new Info();
//				info.setValue(profilinfos.elementAt(i).getWert());
//				Eigenschaft e = new Eigenschaft();
//				e.setErlaeuterung(profilinfos.elementAt(i).getName());
//				pe.setEigenschaft(e);
//				pe.setInfo(info);
//				report.addEigenschaft(pe);
//			}
//		}
		
	
		return report;

	}
	
	public Vector<ProfilReport> getAllReports() throws Exception {
		
		Vector<Profil> profile = ProfilMapper.profilMapper().getAll();
		Vector<ProfilReport> reports = new Vector<ProfilReport>();
		for (int i = 0; i < profile.size(); i++) {
			reports.add(getProfilReport(profile.elementAt(i).getEmail()));		
		}
		return reports;
	}
}
