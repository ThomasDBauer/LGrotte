package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


import de.shared.BO.EigenschaftAuswahl;

public class EigenschaftAuswahlMapper {
	
	

		
		private static EigenschaftAuswahlMapper eAuswahlMapper = null;
		
		protected EigenschaftAuswahlMapper(){
			
		}
		
		public static EigenschaftAuswahlMapper eigenschaftAuswahlMapper(){
			if(eAuswahlMapper == null){
				eAuswahlMapper = new EigenschaftAuswahlMapper();
			}
			return eAuswahlMapper;
		}
		
		public void createEigenschaftAuswahlTable()throws Exception {
			Connection conn = (Connection) DBConnection.connection();
			PreparedStatement create = (PreparedStatement) conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS eigenschaftauswahl(element varchar(255),eigenschaftauswahl_id int REFERENCES eigenschaft(id), PRIMARY KEY(eigenschaftauswahl_id))");
			create.execute();
		}
		
		public void insertEigenschaftAuswahl(EigenschaftAuswahl ea) throws Exception{
			Connection conn = (Connection) DBConnection.connection();
			PreparedStatement insert = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO EigenschaftAuswahl(eigenschaftauswahl_id, element) VALUES ('" + ea + "')");
				insert.execute();	
		}
		
		public void updateEigenschaftAuswahl(EigenschaftAuswahl ea)throws Exception{
			
		}
		
		public void deleteEigenschaftAuswahl(EigenschaftAuswahl ea)throws Exception{
			
		}
		
		public EigenschaftAuswahl getEigenschaftAuswahlByID(int id)throws Exception{
			return null;
		}
		
		public Vector<EigenschaftAuswahl> getEigenschaftAuswahl()throws Exception{
			return null;
		}
		
	
}

