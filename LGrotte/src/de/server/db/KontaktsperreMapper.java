package de.server.db;



import java.sql.Connection;
import java.sql.PreparedStatement;

import de.shared.BO.Profil;



public class KontaktsperreMapper {
	
	private static KontaktsperreMapper kontaktsperreMapper = null;
	
	protected KontaktsperreMapper() {
		
	}
	
	public static KontaktsperreMapper kontaktsperreMapper() {
		if (kontaktsperreMapper == null) {
			kontaktsperreMapper = new KontaktsperreMapper();
		}
		return kontaktsperreMapper;	
	}
	
	public void createKontaktsperreTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createKontaktsperre = (PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS kontaktsperre(gesperrtesProfil varchar(255), "
				+ "profil_id int, PRIMARY KEY(gesperrtesProfil), FOREIGN KEY(profil_id) REFERENCES profil(id))");
		createKontaktsperre.execute();
	}
	
	public void insertKontaktsperre(Profil profil) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertKontaktsperre = (PreparedStatement) con.prepareStatement(
				"INSERT INTO kontaktsperre(gesperrtesProfil) VALUES ('" + profil.getId() + "')");
		insertKontaktsperre.execute();
	}
	
	public void deleteKontaktsperre(Profil profil) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteKontaktsperre = (PreparedStatement) con.prepareStatement(
				"DELETE FROM kontaktsperre WHERE gesperrtesProfil='" + profil.getId() + "'");
		deleteKontaktsperre.execute();
	}
	
	
}
