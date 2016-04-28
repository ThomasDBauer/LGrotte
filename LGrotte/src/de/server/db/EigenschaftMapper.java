package de.server.db;

import java.util.Vector;

import java.sql.Connection;
import java.sql.PreparedStatement;


import de.shared.BO.Eigenschaft;
import de.shared.BO.Profil;

public class EigenschaftMapper {

	private static EigenschaftMapper eigenschaftMapper;

	protected EigenschaftMapper() {

	}
	
	public static EigenschaftMapper eigenschaftMapper(){
		if(eigenschaftMapper ==null){
			eigenschaftMapper = new EigenschaftMapper();
		}
		return eigenschaftMapper;
	}

	public void createEigenschaftTable() throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS EIGENSCHAFT (fname varchar(255), lname varchar(255), PRIMARY KEY(fname))");
		create.execute();

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
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement("DELETE EIGENSCHAFT WHERE id =  ");
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
