package de.server.db;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;

import de.shared.BO.Profil;

public class AehnlichkeitMapper {
public static AehnlichkeitMapper aehnlichkeitmapper = null;
	
	protected AehnlichkeitMapper(){
		
	}
	
	public static AehnlichkeitMapper aehnlichkeitMapper(){
		if(aehnlichkeitmapper==null){
			aehnlichkeitmapper = new AehnlichkeitMapper();
		}
		return aehnlichkeitmapper;
	}
	
	
	public void createAehnlichkeitTable() throws Exception {
		
		Connection con = (Connection) DBConnection.connection();
		
		PreparedStatement create = (PreparedStatement) con.prepareStatement("CREATE TABLE IF NOT EXISTS AEHNLICHKEIT (REFERENZPROFIL_id int, VERGLEICHSPROFIL_id int, mass byte, PRIMARY KEY (REFERENZPROFIL_id, VERGLEICHSPROFIL_id");
		
		create.execute();
		
	}
	
	
	public void insertAehnlichkeit(Profil referenz, Profil vergleich, byte mass) throws Exception{
		
		Connection con = (Connection) DBConnection.connection();
		
		PreparedStatement insert = (PreparedStatement) con.prepareStatement("INSERT INTO AEHNLICHKEIT (REFERENZPROFIL_id, VERGLEICHSPROFIL_id, mass) VALUES (?,?,?)");
		
		
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
