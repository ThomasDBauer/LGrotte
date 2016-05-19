package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		PreparedStatement create = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS profil_info "
				+ "(profil varchar(35) NOT NULL, info_id INT NOT NULL, " + "PRIMARY KEY (profil, info_id), "
				+ "FOREIGN KEY(profil) REFERENCES profil(email) " + "ON UPDATE CASCADE ON DELETE RESTRICT, "
				+ "FOREIGN KEY(info_id) REFERENCES infos(id) " + "ON UPDATE CASCADE ON DELETE RESTRICT)");
		create.execute();
	}

	public void insertProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO profil_info (profil, " + " Info_id) VALUES ('" 
						+ pi.getProfilEmail() + "'" + ", " + pi.getInfoID() + ")");
		stmt.execute();
	}

	// onnection conn = (Connection) DBConnection.connection();
	// PreparedStatement insert = (PreparedStatement) conn.prepareStatement
	// ("INSERT INTO PROFIL (email, fname, lname, koerpergroesse, geschlecht,
	// religion,"
	// + "haarfarbe, geburtsdatum, raucher) VALUES
	// ('"+p.getEmail()+"','"+p.getFname()+"','"+
	// p.getLname()+"',"+p.getKoerpergroesse()+",'"+p.getGeschlecht()+"','"+
	// p.getReligion()+"','"+p.getHaarfarbe()+"','"+p.getGeburtsdatum()+"','"+
	// p.getRaucher()+"')");
	// insert.execute();

	public void updateProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = DBConnection.connection();

		try {
			PreparedStatement stmt = (PreparedStatement) conn.createStatement();

			stmt.executeUpdate("UPDATE profil_infos SET profil= '" + pi.getProfilEmail() + "'," + "info_id = "
					+ pi.getInfoID() + "WHERE profil = " + pi.getProfilEmail());

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
			PreparedStatement stmt = (PreparedStatement) conn.createStatement();
			stmt.executeUpdate("DELETE FROM profil_info WHERE profil = '" + pi.getProfilEmail() + "' AND info-id="
					+ pi.getInfoID());
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
	// stmt.executeQuery("SELECT `Eigenschaft-id` FROM `profilinfo` WHERE
	// `Profil-id`='"
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
			PreparedStatement stmt = (PreparedStatement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT profil, info_id FROM profil_info" + "Order BY id");

			while (rs.next()) {
				ProfilInfo pi = new ProfilInfo();
				pi.setInfoID(Integer.parseInt(rs.getString("info_id")));
				pi.setProfilEmail(rs.getString("profil"));
				profilInfos.addElement(pi);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return profilInfos;
	}
}
