package de.server.db;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;

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



	public void createInfoTable() throws Exception{
		Connection con = (Connection) DBConnection.connection();
		
		PreparedStatement createInfo = (PreparedStatement) con.prepareStatement(
				
				"CREATE TABLE IF NOT EXISTS INFOS (id int NOT NULL, value varchar(255), EIGENSCHAFT_id int, PRIMARY KEY(id), FOREIGN KEY(EIGENSCHAFT_id) references EIGENSCHAFT");
		
		createInfo.execute();
		
	}

	public void insertInfo(Info info) throws Exception{

		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertInfo = (PreparedStatement) con.prepareStatement("INSERT INTO INFOS (id, value, EIGENSCHAFT_id) VALUES (?,?,?)");
		
		insertInfo.execute();
		
	}
	
	public void deleteInfo (Info info) throws Exception {
		
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteInfo =  (PreparedStatement) con.prepareStatement(
				"DELETE FROM INFOS WHERE id='"+info+"'");
		deleteInfo.execute();
	}


}
