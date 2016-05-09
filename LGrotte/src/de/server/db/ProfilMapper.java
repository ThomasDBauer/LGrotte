package de.server.db;

import java.sql.PreparedStatement;
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
	public Profil getProfilByGoogleID() {
		return null;
	}

	public Vector<Profil> getBySuchprofil(Suchprofil sp) {
		// select profil.eigenschaften WHERE values = sp
		return null;
	}

	public Vector<Profil> getAll() {
		return null;
	}

	public void createProfilTable() throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement
				("CREATE TABLE IF NOT EXISTS PROFIL (fname varchar(35), lname varchar(35), "
						+ "koerpergroesse int(3), geschlecht varchar(35), religion varchar(35), "
						+ "haarfarbe varchar(35), geburtsdatum int(8), raucher varchar(35), "
						+ "id int(10) NOT NULL AUTO_INCREMENT, PRIMARY KEY(id))");
		create.execute();
	}
	
	public void insertProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement
				("INSERT INTO PROFIL (fname, lname, koerpergroesse, geschlecht, religion,"
						+ "haarfarbe, geburtsdatum, raucher) VALUES ('"+p.getFname()+"','"+
						p.getLname()+"',"+p.getKoerpergroesse()+",'"+p.getGeschlecht()+"','"+
						p.getReligion()+"','"+p.getHaarfarbe()+"',"+15111985+",'"+p.getRaucher()+"')");
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
						+ "geburtsdatum = '"+19214321+"', geschlecht = '"+p.getGeschlecht()+"',"
						+ "haarfarbe ='"+p.getHaarfarbe()+"', religion = '"+p.getReligion()+"',"
						+ "raucher = '"+p.getRaucher()+"', koerpergroesse = '"+p.getKoerpergroesse()+"' "
						+ "WHERE id='"+p.getId()+"'");								
		update.execute();

	}


}
