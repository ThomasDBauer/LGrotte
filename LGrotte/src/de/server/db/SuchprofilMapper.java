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
				"CREATE TABLE IF NOT EXISTS suchprofil(suchprofilname varchar(30), "
				+ "geschlecht varchar(30), minAlter int(3), maxAlter int(3), "
				+ "religion varchar(30), haarfarbe varchar(15), profil varchar(35),"
				+ "raucher varchar(30), koerpergroesse int(3),"
				+ "PRIMARY KEY(suchprofilname, profil), FOREIGN KEY (profil)"
				+ "REFERENCES profil(email) ON UPDATE CASCADE ON DELETE RESTRICT)");
		createSuchprofil.execute();
	}
	
	
	public void insertSuchprofil (Suchprofil sp) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertSuchprofil = (PreparedStatement)con.prepareStatement(
				"INSERT INTO suchprofil(suchprofilname, geschlecht, raucher, religion, "
				+ "minAlter, maxAlter, koerpergroesse, haarfarbe, profil) VALUES ('"+ 
				sp.getSuchprofilname() +"','" + sp.getGeschlecht() +"', '"+ 
				sp.getRaucher() + "', '" + sp.getReligion() + "', '" + sp.getMinAlter() + 
				"', '" +sp.getMaxAlter()+"','"+ sp.getKoerpergroesse() + "', '" 
				+ sp.getHaarfarbe() + "','"+sp.getProfil()+"')");
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
