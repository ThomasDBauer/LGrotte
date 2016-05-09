package de.server.db;

import java.util.Vector;

import java.sql.Connection;
import java.sql.PreparedStatement;


import de.shared.BO.Eigenschaft;
import de.shared.BO.Profil;

public class EigenschaftMapper {

	private static EigenschaftMapper eigenschaftMapper = null;

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
		PreparedStatement create = (PreparedStatement) conn.prepareStatement
				("CREATE TABLE IF NOT EXISTS eigenschaft (erlauterung varchar(255), "
						+ "id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id))");
		create.execute();

	}

	public void insertEigenschaft(Eigenschaft eigenschaft) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement
				("INSERT INTO eigenschaft(erlauterung) VALUES ('" + 
						eigenschaft.getErlaeuterung() + "')");
		insert.execute();

	}

	// Das ist nur eine provisorische Methode
	public void updateEigenschaft(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement update = (PreparedStatement) conn.prepareStatement
				("UPDATE EIGENSCHAFT (fname, lname) WHERE id =  VALUES (?,?)");
		update.execute();
	}

	// Das ist nur eine provisorische Methode
	public void deleteEigenschaft(Profil p) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
				("DELETE EIGENSCHAFT WHERE id =  ");
		delete.execute();

	}

	public Eigenschaft getEigenschaftByID(int id) {
		return null;
	}

	public Vector<Eigenschaft> getEigenschaft() {
		return null;
	}

}
