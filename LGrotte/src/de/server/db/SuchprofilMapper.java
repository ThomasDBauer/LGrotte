package de.server.db;

import java.sql.PreparedStatement;

import com.google.cloud.sql.jdbc.Connection;

import de.shared.BO.Suchprofil;

public class SuchprofilMapper {
	
	public void createSuchprofilTable () throws Exception {
		Connection con = (Connection) DBConnection.connection();
		
		PreparedStatement createSuchprofil = (PreparedStatement) con
				.prepareStatement("CREATE TABLE IF NOT EXISTS suchprofil,"
						+"suchprofil_id INT NOT NULL AUTO_INCREMENT,"
						+"PRIMARY KEY(suchprofil_id),"
						+"geschlecht boolean(1),"
						+"geburtsdatum Date(1),"
						+"religion varchar(255),"
						+"raucher boolean(1)");
		
		createSuchprofil.execute();
		
	}
	
	
	public void insertSuchprofil (Suchprofil sp) throws Exception{
	
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertSuchprofil = (PreparedStatement)con.prepareStatement(
				"INSERT INTO suchprofil(suchprofil_id, geschlecht, raucher, religion, geburtsdatum,"
				+ "koerpergroesse, haarfarbe) VALUES ('"+sp.getId()+"','"+sp.isGeschlecht()+"'.'"+sp.getGeburtsdatum()+"','"+sp.getReligion()+"','"+sp.getKoerpergroesse()+"','"+sp.isRaucher()+"')");
		insertSuchprofil.execute();
		
}

//public void updateSuchprofil (Suchprofil sp){
//	
//}
//
//public void deleteSuchprofil (Suchprofil sp){
//	
//}
//
//public Suchprofil getSuchprofil(int profilID){
//	return null;
//}
//
//public Vector<Suchprofil> getSuchprofile(){
//	return null;
//}
}
