package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;
import de.shared.BO.SuchprofilInfo;
import de.shared.RO.ProfilEigenschaft;
import de.shared.RO.ProfilInformation;

public class SuchprofilInfoMapper {

	
	private static SuchprofilInfoMapper suchprofilInfoMapper = null;

	protected SuchprofilInfoMapper() {
	}

	public static SuchprofilInfoMapper suchprofilInfoMapper() {
		if (suchprofilInfoMapper == null) {
			suchprofilInfoMapper = new SuchprofilInfoMapper();
		}

		return suchprofilInfoMapper;
	}
	
	
	public void createSuchProfilInfoTable() throws Exception {
		Connection conn = DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS suchprofil_info (suchprofilname varchar(35) NOT NULL, "
				+ "email varchar(35) NOT NULL, info_id int NOT NULL, PRIMARY KEY (suchprofilname, email, info_id), "
				+ "FOREIGN KEY(email) REFERENCES profil(email) ON UPDATE CASCADE ON DELETE RESTRICT, "
				+ "FOREIGN KEY(info_id) REFERENCES infos(info_id) ON UPDATE CASCADE ON DELETE RESTRICT,"
				+ "FOREIGN KEY (suchprofilname) REFERENCES suchprofil(suchprofilname) ON UPDATE CASCADE ON DELETE RESTRICT)");
		create.execute();
	}
	
	public void insertSuchprofilInfo(SuchprofilInfo spi) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO suchprofil_info (suchprofilname, email, info_id) VALUES "
				+ "('" + spi.getSp() + "','" + spi.getProfil() + "',"+spi.getInfo()+")");
		stmt.execute();
	}

	
	public ProfilEigenschaft getSPInfosByInfoID(int infoID) throws Exception{
		
		Connection conn = DBConnection.connection();
		
		PreparedStatement select = conn.prepareStatement("SELECT infos.value, "
				+ "eigenschaft.erlauterung FROM infos JOIN eigenschaft ON "
				+ "infos.eigenschaft_id = eigenschaft.eigenschaft_id WHERE info_id = " + infoID);
		
		ResultSet result = select.executeQuery();
		
		ProfilEigenschaft pe = new ProfilEigenschaft();

		while(result.next()){
			pe.setName(result.getString("erlauterung"));
			pe.setWert(result.getString("value"));
		}
		
		return pe;
		
	}
	
	public Vector <ProfilEigenschaft> getSuchprofilInfosByEmail(String email, String spname) throws Exception{
		Connection conn = DBConnection.connection();
		PreparedStatement select = conn.prepareStatement("SELECT info_id FROM "
				+ "suchprofil_info WHERE email = '" + email + "' AND suchprofilname = '" + spname + "'");
		Vector<ProfilEigenschaft> suchprofilinfos = new Vector<ProfilEigenschaft>();
		ResultSet result = select.executeQuery();
		while(result.next()){
			ProfilEigenschaft pi = getSPInfosByInfoID(result.getInt("info_id"));
			suchprofilinfos.add(pi);
		}
		return suchprofilinfos;
		
	}
	
}
