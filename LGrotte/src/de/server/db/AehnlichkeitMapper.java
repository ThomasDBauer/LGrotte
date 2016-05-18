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
		//zusammengesetzer PrimaryKey
		PreparedStatement createAehnlichkeit = (PreparedStatement) con.prepareStatement
				("CREATE TABLE IF NOT EXISTS aehnlichkeit (REFERENZPROFIL_id int, VERGLEICHSPROFIL_id int,"+
						"mass byte, PRIMARY KEY (REFERENZPROFIL_id, VERGLEICHSPROFIL_id))");
		
		createAehnlichkeit.execute();
		
	}//createAehnlichkeit() zu
	
	
	public void insertAehnlichkeit(Profil referenz, Profil vergleich, byte mass) throws Exception{
		
		Connection con = (Connection) DBConnection.connection();
		
//		PreparedStatement insertAehnlichkeit = (PreparedStatement) con.prepareStatement("INSERT INTO aehnlichkeit (REFERENZPROFIL_id, VERGLEICHSPROFIL_id, mass) VALUES ('"
//		+ referenz.getId() + vergleich.getId() + mass + "')");
		
		
//		insertAehnlichkeit.execute();
	}//insertAehnlichkeit() zu
	
	
//	public void deleteAehnlichkeit (Profil referenz, Profil vergleich) throws Exception {
//		
//		Connection con = (Connection) DBConnection.connection();
//		PreparedStatement deleteAehnlichkeit = (PreparedStatement) con.prepareStatement(
//				"DELETE FROM AEHNLICHKEIT WHERE REFERENZPROFIL_id='"+ referenz.getId() +  "',VERGLEICHSPROFIL_id='" + vergleich.getId() + "'" );
//		deleteAehnlichkeit.execute();
//	}
	
}