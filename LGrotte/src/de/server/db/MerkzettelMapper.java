package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.shared.BO.Merkzettel;
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
		PreparedStatement createMerkzettel = (PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS merkzettel(gemerktesProfil varchar(45), "
				+ "merkendesProfil varchar(45), PRIMARY KEY("
				+ "gemerktesProfil, merkendesProfil), FOREIGN KEY(gemerktesProfil) "
				+ "REFERENCES profil(email), FOREIGN KEY (merkendesProfil) "
				+ "REFERENCES profil(email))");
		createMerkzettel.execute();
	}
	
	public void insertMerkzettel(Merkzettel mz) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertMerkzettel = (PreparedStatement)con.prepareStatement(
				"INSERT INTO merkzettel(gemerktesProfil, merkendesProfil) VALUES ("
				+ "'" + mz.getGemerktesProfil() + "', '" + mz.getMerkendesProfil() + "')");
		insertMerkzettel.execute();
	}
	
	public void deleteMerkzettel(Profil profil) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteMerkzettel = (PreparedStatement) con.prepareStatement(
				"DELETE FROM merkzettel WHERE gemerkteProfile='" + profil.getEmail() + "'");
		deleteMerkzettel.execute();
	}
	
	
	
	
	
}
