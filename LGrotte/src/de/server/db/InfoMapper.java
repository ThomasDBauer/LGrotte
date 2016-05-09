package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.shared.BO.Info;

public class InfoMapper {
	//Klasse ist Singleton und das gewährleistet diese Funktion, es speichert die einzige Instanz dieser Klasse.
	public static InfoMapper infomapper = null;



	//Ein geschützter Konstruktor, der verhindert dass man per "new" eine neue Instanz erzeugen kann.
	protected InfoMapper() {
	}


	public static InfoMapper infoMapper(){


	if(infomapper == null){
	infomapper = new InfoMapper();
	}


	return infomapper;
	}



//	public void createInfoTable() throws Exception{
//		Connection con = (Connection)DBConnection.connection();
//		PreparedStatement createInfo = (PreparedStatement) con.prepareStatement(
//				"CREATE TABLE IF NOT EXISTS INFOS (id int NOT NULL AUTO_INCREMENT, "
//				+ "value varchar(50), eigenschaft_id int NOT NULL, PRIMARY KEY(id), "
//				+ "FOREIGN KEY(eigenschaft_id) REFERENCES eigenschaft(id)");
//		createInfo.execute();
//	}

	public void createInfoTable() throws Exception{
		Connection con = (Connection)DBConnection.connection();
		PreparedStatement createInfo = (PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS INFOS (id int NOT NULL AUTO_INCREMENT, value varchar(50), eigenschaft_id int, PRIMARY KEY(id))");
		createInfo.execute();
	}

	public void insertInfo(Info info) throws Exception{

		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertInfo = (PreparedStatement) con.prepareStatement(
				"INSERT INTO INFOS (value, eigenschaft_id) VALUES "
				+ "('"+info.getValue()+"',"+info.getEigenschaft()+")");
		
		insertInfo.execute();
		
	}
	
	public void deleteInfo (Info info) throws Exception {
		
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteInfo =  (PreparedStatement) con.prepareStatement(
				"DELETE FROM INFOS WHERE id='"+info+"'");
		deleteInfo.execute();
	}


}
