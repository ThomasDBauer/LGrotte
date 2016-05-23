package de.server.db;

import java.util.Vector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

//	public void updateEigenschaft(Profil p) throws Exception {
//		Connection conn = (Connection) DBConnection.connection();
//		PreparedStatement update = (PreparedStatement) conn.prepareStatement
//				("UPDATE EIGENSCHAFT (fname, lname) WHERE id ='"+p.getId()+"'  VALUES ('"+p.getFname()+"','"+p.getLname()+"')");
//		update.execute();
//	}
//
//	public void deleteEigenschaft(Profil p) throws Exception {
//		Connection conn = (Connection) DBConnection.connection();
//		PreparedStatement delete = (PreparedStatement) conn.prepareStatement
//				("DELETE EIGENSCHAFT WHERE id = '"+p.getId()+"'");
//		delete.execute();
//
//	}

	public Eigenschaft getEigenschaftByID(int id) {
		return null;
	}

	public Vector<Eigenschaft> getEigenschaften() throws Exception {
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM eigenschaft");
		ResultSet rs = select.executeQuery();
		Vector<Eigenschaft>eigenschaften = new Vector<Eigenschaft>();
		Eigenschaft e = new Eigenschaft();
		while(rs.next()){
			e.setErlaeuterung(rs.getString("erlauterung"));
			e.setId(rs.getInt("id"));
			eigenschaften.add(e);
		}
		return eigenschaften;
	}

}
