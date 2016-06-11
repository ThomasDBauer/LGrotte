package de.server.db;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import de.shared.BO.Kontaktsperre;
import de.shared.BO.Merkzettel;
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
				"CREATE TABLE IF NOT EXISTS kontaktsperre(gesperrtesProfil varchar(45), "
				+ "sperrendesProfil varchar(45), PRIMARY KEY(gesperrtesProfil, sperrendesProfil),"
				+ "FOREIGN KEY(gesperrtesProfil) REFERENCES profil(email), "
				+ "FOREIGN KEY (sperrendesProfil) REFERENCES profil(email))");
		createKontaktsperre.execute();
	}
	
	public void insertKontaktsperre(Kontaktsperre k) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertKontaktsperre = (PreparedStatement) con.prepareStatement(
				"INSERT INTO kontaktsperre(gesperrtesProfil, sperrendesProfil) VALUES ("
				+ "'" + k.getGesperrtesProfil() + "','"+k.getSperrendesProfil()+"')");
		insertKontaktsperre.execute();
	}
	
	public Vector<Kontaktsperre> getKontaktsperreByOwner(String email) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con.prepareStatement(
				"SELECT gesperrtesProfil FROM kontaktsperre WHERE sperrendesProfil="
				+ "'" + email + "'");
		ResultSet result = select.executeQuery();
		Vector<Kontaktsperre> kontaktsperren = new Vector<Kontaktsperre>();
		while(result.next()){
			Kontaktsperre k = new Kontaktsperre();
			k.setGesperrtesProfil(result.getString("gesperrtesProfil"));
			k.setSperrendesProfil(email);
			kontaktsperren.add(k);
		}
		return kontaktsperren;
	}
	
	public Vector<Profil> getKontaktsperreProfileByOwner(String email)
			throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con
				.prepareStatement("SELECT * FROM profil INNER JOIN kontaktsperre ON profil.email = kontaktsperre.gesperrtesProfil WHERE kontaktsperre.sperrendesProfil="
						+ "'" + email + "'");
		ResultSet result = select.executeQuery();
		Vector<Profil> merkzettelProfile = new Vector<Profil>();
		while (result.next()) {
			Profil p = new Profil();
			p.setFname(result.getString("fname"));
			p.setLname(result.getString("lname"));
			merkzettelProfile.add(p);
		}
		return merkzettelProfile;
	}
	
//	
//	public void deleteKontaktsperre(Profil profil) throws Exception {
//		Connection con = (Connection) DBConnection.connection();
//		PreparedStatement deleteKontaktsperre = (PreparedStatement) con.prepareStatement(
//				"DELETE FROM kontaktsperre WHERE gesperrtesProfil='" + profil.getId() + "'");
//		deleteKontaktsperre.execute();
//	}
	
	
}
