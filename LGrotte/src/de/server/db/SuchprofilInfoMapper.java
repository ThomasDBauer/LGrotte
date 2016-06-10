package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.shared.BO.ProfilInfo;
import de.shared.RO.SuchprofilInformation;

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
	
	public void insertSuchprofilInfo(SuchprofilInformation spi) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO suchprofil_info (suchprofilname, email, info_id) VALUES "
				+ "('" + spi.getProfil() + "','" + spi.getProfil() + "',"+spi.getInfo()+")");
		stmt.execute();
	}
	
}
