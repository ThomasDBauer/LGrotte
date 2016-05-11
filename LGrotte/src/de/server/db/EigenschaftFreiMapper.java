package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;




import de.shared.BO.EigenschaftFrei;

public class EigenschaftFreiMapper {
	
	
	private static EigenschaftFreiMapper eigenschaftFreiMapper = null;
	
	protected EigenschaftFreiMapper(){
		
	}
	
	//for test commit
	
	public static EigenschaftFreiMapper eigenschaftFreiMapper(){
		if(eigenschaftFreiMapper == null){
			eigenschaftFreiMapper = new EigenschaftFreiMapper();
		}
		return eigenschaftFreiMapper;
	}
	
	
	public void createEigenschaftFreiTable()throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS eigenschaftfrei(eigenschaftfrei_id int, FOREIGN KEY(eigenschaftfrei_id) REFERENCES eigenschaft(id), PRIMARY KEY(eigenschaftfrei_id))");
		create.execute();
		
	}
	
	public void insertEigenschaftFrei(EigenschaftFrei ef)throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO eigenschaftfrei(eigenschaftfrei_id) VALUES ('" + ef.getId() + "')");
			insert.execute();
		
	}
	
	public void deleteEigenschaftFrei(EigenschaftFrei ef)throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement deleteEigenschaftFrei = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM eigenschaftfrei WHERE eigenschaftauswahl_id ='"+ ef.getId() +"'");
			deleteEigenschaftFrei.execute();	
	}
	
	public void updateEigenschaftFrei(EigenschaftFrei e)throws Exception{
		
	}
	
	public EigenschaftFrei getEigenschaftFreiByID(int id)throws Exception{
		return null;
	}
	
	public Vector<EigenschaftFrei> getEigenschaftFrei()throws Exception{
		return null;
	}
	
	
}
