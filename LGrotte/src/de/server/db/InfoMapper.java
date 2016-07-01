package de.server.db;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Vector;


import de.shared.BO.Info;

/**
 * Die InfoMapper speichert alle Infos abhängig zur Eigenschaft
 * 
 * @author Thomas Bauer und Enrico Popaj
 *
 * @version 1.0
 */

public class InfoMapper {
	//Klasse ist Singleton und das gewährleistet diese Funktion, es speichert die einzige Instanz dieser Klasse.
	private static InfoMapper infomapper = null;


	//Ein geschützter Konstruktor, der verhindert dass man per "new" eine neue Instanz erzeugen kann.
	protected InfoMapper() {
	}


	public static InfoMapper infoMapper(){


	if(infomapper == null){
	infomapper = new InfoMapper();
	}


	return infomapper;
	}

	// Erstellt eine Tabelle in der die Infos gespeichert werden
	public void createInfoTable() throws Exception{
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement createInfo = (PreparedStatement) conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS infos (info_id int NOT NULL AUTO_INCREMENT, "
				+ "value varchar(50), eigenschaft_id int, PRIMARY KEY(info_id), "
				+ "FOREIGN KEY(eigenschaft_id) REFERENCES eigenschaft(eigenschaft_id) "
				+ "ON UPDATE CASCADE ON DELETE RESTRICT, UNIQUE(eigenschaft_id, value))"); // 
		createInfo.execute();
	}

	// Fügt die Infos in die Tabelle ein
	public void insertInfo(Info info) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertInfo = (PreparedStatement) con.prepareStatement(
				"INSERT INTO infos (value, eigenschaft_id) VALUES "
				+ "('"+info.getValue()+"',"+info.getEigenschaft()+")");
		insertInfo.execute();
	}
	
	/*
	 * Ruft die Info-ID anhand der Eigenschafts-ID und 
	 * der Erläuterung der Info auf
	 */
	public int getInfoIDByEigenschaftsIDAndValue(int eID, String value) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con.prepareStatement(
				"SELECT info_id FROM infos WHERE value= '" + value + "' AND eigenschaft_id =" + eID);
		ResultSet result = select.executeQuery();
		int id = 0;
		while(result.next()){
			id = result.getInt("info_id");
		}
		return id;
	}
	
	// Löscht Infos aus der Tabelle 
	public void deleteInfo (Info info) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteInfo =  (PreparedStatement) con.prepareStatement(
				"DELETE FROM infos WHERE value='"+info.getValue() +"' AND eigenschaft_id ="
						+ info.getEigenschaft());
		deleteInfo.execute();
	}
	
	// Akualisiert die Erläuterung der Infos
	public void updateInfo (Info info) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		
		PreparedStatement updateInfo = (PreparedStatement) con.prepareStatement(
				"UPDATE infos (value) WHERE info_id='"+info.getId()+"' VALUES('"+info.getValue()+"')");
		updateInfo.execute();
	}


}
