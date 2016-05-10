package de.server.db;

import java.util.Vector;
import java.sql.*;

import de.server.db.DBConnection;
import de.server.GreetingServiceImpl;


public class ProfilinfoMapper {
	
	private static ProfilinfoMapper profilinfoMapper = null;
	
	protected ProfilinfoMapper(){
	}
	
	public static ProfilinfoMapper profilinfoMapper(){
		if (profilinfoMapper == null){
			profilinfoMapper = new ProfilinfoMapper();
		}
		
		return profilinfoMapper;
	}
	
	public void createProfilInfo() throws Exception {
		Connection connection = DBConnection.connection();
			PreparedStatement create = (PreparedStatement) connection
					.prepareStatement("CREATE TABLE IF NOT EXISTS profil_info "
							+ "(profil_id INT NOT NULL, info_id INT NOT NULL, "
							+ "eigenschaft_id INT NOT NULL, "
							+ "PRIMARY KEY (profil_id, info_id), "
							+ "FOREIGN KEY(profil_id) REFERENCES profile(id) "
							+ "ON UPDATE CASCADE ON DELETE RESTRICT, "
							+ "FOREIGN KEY(info_id) REFERENCES infos(id) "
							+ "ON UPDATE CASCADE ON DELETE RESTRICT, "
							+ "FOREIGN KEY(eigenschaft_id) "
							+ "REFERENCES eigenschaften(id) "
							+ "ON UPDATE CASCADE ON DELETE RESTRICT)");
			create.execute();
	}
	
//	public void insertProfilInfo(ProfilInfo pi) {
//		Connection connection = DBConnection.connection();
//
//		try {
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("INSERT INTO `profilinfo`(`Profil-id`, `Info-id`) "
//					+ "VALUES ("
//					+ pi.getInfoID()
//					+ ","
//					+ pi.getProfilID()
//					+ ")");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
//	
//	public void updateProfilInfo (ProfilInfo pi){
//		Connection connection = DBConnection.connection();
//		
//		try {
//		Statement stmt = connection.createStatement();
//
//		stmt.executeUpdate("UPDATE `profilinfo`" + "SET `Profil-id`=[" + pi.getProfilID() + 
//				"],`Eigenschaft-id`=[" + pi.getInfoID() + "] WHERE `Profil-id`='" + pi.getProfilID() + "'");
//				
//		/*Wenn wir das ERM so lassen, muss man �ber das SQL-Statement herauslesen k�nnen, zu welcher Eigenschafts-ID,
//		Die Info-id zugeh�rig ist, um die info-id der richtigen Eigenschafts-id zu l�schen --> das Problem liegt im 
//		zusammengesetzten Primary-Key, der nur in Kombi beider Attribute Eindeutig ist*/
//		}
//		catch(SQLException e2){
//			e2.printStackTrace();
//		}
////		return pi; ??? --> Analogie zu insert(ProfilInfo)
//	}
//	
//	public void deleteProfilInfo (ProfilInfo pi){
//		Connection connection = DBConnection.connection();
//		
//		try{
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("DELETE FROM `profilinfo`" + "WHERE `Profil-id`='" + pi.getProfilID() + 
//					"' AND `Eigenschaft-id`='" + pi.getInfoID() + "'");
//		}
//		catch (SQLException e2){
//			e2.printStackTrace();
//		}
//	}
//	
//	public ProfilInfo getProfilInfo(int profilID){
//		//Eigentlich m�sste geProfilInfo ein Vetor oder einen Array mit der Anzahl aller Info-ID�s zur�ckgeben
//		Connection connection = DBConnection.connection();
//		
//		try{
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT `Eigenschaft-id` FROM `profilinfo` WHERE `Profil-id`='" + profilID + "'");
//		
//		while(rs.next()){
//			ProfilInfo pi = new ProfilInfo();
//			pi.setProfilID(profilID);
//		}
//			
//		}
//		catch(Exception e2){
//			e2.printStackTrace();
//		}
//	
//		
//		return null;
//	}
//	
//	public Vector<ProfilInfo> getProfilInfos(){
//		Connection connection = DBConnection.connection();
//		
//		try{
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM `profilinfo`");
//			Vector<ProfilInfo> ProfilInfos = new Vector<ProfilInfo>();
//		while(rs.next()){
//			ProfilInfo pi = new ProfilInfo();
//			pi.setInfoID(Integer.parseInt(rs.getString("`Eigenschaft-id`")));
//			//Eigentlich m�sste man hier neue Werte in den Vector<Info-ID> eintragen
//			pi.setProfilID(Integer.parseInt(rs.getString("`Profil-id`")));
//			ProfilInfos.add(pi);
//		}
//			
//		}
//		catch(Exception e2){
//			e2.printStackTrace();
//		}
//	
//		
//		return null;
//	}
}
