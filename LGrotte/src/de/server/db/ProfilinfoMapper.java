package de.server.db;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Connection;


import java.sql.ResultSet;
import java.util.Vector;

import de.server.db.DBConnection;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.ProfilInfo;
import de.shared.RO.ProfilEigenschaft;

/**
 * Die ProfilMapper zeigt auf welche Info zu welchem Profil gehört
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class ProfilinfoMapper {

	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static ProfilinfoMapper profilinfoMapper = null;

	/**
	 * Protected (geschützer) Konstruktur
	 */
	protected ProfilinfoMapper() {
	}

	/**
	 * Statische Methode vom Typ ProfilinfoMapper
	 * 
	 * @return profilinfomapper
	 */
	public static ProfilinfoMapper profilinfoMapper() {
		if (profilinfoMapper == null) {
			profilinfoMapper = new ProfilinfoMapper();
		}
		return profilinfoMapper;
	}
	
	/**
	 * Erstellt eine Tabelle in der die Primary Keys vom Profil und Info
	 * als Forgein Key eine Verbindung bilden
	 */
	public void createProfilInfo() throws Exception {
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS profil_info "
				+ "(email varchar(35) NOT NULL, info_id INT NOT NULL, " + "PRIMARY KEY (email, info_id), "
				+ "FOREIGN KEY(email) REFERENCES profil(email) " + "ON DELETE CASCADE, "
				+ "FOREIGN KEY(info_id) REFERENCES infos(info_id) " + "ON DELETE CASCADE)");
		create.execute();
	}
	
	/**
	 * Fügt die Verbindung zwischen einem Profil und einer Info ein
	 * 
	 * @param pi
	 */
	public void insertProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM profil_info WHERE email = '"+pi.getProfilEmail()+"' AND"
						+ " info_id ="+pi.getInfoID());
		ResultSet result = select.executeQuery();
		if(!result.next()){
			PreparedStatement insert = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO profil_info (email, info_id) VALUES "
							+ "('" + pi.getProfilEmail() + "'," + pi.getInfoID() + ")");
			insert.execute();
		}
	}
	
	/**
	 * Aktualisiert die Verbidung zwischen einem Profil und einer Info
	 * 
	 * @param pi
	 */
	public void updateProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = (Connection)DBConnection.connection();
		try {
			PreparedStatement stmt = (PreparedStatement) conn.createStatement();

			stmt.executeUpdate("UPDATE profil_infos SET email= '" + pi.getProfilEmail() + "'," + "info_id = "
					+ pi.getInfoID() + "WHERE email = " + pi.getProfilEmail());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Löscht die Verbindung zwischen einem Profil und einer Info
	 * 
	 * @param pi
	 */
	public void deleteProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM profil_info WHERE email = '" + pi.getProfilEmail() + "' AND info_id="
							+ pi.getInfoID() + "");
		stmt.execute();
	}
	
	/**
	 * Löscht die Verbindung zwischen ALLEN Infos zu einem Profil
	 * 
	 * @param usermail
	 */
	public void deleteAllInfos(String usermail) throws Exception{
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM profil_info WHERE email = '" + usermail + "'");
		stmt.execute();
	}
	
	/**
	 * Gibt alle Infos zu einem Profil aus
	 * 
	 * @param email
	 * @return profilinfos
	 */
	public Vector <ProfilEigenschaft> getProfilInfosByEmail(String email) throws Exception{
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement("SELECT info_id FROM "
				+ "profil_info WHERE email = '" + email + "'");
		Vector<ProfilEigenschaft>profilinfos = new Vector<ProfilEigenschaft>();
		ResultSet result = select.executeQuery();
		while(result.next()){
			ProfilEigenschaft pi = getInfosForProfil(result.getInt("info_id"));
			profilinfos.add(pi);
		}
		return profilinfos;
	}
	
	/**
	 * Gibt alle Infos mit zugehöriger Eigenschaft zu eine Profil aus
	 * 
	 * @param infoID
	 * @return pe
	 */
	private ProfilEigenschaft getInfosForProfil(int infoID) throws Exception{
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement("SELECT infos.value, "
				+ "infos.info_id, eigenschaft.erlauterung, eigenschaft.auswahl, eigenschaft.eigenschaft_id "
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
	
}
