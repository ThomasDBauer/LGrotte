package de.server.db;

import java.sql.*;
import java.util.Vector;

import de.server.db.DBConnection;
import de.shared.BO.ProfilInfo;

public class ProfilinfoMapper {

	private static ProfilinfoMapper profilinfoMapper = null;

	protected ProfilinfoMapper() {
	}

	public static ProfilinfoMapper profilinfoMapper() {
		if (profilinfoMapper == null) {
			profilinfoMapper = new ProfilinfoMapper();
		}

		return profilinfoMapper;
	}

	public void createProfilInfo() throws Exception {
		Connection conn = DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn
				.prepareStatement("CREATE TABLE IF NOT EXISTS profil_info "
						+ "(profil_id INT NOT NULL, info_id INT NOT NULL, "
						+ "eigenschaft_id INT NOT NULL, "
						+ "PRIMARY KEY (profil_id, info_id), "
						+ "FOREIGN KEY(profil_id) REFERENCES profile(id) "
						+ "ON UPDATE CASCADE ON DELETE RESTRICT, "
						+ "FOREIGN KEY(info_id) REFERENCES infos(id) "
						+ "ON UPDATE CASCADE ON DELETE RESTRICT, "
						+ "FOREIGN KEY(eigenschaft_id) "
						+ "REFERENCES eigenschaften(id) "
						+ "ON UPDATE CASCADE ON DELETE RESTRICT)");
		create.execute();
	}

	public void insertProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = DBConnection.connection();

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO `profil_infos`(profil_id, "
					+ "eigenschaft_id, Info_id) VALUES (" + pi.getProfilID()
					+ ", " + pi.getEigenschaftID() + ", " + pi.getInfoID()
					+ ")");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = DBConnection.connection();

		try {
			Statement stmt = conn.createStatement();

			stmt.executeUpdate("UPDATE `profil_infos` SET `profi_Id`= "
					+ pi.getProfilID() + ",`eigenschaft_id`= "
					+ pi.getEigenschaftID() + ",`Info_id`= " + pi.getInfoID()
					+ " " + "WHERE `profil_id` = " + pi.getProfilID());

			/*
			 * Wenn wir das ERM so lassen, muss man über das SQL-Statement
			 * herauslesen können, zu welcher Eigenschafts-ID, Die Info-id
			 * zugehörig ist, um die info-id der richtigen Eigenschafts-id zu
			 * löschen --> das Problem liegt im zusammengesetzten Primary-Key,
			 * der nur in Kombi beider Attribute Eindeutig ist
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void deleteProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = DBConnection.connection();

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM `profil_info` WHERE `profil_id`'"
					+ pi.getProfilID() + "' AND `info-id`='" + pi.getInfoID()
					+ "'");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	//
	// public ProfilInfo getProfilInfo(int profilID){
	// //Eigentlich müsste geProfilInfo ein Vetor oder einen Array mit der
	// Anzahl aller Info-ID´s zurückgeben
	// Connection connection = DBConnection.connection();
	//
	// try{
	// Statement stmt = connection.createStatement();
	// ResultSet rs =
	// stmt.executeQuery("SELECT `Eigenschaft-id` FROM `profilinfo` WHERE `Profil-id`='"
	// + profilID + "'");
	//
	// while(rs.next()){
	// ProfilInfo pi = new ProfilInfo();
	// pi.setProfilID(profilID);
	// }
	//
	// }
	// catch(Exception e2){
	// e2.printStackTrace();
	// }
	//
	//
	// return null;
	// }

	public Vector<ProfilInfo> getProfilInfos() throws Exception {
		Connection conn = DBConnection.connection();
		Vector<ProfilInfo> profilInfos = new Vector<ProfilInfo>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT profil_id, info_id FROM `profil_infos`"
							+ "Order BY id");

			while (rs.next()) {
				ProfilInfo pi = new ProfilInfo();
				pi.setInfoID(Integer.parseInt(rs.getString("`info_id`")));
				pi.setProfilID(Integer.parseInt(rs.getString("`profil_id`")));
				profilInfos.addElement(pi);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return profilInfos;
	}
}
