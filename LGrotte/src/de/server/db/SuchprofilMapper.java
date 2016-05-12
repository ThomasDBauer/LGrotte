package de.server.db;

import java.sql.PreparedStatement;

import com.google.cloud.sql.jdbc.Connection;

import de.shared.BO.Suchprofil;

public class SuchprofilMapper {
	public void insertSuchprofil (Suchprofil sp) throws Exception{
	
		
		/**
		 * Unser Suchprofil benötigt noch..
		 * - eine ID
		 * - ein Geschlecht (für sp.getGeschlecht)
		 */
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertSuchprofil = (PreparedStatement)con.prepareStatement(
				"INSERT INTO suchprofil(suchprofil_id, geschlecht, raucher, religion, geburtsdatum,"
				+ "koerpergroesse, haarfarbe) VALUES ('"+sp.getGeburtsdatum()+"','"+sp.getKoerpergroesse()+"'.'"+sp.getHaarfarbe()+"','"+sp.getReligion()+"')");
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
