package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.shared.BO.Profil;

public class MerkzettelMapper {
		
	private static MerkzettelMapper merkzettelMapper = null; 
	
	protected MerkzettelMapper() {
		
	}
	
	public static MerkzettelMapper merkzettelMapper() {
	if(merkzettelMapper == null) {
		merkzettelMapper = new MerkzettelMapper();
	}
	return merkzettelMapper;
	}
	
	public void createMerkzettelTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createMerkzettel = (PreparedStatement) con.prepareStatement("CREATE TABLE IF NOT EXISTS Merkzettel(gemerkteProfile varchar(255), PRIMARY KEY(gemerkteProfile))");
		createMerkzettel.execute();
	}
	
	public void insertMerkzettel(Profil[] gemerkteProfile) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertMerkzettel = (PreparedStatement)con.prepareStatement("INSERT INTO Merkzettel(gemerkteProfile) VALUES ('" + gemerkteProfile + "')");
		insertMerkzettel.execute();
	}
	
}
