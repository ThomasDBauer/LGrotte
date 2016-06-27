package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
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
				+ "FOREIGN KEY(info_id) REFERENCES infos(info_id) ON DELETE RESTRICT,"
				+ "FOREIGN KEY (suchprofilname) REFERENCES suchprofil(suchprofilname) ON UPDATE RESTRICT)");
		create.execute();
	}
	
	public void insertSuchprofilInfo(SuchprofilInfo spi) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO suchprofil_info (suchprofilname, email, info_id) VALUES "
				+ "('" + spi.getSp().getSuchprofilname() + "','" + spi.getProfil().getEmail() + "',"+spi.getInfo().getId()+")");
		stmt.execute();
	}

	
	public ProfilEigenschaft getSPInfosByInfoID(int infoID) throws Exception{
		
		Connection conn = DBConnection.connection();
		
		PreparedStatement select = conn.prepareStatement("SELECT infos.value, "
				+ "infos.info_id, eigenschaft.auswahl, eigenschaft.erlauterung, eigenschaft.eigenschaft_id "
				+ "FROM infos JOIN eigenschaft ON infos.eigenschaft_id = "
				+ "eigenschaft.eigenschaft_id WHERE info_id = " + infoID);
		
		ResultSet result = select.executeQuery();
		
		ProfilEigenschaft pe = new ProfilEigenschaft();

		while(result.next()){

			Info info = new Info();
			info.setEigenschaft(result.getInt("eigenschaft_id"));
			info.setId(result.getInt("info_id"));
			info.setValue(result.getString("value"));
			
			Eigenschaft eigenschaft = new Eigenschaft();
			eigenschaft.setErlaeuterung(result.getString("erlauterung"));
			eigenschaft.setId(result.getInt("eigenschaft_id"));
			eigenschaft.setAuswahl(result.getInt("auswahl"));

			pe.setInfo(info);
			pe.setEigenschaft(eigenschaft);
		}
		
		return pe;
	}
	
	public void deleteSuchprofilInfo(SuchprofilInfo spi) throws Exception{
		Connection conn = DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM suchprofil_info WHERE email = '" + spi.getProfil().getEmail() + 
				"' AND info_id="+ spi.getInfo().getId() + " "
						+ "AND suchprofilname='" +spi.getSp().getSuchprofilname()+"'");
		stmt.execute();
	}
	
	public void deleteAllSuchprofilInfos(Suchprofil sp, Profil user) throws Exception{
		Connection conn = DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM suchprofil_info WHERE suchprofilname = '" + sp.getSuchprofilname() + "' "
						+ "AND email = '" + user.getEmail() + "'");
		stmt.execute();
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
