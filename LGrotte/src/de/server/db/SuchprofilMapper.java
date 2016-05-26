package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;


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
	
	public void createSuchprofilTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createSuchprofil = (PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS suchprofil(suchprofilname varchar(30), geschlecht varchar(30), suchprofilalter varchar(30), religion varchar(30), raucher varchar(30), PRIMARY KEY(suchprofilname))");
		createSuchprofil.execute();
	}
	
	
	public void insertSuchprofil (Suchprofil sp) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertSuchprofil = (PreparedStatement)con.prepareStatement(
				"INSERT INTO suchprofil(suchprofilname, geschlecht, raucher, religion, suchprofilalter,"
				+ "koerpergroesse, haarfarbe) VALUES ('"+ sp.getSuchprofilname() +"','"+ sp.getGeschlecht() +"', '"+ sp.getRaucher() + "', '" + sp.getReligion() + "', '" + sp.getSuchprofilalter() + "', '" + sp.getKoerpergroesse() + "', '" + sp.getHaarfarbe() + "')");
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
