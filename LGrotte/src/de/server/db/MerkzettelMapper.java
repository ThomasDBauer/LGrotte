package de.server.db;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;

import de.shared.BO.Profil;

public class MerkzettelMapper {
		
	private static MerkzettelMapper merkzettelMapper = null; 
	
	protected MerkzettelMapper() {
		
	}
	
	public static MerkzettelMapper MerkzettelMapper() {
	if(merkzettelMapper == null) {
		merkzettelMapper = new MerkzettelMapper();
	}
	return merkzettelMapper;
	}
	
	public void createMerkzettel() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createMerkzettel = (PreparedStatement) con.prepareStatement("CREATE TABLE IF NOT EXISTS Merkzettel(gemerkteProfile varchar(255), PRIMARY KEY(gemerkteProfile))");
		createMerkzettel.execute();
	}
	
	public void insertMerkzettel(Profil[] gemerkteProfile) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertMerkzettel = (PreparedStatement) con.prepareStatement("INSERT INTO Merkzettel(gemerkteProfile) VALUES ('" + gemerkteProfile + "')");
		insertMerkzettel.execute();
	}
	
}
