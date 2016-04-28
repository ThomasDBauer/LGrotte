package de.server.db;

import java.sql.PreparedStatement;
import java.util.Vector;

import com.google.cloud.sql.jdbc.Connection;

import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;

public class ProfilMapper {
	ProfilMapper profilMapper;

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

	public void deleteProfil(Profil p) {
		// delete p
	}

	public void updateProfil(Profil p) {
		// update p
	}
	
	
	public void insertProfil(Profil p) throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn
				.prepareStatement("INSERT INTO PROFIL (fname, lname) VALUES (?,?)");
		insert.execute();
		try {
			insert.close();
		} catch (Exception e) {
			/* ignored */ }
		try {
			conn.close();
		} catch (Exception e) {
			/* ignored */ }

	}

	public void createProfil(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS PROFIL (fname varchar(255), lname varchar(255), PRIMARY KEY(fname))");
		create.execute();
		try {
			create.close();
		} catch (Exception e) {
			/* ignored */ }
		try {
			conn.close();
		} catch (Exception e) {
			/* ignored */ }

	}


}
