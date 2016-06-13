package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.google.cloud.sql.jdbc.ResultSet;

import de.shared.BO.Besuch;
import de.shared.BO.Merkzettel;

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
}
