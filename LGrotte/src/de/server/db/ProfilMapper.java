package de.server.db;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import de.shared.BO.Profil;

/**
 * Die ProfilMapper verwaltet das komplette Profil mit seinen Attributen
 * 
 * @author Thomas Bauer, Enrico Popaj und Nicolai Ehrmanntraut
 *	
 * @version 1.0
 */

public class ProfilMapper {
	
	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static ProfilMapper profilMapper = null;
	
	/**
	 * Protected (geschützer) Konstruktur
	 */
	protected ProfilMapper(){	
	}
	
	/**
	 * Statische Methode vom Typ ProfilMapper
	 * 
	 * @return profilmapper
	 */
	public static ProfilMapper profilMapper(){
		if(profilMapper ==null){
			profilMapper = new ProfilMapper();
		}
		return profilMapper;
	}

	/**
	 * Gibt Profil Attribute anhand der Email aus
	 * 
	 * @param email
	 * @return p
	 */
	public Profil getProfilByEmail(String email) throws Exception {
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM profil WHERE email = '"+email+"'");
		ResultSet rs = select.executeQuery();
		Profil p = new Profil();
		while(rs.next()){
			p.setFname(rs.getString("fname"));
			p.setLname(rs.getString("lname"));
			p.setKoerpergroesse(rs.getInt("koerpergroesse"));
			p.setGeschlecht(rs.getString("geschlecht"));
			p.setHaarfarbe(rs.getString("haarfarbe"));
			p.setReligion(rs.getString("religion"));
			p.setRaucher(rs.getString("raucher"));
			p.setGeburtsdatum(rs.getDate("geburtsdatum"));
			p.setEmail(email);	
		}
		return p;
	}

	/**
	 * Gibt alle Profile aus die es gibt
	 * 
	 * @return profile
	 */
	public Vector<Profil> getAll() throws Exception {
		Vector<Profil>profile = new Vector<Profil>();
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM profil");
		ResultSet rs = select.executeQuery();
		while(rs.next()){
			Profil p = new Profil();
			p.setFname(rs.getString("fname"));
			p.setLname(rs.getString("lname"));
			p.setGeschlecht(rs.getString("geschlecht"));
			p.setHaarfarbe(rs.getString("haarfarbe"));
			p.setKoerpergroesse(rs.getInt("koerpergroesse"));
			p.setReligion(rs.getString("religion"));
			p.setRaucher(rs.getString("raucher"));
			p.setEmail(rs.getString("email"));
			p.setGeburtsdatum(rs.getDate("geburtsdatum"));
			profile.add(p);
		}
		return profile;
	}
	
	/**
	 * Gibt alle Profile gefiltert nach Geschlecht aus
	 * 
	 * @param geschlecht
	 * @return profile
	 */
	public Vector<Profil> getProfileNachGeschlecht(String geschlecht) throws Exception {
		Vector<Profil>profile = new Vector<Profil>();
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM profil WHERE geschlecht = '"+geschlecht+"'");
		ResultSet rs = select.executeQuery();
		while(rs.next()){
			Profil p = new Profil();
			p.setFname(rs.getString("fname"));
			p.setLname(rs.getString("lname"));
			p.setGeschlecht(rs.getString("geschlecht"));
			p.setHaarfarbe(rs.getString("haarfarbe"));
			p.setKoerpergroesse(rs.getInt("koerpergroesse"));
			p.setReligion(rs.getString("religion"));
			p.setRaucher(rs.getString("raucher"));
			p.setEmail(rs.getString("email"));
			profile.add(p);
		}
		return profile;
	}
	
	/**
	 * Erstellt eine Tabelle in der die Profile eingefügt werden
	 */
	public void createProfilTable() throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement
				("CREATE TABLE IF NOT EXISTS profil (email varchar(35),fname varchar(35), lname varchar(35), "
						+ "koerpergroesse int(3), geschlecht varchar(35), religion varchar(35), "
						+ "haarfarbe varchar(35), geburtsdatum date, raucher varchar(35), "
						+ "PRIMARY KEY(email))");
		create.execute();
	}
	
	/**
	 * Fügt ein neues Profil ein
	 * 
	 * @param p
	 */
	public void insertProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement
				("INSERT INTO profil (email, fname, lname, koerpergroesse, geschlecht, religion,"
						+ "haarfarbe, raucher) VALUES ('"+p.getEmail()+"','"+p.getFname()+"','"+
						p.getLname()+"',"+p.getKoerpergroesse()+",'"+p.getGeschlecht()+"','"+
						p.getReligion()+"','"+p.getHaarfarbe()+"','"+ p.getRaucher()+"')");
		insert.execute();	
	}	
	
	/**
	 * Löscht ein Profil komplett aus unserer Partnerbörse
	 * 
	 * @param email
	 */
	public void deleteProfil(String email) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
				("DELETE FROM profil WHERE email ='" + email +"'");
		delete.execute();
	}
	
	/**
	 * Aktualisiert die Attribute von einem Profil
	 * 
	 * @param p
	 */
	public void updateProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement update = (PreparedStatement) conn.prepareStatement
				("UPDATE profil SET fname = '"+p.getFname()+"', lname = '"+ p.getLname()+"',"
						+ "koerpergroesse = "+p.getKoerpergroesse()+", geschlecht = '"+p.getGeschlecht()+"',"
						+ "religion ='"+p.getReligion()+"', haarfarbe = '"+p.getHaarfarbe()+"',"
						+ "geburtsdatum = '"+p.getGeburtsdatum()+"', raucher = '"+p.getRaucher()+"' "
						+ "WHERE email='"+p.getEmail()+"'");								
		update.execute();
	}


}
