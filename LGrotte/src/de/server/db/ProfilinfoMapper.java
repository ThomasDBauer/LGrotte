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
import de.shared.RO.ProfilAttribut;
import de.shared.RO.ProfilEigenschaft;
import de.shared.RO.ProfilInformation;

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
				+ "(email varchar(35) NOT NULL, info_id INT NOT NULL, " + "PRIMARY KEY (email, info_id), "
				+ "FOREIGN KEY(email) REFERENCES profil(email) " + "ON UPDATE CASCADE ON DELETE RESTRICT, "
				+ "FOREIGN KEY(info_id) REFERENCES infos(info_id) " + "ON UPDATE CASCADE ON DELETE RESTRICT)");
		create.execute();
	}

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

			stmt.executeUpdate("UPDATE profil_infos SET email= '" + pi.getProfilEmail() + "'," + "info_id = "
					+ pi.getInfoID() + "WHERE email = " + pi.getProfilEmail());

			/*
			 * Wenn wir das ERM so lassen, muss man �ber das SQL-Statement
			 * herauslesen k�nnen, zu welcher Eigenschafts-ID, Die Info-id
			 * zugeh�rig ist, um die info-id der richtigen Eigenschafts-id zu
			 * l�schen --> das Problem liegt im zusammengesetzten Primary-Key,
			 * der nur in Kombi beider Attribute Eindeutig ist
			 */
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void deleteProfilInfo(ProfilInfo pi) throws Exception {
		Connection conn = DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM profil_info WHERE email = '" + pi.getProfilEmail() + "' AND info_id="
							+ pi.getInfoID() + "");
		stmt.execute();
	}
	
	
	public Vector <ProfilEigenschaft> getProfilInfosByEmail(String email) throws Exception{
		
		Connection conn = DBConnection.connection();
		
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
	
	
	private ProfilEigenschaft getInfosForProfil(int infoID) throws Exception{
		
		Connection conn = DBConnection.connection();
		
		PreparedStatement select = (PreparedStatement) conn.prepareStatement("SELECT infos.value, "
				+ "infos.info_id, eigenschaft.erlauterung, eigenschaft.eigenschaft_id "
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

			pe.setInfo(info);
			pe.setEigenschaft(eigenschaft);
		}
		
		return pe;
		
	}
	
}
