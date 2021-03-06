package de.server.db;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.util.Vector;

import de.shared.BO.Suchprofil;

/**
 * Die SuchprofilMapper verwaltet das komplette Suchprofil mit seinen Attributen
 * 
 * @author Thomas Bauer, Enrico Popaj und Nicolai Ehrmanntraut
 * 
 * @version 1.0
 */

public class SuchprofilMapper {
	
	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
    private static SuchprofilMapper suchprofilMapper = null; 
	
    /**
	 * Protected (geschützer) Konstruktur
	 */
	protected SuchprofilMapper() {
	}
	
	/**
	 * Statische Methode vom Typ SuchprofilMapper
	 * 
	 * @return suchprofilmapper
	 */
	public static SuchprofilMapper suchprofilMapper() {
	if(suchprofilMapper == null) {
		suchprofilMapper = new SuchprofilMapper();
	}
	return suchprofilMapper;
	}
	
	/**
	 * Erstellt eine Tabelle in der die Suchprofile eingefügt werden
	 */
	public void createSuchprofilTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createSuchprofil = (PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS suchprofil(suchprofilname varchar(35), "
				+ "geschlecht varchar(30), minAlter int(3), maxAlter int(3), "
				+ "religion varchar(30), haarfarbe varchar(15), email varchar(35),"
				+ "raucher varchar(30), minGroesse int(3), maxGroesse int(3),"
				+ "PRIMARY KEY(suchprofilname, email), FOREIGN KEY (email)"
				+ "REFERENCES profil(email) ON DELETE CASCADE)");
		createSuchprofil.execute();
	}
	
	/**
	 * Gibt alle Suchprofile zu einem bestimmten Profil aus
	 * 
	 * @param email
	 * @return Vector<Suchprofil>
	 */
	public Vector<Suchprofil> getSuchprofileByEmail(String email) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con.prepareStatement(
				"SELECT * FROM suchprofil WHERE email = '"+email+"'");
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
	
	/**
	 * Fügt ein neues Suchrofil abhängig zu einem Profil ein
	 * 
	 * @param sp
	 */
	public void insertSuchprofil (Suchprofil sp) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertSuchprofil = (PreparedStatement)con.prepareStatement(
				"INSERT INTO suchprofil(suchprofilname, geschlecht, raucher, religion, "
				+ "minAlter, maxAlter, minGroesse, maxGroesse, haarfarbe, email) VALUES ('"+ 
				sp.getSuchprofilname() +"','" + sp.getGeschlecht() +"', '"+ 
				sp.getRaucher() + "', '" + sp.getReligion() + "', '" + sp.getMinAlter() + 
				"', '" +sp.getMaxAlter()+"','"+ sp.getMinGroesse() + "','"+ sp.getMaxGroesse() + "', '" 
				+ sp.getHaarfarbe() + "','"+sp.getProfil()+"')");
		insertSuchprofil.execute();
	}
	/**
	 * Löscht ein Suchprofil von einem Profil
	 * 
	 * @param sp
	 */
	public void deleteSuchprofil (Suchprofil sp) throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
				("DELETE FROM suchprofil WHERE suchprofilname ='" + sp.getSuchprofilname() +"'");
		delete.execute();
	}
	
	/**
	 * Gibt ein Suchprofil abhängig von seinem Suchprofilname aus
	 * 
	 * @param suchprofilname
	 * @return Suchprofil
	 */
		public Suchprofil getSuchprofiByName(String suchprofilname) throws Exception{
			Connection con = (Connection) DBConnection.connection();
			PreparedStatement select = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM suchprofil WHERE suchprofilname = '"+suchprofilname+"'");
			ResultSet result = select.executeQuery();
			Suchprofil sp = new Suchprofil();
			while(result.next()){
				sp.setGeschlecht(result.getString("geschlecht"));
				sp.setHaarfarbe(result.getString("haarfarbe"));
				sp.setMaxGroesse(result.getInt("maxGroesse"));
				sp.setMinGroesse(result.getInt("minGroesse"));
				sp.setMaxAlter(result.getInt("maxAlter"));
				sp.setMinAlter(result.getInt("minAlter"));
				sp.setRaucher(result.getString("raucher"));
				sp.setReligion(result.getString("religion"));
				sp.setSuchprofilname(result.getString("suchprofilname"));
			}
			return sp;
		}
	
		/**
		 * Aktualisiert die Attribute von einem Suchprofil
		 * 
		 * @param sp
		 */
		public void updateSuchprofil(Suchprofil sp) throws Exception {
			Connection conn = (Connection) DBConnection.connection();
			PreparedStatement update = (PreparedStatement) conn.prepareStatement(
					"UPDATE suchprofil SET geschlecht= '" + sp.getGeschlecht() + "', "
					+ "minAlter=" + sp.getMinAlter() + ", maxAlter=" + sp.getMaxAlter() + ", "
					+ "religion='" + sp.getReligion() + "', haarfarbe='" + sp.getHaarfarbe() + "', "
					+ "raucher='" + sp.getRaucher() + "', minGroesse=" + sp.getMinGroesse() + ", "
					+ "maxGroesse=" + sp.getMaxGroesse() + " WHERE "
					+ "suchprofilname='" + sp.getSuchprofilname() + "' AND email='" + sp.getProfil() + "'");								
			update.execute();
		}
		
		
}
