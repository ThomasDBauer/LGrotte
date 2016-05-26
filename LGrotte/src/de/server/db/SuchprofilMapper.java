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
			sp.setKoerpergroesse(result.getInt("koerpergroesse"));
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
	
	public void deleteSuchprofil (Suchprofil sp) throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
				("DELETE FROM suchprofil WHERE suchprofilname ='" + sp.getSuchprofilname() +"'");
		delete.execute();
	}
	

//public void updateSuchprofil (Suchprofil sp){
//	
//}
//
//
//public Suchprofil getSuchprofil(int profilID){
//	return null;
//}
//
//public Vector<Suchprofil> getSuchprofile(){
//	return null;
//}
}
