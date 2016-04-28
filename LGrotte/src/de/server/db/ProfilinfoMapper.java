package de.server.db;

import java.util.Vector;
import java.sql.*;

import de.hdm.test.lukas.server.GreetingServiceImpl;
import de.hdm.test.lukas.shared.BO.*;


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
	
	public void insertProfilInfo(ProfilInfo pi){
		Connection connection = DBConnection.connection();
		
		try{
			Statement stmt = connection.createStatement();
			
//			ResultSet rs = stmt.executeQuery("SELECT MAX");
			
//			if (rs.next()){
//				p.setInfoID(rs.getInt("maxid") + 1);
				
			stmt = connection.createStatement();
			
			stmt.executeUpdate("INSERT INTO `profilinfo`(`Profil-id`, `Eigenschaft-id`) " + 
			"VALUES (" + pi.getInfoID() + "," + pi.getProfilID() + ")");
//			} 
		}
		catch (SQLException e2){
			e2.printStackTrace();
		}
	}
	
	public void updateProfilInfo (ProfilInfo pi){
		Connection connection = DBConnection.connection();
		
		try {
		Statement stmt = connection.createStatement();

		stmt.executeUpdate("UPDATE `profilinfo`" + "SET `Profil-id`=[" + pi.getProfilID() + 
				"],`Eigenschaft-id`=[" + pi.getInfoID() + "] WHERE `Profil-id`='" + pi.getProfilID() + "'");
				
		/*Wenn wir das ERM so lassen, muss man über das SQL-Statement herauslesen können, zu welcher Eigenschafts-ID,
		Die Info-id zugehörig ist, um die info-id der richtigen Eigenschafts-id zu löschen --> das Problem liegt im 
		zusammengesetzten Primary-Key, der nur in Kombi beider Attribute Eindeutig ist*/
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
//		return pi; ??? --> Analogie zu insert(ProfilInfo)
	}
	
	public void deleteProfilInfo (ProfilInfo pi){
		Connection connection = DBConnection.connection();
		
		try{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM `profilinfo`" + "WHERE `Profil-id`='" + pi.getProfilID() + 
					"' AND `Eigenschaft-id`='" + pi.getInfoID() + "'");
		}
		catch (SQLException e2){
			e2.printStackTrace();
		}
	}
	
	public ProfilInfo getProfilInfo(int profilID){
		//Eigentlich müsste geProfilInfo ein Vetor oder einen Array mit der Anzahl aller Info-ID´s zurückgeben
		Connection connection = DBConnection.connection();
		
		try{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `Eigenschaft-id` FROM `profilinfo` WHERE `Profil-id`='" + profilID + "'");
		
		while(rs.next()){
			ProfilInfo pi = new ProfilInfo();
			pi.setProfilID(profilID);
		}
			
		}
		catch(Exception e2){
			e2.printStackTrace();
		}
	
		
		return null;
	}
	
	public Vector<ProfilInfo> getProfilInfos(){
		Connection connection = DBConnection.connection();
		
		try{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `profilinfo`");
			Vector<ProfilInfo> ProfilInfos = new Vector<ProfilInfo>();
		while(rs.next()){
			ProfilInfo pi = new ProfilInfo();
			pi.setInfoID(Integer.parseInt(rs.getString("`Eigenschaft-id`")));
			//Eigentlich müsste man hier neue Werte in den Vector<Info-ID> eintragen
			pi.setProfilID(Integer.parseInt(rs.getString("`Profil-id`")));
			ProfilInfos.add(pi);
		}
			
		}
		catch(Exception e2){
			e2.printStackTrace();
		}
	
		
		return null;
	}
}
