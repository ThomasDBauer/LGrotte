package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.shared.BO.Suchprofil;

public class SuchprofilMapper {
	
    private static SuchprofilMapper suchprofilMapper = null; 
	
	protected SuchprofilMapper() {
	}
	
	public static SuchprofilMapper suchprofilMapper() {
	if(suchprofilMapper == null) {
		suchprofilMapper = new SuchprofilMapper();
	}
	return suchprofilMapper;
	}
	
	// Tabelle erstellen
	public void createSuchprofilTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createSuchprofil = (PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS suchprofil(suchprofilname varchar(30), "
				+ "geschlecht varchar(30), minAlter int(3), maxAlter int(3), "
				+ "religion varchar(30), haarfarbe varchar(15), profil varchar(35),"
				+ "raucher varchar(30), minGroesse int(3), maxGroesse int(3),"
				+ "PRIMARY KEY(suchprofilname, profil), FOREIGN KEY (profil)"
				+ "REFERENCES profil(email) ON UPDATE CASCADE ON DELETE RESTRICT)");
		createSuchprofil.execute();
	}
	
	// Suchprofil name anzeigen
	public Vector<Suchprofil> getSuchprofileByEmail(String email) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con.prepareStatement(
				"SELECT * FROM suchprofil WHERE profil = '"+email+"'");
		ResultSet result = select.executeQuery();
		
		Vector<Suchprofil> suchprofile = new Vector<Suchprofil>();
		
		while(result.next()){
			Suchprofil sp = new Suchprofil();
			sp.setGeschlecht(result.getString("geschlecht"));
			sp.setHaarfarbe(result.getString("haarfarbe"));
			sp.setMaxGroesse(result.getInt("maxGroesse"));
			sp.setMinGroesse(result.getInt("minGroesse"));
			sp.setMaxAlter(result.getInt("maxAlter"));
			sp.setMinAlter(result.getInt("minAlter"));
			sp.setProfil(email);
			sp.setRaucher(result.getString("raucher"));
			sp.setReligion(result.getString("religion"));
			sp.setSuchprofilname(result.getString("suchprofilname"));
			suchprofile.add(sp);
		}
		return suchprofile;
	}
	
	// Suchprofil einfügen
	public void insertSuchprofil (Suchprofil sp) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertSuchprofil = (PreparedStatement)con.prepareStatement(
				"INSERT INTO suchprofil(suchprofilname, geschlecht, raucher, religion, "
				+ "minAlter, maxAlter, maxGroesse, minGroesse, haarfarbe, profil) VALUES ('"+ 
				sp.getSuchprofilname() +"','" + sp.getGeschlecht() +"', '"+ 
				sp.getRaucher() + "', '" + sp.getReligion() + "', '" + sp.getMinAlter() + 
				"', '" +sp.getMaxAlter()+"','"+ sp.getMinGroesse() + "','"+ sp.getMaxGroesse() + "', '" 
				+ sp.getHaarfarbe() + "','"+sp.getProfil()+"')");
		insertSuchprofil.execute();
	}
	
	// Suchprofil löschen
	public void deleteSuchprofil (Suchprofil sp) throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
				("DELETE FROM suchprofil WHERE suchprofilname ='" + sp.getSuchprofilname() +"'");
		delete.execute();
	}
	
	// Komplettes Suchprofil anzeigen
		public Vector<Suchprofil> getSuchprofiByName(String suchprofilname) throws Exception{
			Connection con = (Connection) DBConnection.connection();
			PreparedStatement select = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM suchprofil WHERE suchprofilname = '"+suchprofilname+"'");
			ResultSet result = select.executeQuery();
			
			Vector<Suchprofil> suchprofil = new Vector<Suchprofil>();
			
			while(result.next()){
				Suchprofil sp = new Suchprofil();
				sp.setGeschlecht(result.getString("geschlecht"));
				sp.setHaarfarbe(result.getString("haarfarbe"));
				sp.setMaxGroesse(result.getInt("maxGroesse"));
				sp.setMinGroesse(result.getInt("minGroesse"));
				sp.setMaxAlter(result.getInt("maxAlter"));
				sp.setMinAlter(result.getInt("minAlter"));
				sp.setRaucher(result.getString("raucher"));
				sp.setReligion(result.getString("religion"));
				sp.setSuchprofilname(result.getString("suchprofilname"));
				suchprofil.add(sp);
			}
			return suchprofil;
		}
	
		//Suchprofil bearbeiten
		public void updateSuchprofil(Suchprofil sp) throws Exception {
			Connection conn = (Connection) DBConnection.connection();
			PreparedStatement update = (PreparedStatement) conn.prepareStatement(
					"UPDATE suchprofil SET geschlecht= '" + sp.getGeschlecht() + "', "
					+ "minAlter=" + sp.getMinAlter() + ", maxAlter=" + sp.getMaxAlter() + ", "
					+ "religion='" + sp.getReligion() + "', haarfarbe='" + sp.getHaarfarbe() + "', "
					+ "raucher='" + sp.getRaucher() + "', minGroesse=" + sp.getMinGroesse() + ", "
					+ "maxGroesse=" + sp.getMaxGroesse() + " WHERE "
					+ "suchprofilname='" + sp.getSuchprofilname() + "'");								
			update.execute();
		}
}
