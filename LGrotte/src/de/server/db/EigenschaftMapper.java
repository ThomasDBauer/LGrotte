package de.server.db;

import java.util.Vector;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;

import de.shared.BO.Eigenschaft;

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

	public void updateEigenschaft(Eigenschaft e) {

	}
	
	public void deleteEigenschaft(Eigenschaft e) {

	}

	public Eigenschaft getEigenschaftByID(int id) {
		return null;
	}

	public Vector<Eigenschaft> getEigenschaft() {
		return null;
	}

}


