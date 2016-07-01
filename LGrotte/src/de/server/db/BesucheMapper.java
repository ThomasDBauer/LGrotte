package de.server.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


import de.shared.BO.Besuch;
import de.shared.BO.Profil;

/**
 * Die BesucheMapper ist dafür zuständig um Profile als besucht zu merken
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class BesucheMapper {
	
	private static BesucheMapper besucheMapper = null;
	
	protected BesucheMapper(){
		
	}
	
	public static BesucheMapper besucheMapper(){
		if(besucheMapper ==null){
			besucheMapper = new BesucheMapper();
		}
		return besucheMapper;
	}
	
	// Erstellt eine Tabelle für Profile die schon besucht worden
	public void createBesucheTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) con
				.prepareStatement("CREATE TABLE IF NOT EXISTS besuche(besuchtesProfil varchar(45), "
						+ "besuchendesProfil varchar(45), PRIMARY KEY("
						+ "besuchtesProfil, besuchendesProfil), FOREIGN KEY(besuchtesProfil) "
						+ "REFERENCES profil(email), FOREIGN KEY (besuchendesProfil) "
						+ "REFERENCES profil(email))");
		create.execute();
	}
	
	// Fügt besuchte Profile in die Tabelle 
	public void insertBesuch(Besuch b) throws Exception {
		if(!besuchExists(b)){
			Connection con = (Connection) DBConnection.connection();
			PreparedStatement insert = (PreparedStatement) con
					.prepareStatement("INSERT INTO besuche(besuchendesProfil, besuchtesProfil) "
							+ "VALUES ('"+b.getBesuchendesProfil().getEmail()+"',"
							+ "'"+b.getBesuchtesProfil().getEmail()+"')");
			insert.execute();
		}
	}
	
	// Filtert nach Profilen die schon besucht worden
	private boolean besuchExists(Besuch b) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = con.prepareStatement("SELECT * FROM besuche WHERE "
				+ "besuchendesProfil = '" +b.getBesuchendesProfil().getEmail()+"' AND "
						+ "besuchtesProfil ='" +b.getBesuchtesProfil().getEmail()+"'");
		ResultSet result = (ResultSet) select.executeQuery();
		if(result.next()){
			return true;
		}else{
			return false;
		}
	}
	
	// Liest besuchte Profile aus der Datenbank
	public Vector<Besuch> getBesuche(Profil user) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = con.prepareStatement("SELECT * FROM besuche WHERE "
				+ "besuchendesProfil = '" +user.getEmail()+"'");
		ResultSet result = (ResultSet) select.executeQuery();
		
		Vector<Besuch> besuche = new Vector<Besuch>();
		while(result.next()){
			Besuch b = new Besuch();
			b.setBesuchendesProfil(user);
			Profil p = new Profil();
			p.setEmail(result.getString("besuchtesProfil"));
			b.setBesuchtesProfil(p);
			besuche.add(b);
		}
		return besuche;
	}
}
