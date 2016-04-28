package de.server.db;

import java.util.Vector;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Profil;

public class EigenschaftMapper {

	EigenschaftMapper eigenschaftMapper;

	public void createEigenschaft(Eigenschaft e) throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn
				.prepareStatement("INSERT INTO PROFIL (fname, lname) VALUES (?,?)");
		create.execute();
		try {
			create.close();
		} catch (Exception ex) {
			/* ignored */ }
		try {
			conn.close();
		} catch (Exception ex) {
			/* ignored */ }
		

	}

	public void insertEigenschaft(Eigenschaft e) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn
				.prepareStatement("INSERT INTO EIGENSCHAFT (fname, lname) VALUES (?,?)");
		insert.execute();
		try {
			insert.close();
		} catch (Exception ex) {
			/* ignored */ }
		try {
			conn.close();
		} catch (Exception ex) {
			/* ignored */ }

	}

	// Das ist nur eine provisorische Methode
	public void updateEigenschaft(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement update = (PreparedStatement) conn
				.prepareStatement("UPDATE EIGENSCHAFT (fname, lname) WHERE id =  VALUES (?,?)");
		update.execute();
		try {
			update.close();
		} catch (Exception e) {
			/* ignored */ }
		try {
			conn.close();
		} catch (Exception e) {
			/* ignored */ }

	}
	
	// Das ist nur eine provisorische Methode
	public void deleteEigenschaft(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn
				.prepareStatement("DELETE EIGENSCHAFT WHERE id =  ");
		delete.execute();
		try {
			delete.close();
		} catch (Exception e) {
			/* ignored */ }
		try {
			conn.close();
		} catch (Exception e) {
			/* ignored */ }

	}

	public Eigenschaft getEigenschaftByID(int id) {
		return null;
	}

	public Vector<Eigenschaft> getEigenschaft() {
		return null;
	}

}


