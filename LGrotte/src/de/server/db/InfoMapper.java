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
		
		PreparedStatement create = (PreparedStatement) con.prepareStatement(
				
				"CREATE TABLE IF NOT EXISTS INFOS (id int NOT NULL, value varchar(255), EIGENSCHAFT_id int, PRIMARY KEY(id), FOREIGN KEY(EIGENSCHAFT_id) references EIGENSCHAFT");
		
		create.execute();
		
	}

	public void insertInfo(Info info) throws Exception{

		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) con.prepareStatement("INSERT INTO INFOS (id, value, EIGENSCHAFT_id) VALUES (?,?,?)");
		
		insert.execute();
		
		try {
			insert.close();
		} catch (Exception e) {
			/* ignored */ }
		try {
			con.close();
		} catch (Exception e) {
			/* ignored */ }
		
	}


}
