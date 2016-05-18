package de.server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.Connection;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;

public class ProfilMapper {
	
	
	private static ProfilMapper profilMapper = null;
	
	protected ProfilMapper(){
		
	}
	
	public static ProfilMapper profilMapper(){
		if(profilMapper ==null){
			profilMapper = new ProfilMapper();
		}
		return profilMapper;
	}

	// @param googleID
	public Profil getProfilByID(int id) throws Exception {
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT fname, lname, geschlecht, haarfarbe, religion, raucher "
				+ "FROM PROFIL WHERE id = " + id);
		ResultSet rs = select.executeQuery();
		Profil p = new Profil();
		while(rs.next()){
			p.setFname(rs.getString("fname"));
			p.setLname(rs.getString("lname"));
			p.setGeschlecht(rs.getString("geschlecht"));
			p.setHaarfarbe(rs.getString("haarfarbe"));
			p.setReligion(rs.getString("religion"));
			p.setRaucher(rs.getString("raucher"));
			
			
		}
		return p;
	}

	public Vector<Profil> getBySuchprofil(Suchprofil sp) {
		// select profil.eigenschaften WHERE values = sp
		return null;
	}

	public Vector<Profil> getAll() throws Exception {
		Vector<Profil>profile = new Vector<Profil>();
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM PROFIL");
		ResultSet rs = select.executeQuery();
		while(rs.next()){
			Profil p = new Profil();
			p.setFname(rs.getString("fname"));
			p.setLname(rs.getString("lname"));
			p.setGeschlecht(rs.getString("geschlecht"));
			p.setHaarfarbe(rs.getString("haarfarbe"));
			p.setId(rs.getInt("id"));
			p.setKoerpergroesse(rs.getInt("koerpergroesse"));
			p.setReligion(rs.getString("religion"));
			p.setRaucher(rs.getString("raucher"));
			profile.add(p);
		}
		
		return profile;
		
	}

	public void createProfilTable() throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement
				("CREATE TABLE IF NOT EXISTS PROFIL (fname varchar(35), lname varchar(35), "
						+ "koerpergroesse int(3), geschlecht varchar(35), religion varchar(35), "
						+ "haarfarbe varchar(35), geburtsdatum date, raucher varchar(35), "
						+ "id int(10) NOT NULL AUTO_INCREMENT, PRIMARY KEY(id))");
		create.execute();
	}
	
	public void insertProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement
				("INSERT INTO PROFIL (fname, lname, koerpergroesse, geschlecht, religion,"
						+ "haarfarbe, geburtsdatum, raucher) VALUES ('"+p.getFname()+"','"+
						p.getLname()+"',"+p.getKoerpergroesse()+",'"+p.getGeschlecht()+"','"+
						p.getReligion()+"','"+p.getHaarfarbe()+"','"+p.getGeburtsdatum()+"','"+
						p.getRaucher()+"')");
		insert.execute();
		
	}	
	
	public void deleteProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
				("DELETE FROM PROFIL WHERE id =" + p.getId()+"");
		delete.execute();

	}
	
	public void updateProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement update = (PreparedStatement) conn.prepareStatement
				("UPDATE PROFIL SET fname = '"+p.getFname()+"', lname = '"+ p.getLname()+"',"
						+ "geburtsdatum = '"+p.getGeburtsdatum()+"', geschlecht = '"+p.getGeschlecht()+"',"
						+ "haarfarbe ='"+p.getHaarfarbe()+"', religion = '"+p.getReligion()+"',"
						+ "raucher = '"+p.getRaucher()+"', koerpergroesse = '"+p.getKoerpergroesse()+"' "
						+ "WHERE id='"+p.getId()+"'");								
		update.execute();

	}


}
