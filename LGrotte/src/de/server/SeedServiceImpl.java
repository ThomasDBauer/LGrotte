package de.server;

import de.client.TestService;
import de.server.db.InfoMapper;
import de.server.db.ProfilMapper;
import de.server.db.ProfilinfoMapper;
import de.server.db.Seeder;
import de.server.db.SuchprofilInfoMapper;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;
import de.shared.BO.SuchprofilInfo;
import de.shared.RO.ProfilInformation;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

//huuuuuhuuu
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SeedServiceImpl extends RemoteServiceServlet implements TestService {

	private Profil user;

	public void setUser(Profil p) {
		user = p;
	}

	// die hier geht nicht. die ruft die methode im merkzettelmapper auf:
	public void seed() throws Exception {
		Seeder seeder = new Seeder();
		seeder.init();
	}

	public Vector<Profil> getAllProfiles() throws Exception {
		return ProfilMapper.profilMapper().getAll();
	}

	public Vector<ProfilInformation> getProfilInfos() throws Exception {

		return ProfilinfoMapper.profilinfoMapper().getProfilInfosByEmail("thdobauer@gmail.com");
	}
	
	/**
	 * 
	 * TODO: insertInfo() raus aus den Service-Klassen, damit
	 * nur noch ProfilInfo und SuchprofilInfo aufgerufen werden können.
	 * 
	 */

	// ProfilInfos Hauptmethode
	public void insertProfilInfo(Info info) throws Exception {
		// Verarbeitung der Info und Auslesen der ID
		int infoID = insertInfo(info);
		// Aufbau des ProfilInfo-Objekts
		ProfilInfo pi = new ProfilInfo();
		pi.setInfoID(infoID);
		pi.setProfilEmail(user.getEmail());
		// Eintrag in die Datenbank
		ProfilinfoMapper.profilinfoMapper().insertProfilInfo(pi);
	}

	// SuchprofilInfos Hauptmethode
	public void insertSuchprofilInfo(Suchprofil sp, Info info) throws Exception {
		// Verarbeitung der Info und Auslesen der ID
		int infoID = insertInfo(info);
		// Aufbau des SuchprofilInfo-Objekts
		SuchprofilInfo spi = new SuchprofilInfo();
		spi.setInfo(infoID);
		spi.setProfil(user.getEmail());
		spi.setSp(sp.getSuchprofilname());
		// Eintrag in die DB
		SuchprofilInfoMapper.suchprofilInfoMapper().insertSuchprofilInfo(spi);
	}

	// Hilfsmethode für insertSuchprofilInfo() und ProfilInfo()
	// Checkt, ob es die Info bereits gibt. Gibt in jedem Fall
	// die InfoID zurück, damit die beiden n:m Tabellen damit
	//bestückt werden können.
	private int insertInfo(Info info) throws Exception {
		// check, ob die Info bereits besteht und Auslesen der ID
		int infoID = InfoMapper.infoMapper().getInfoIDByEigenschaftsIDAndValue(
				info.getEigenschaft(), info.getValue());
		// falls nicht, Schreiben der neuen Info und Auslesen der ID
		if (infoID == 0) {
			InfoMapper.infoMapper().insertInfo(info);
			infoID = InfoMapper.infoMapper().getInfoIDByEigenschaftsIDAndValue(
					info.getEigenschaft(), info.getValue());
		}
		// zurückgeben der ID
		return infoID;
	}

}
