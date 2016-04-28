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
				("CREATE TABLE IF NOT EXISTS PROFIL (fname varchar(255), lname varchar(255), id int NOT NULL, PRIMARY KEY(id))");
		create.execute();
	}
	
	public void insertProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement
				("INSERT INTO PROFIL (fname, lname) VALUES (?,?)");
		insert.execute();
		
	}	
	
	// Das ist nur eine provisorische Methode
	public void deleteProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
				("DELETE PROFIL WHERE id =  ");
		delete.execute();

	}

	// Das ist nur eine provisorische Methode
	public void updateProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement update = (PreparedStatement) conn.prepareStatement
				("UPDATE PROFIL (fname, lname) WHERE id =  VALUES (?,?)");
		update.execute();

	}


}
