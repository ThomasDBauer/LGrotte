package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;




import de.shared.BO.EigenschaftFrei;

public class EigenschaftFreiMapper {
	
	
	private static EigenschaftFreiMapper eFreiMapper = null;
	
	protected EigenschaftFreiMapper(){
		
	}
	
	public EigenschaftFreiMapper eigenschaftFreiMapper(){
		if(eFreiMapper == null){
			eFreiMapper = new EigenschaftFreiMapper();
		}
		return eFreiMapper;
	}
	
	
	public void createEigenschaftFrei(EigenschaftFrei e)throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS eigenschaftfrei(eigenschaftfrei_id varchar(255), FOREIGN KEY(eigenschaftfrei_id) REFERENCES eigenschaft(id), PRIMARY KEY(eigenschaftfrei_id))");
		create.execute();
		
	}
	
	public void insertEigenschaftFrei(EigenschaftFrei e)throws Exception{
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO EigenschaftFrei(eigenschaftfrei_id) VALUES ('" + e + "')");
			insert.execute();
		
	}
	
	public void deleteEigenschaftFrei(EigenschaftFrei e)throws Exception{
		
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
