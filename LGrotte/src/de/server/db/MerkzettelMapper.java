package de.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;

public class MerkzettelMapper {

	private static MerkzettelMapper merkzettelMapper = null;

	protected MerkzettelMapper() {
	}

	public static MerkzettelMapper merkzettelMapper() {
		if (merkzettelMapper == null) {
			merkzettelMapper = new MerkzettelMapper();
		}
		return merkzettelMapper;
	}

	public void createMerkzettelTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createMerkzettel = (PreparedStatement) con
				.prepareStatement("CREATE TABLE IF NOT EXISTS merkzettel(gemerktesProfil varchar(45), "
						+ "merkendesProfil varchar(45), PRIMARY KEY("
						+ "gemerktesProfil, merkendesProfil), FOREIGN KEY(gemerktesProfil) "
						+ "REFERENCES profil(email), FOREIGN KEY (merkendesProfil) "
						+ "REFERENCES profil(email))");
		createMerkzettel.execute();
	}

	public void insertMerkzettel(Merkzettel mz) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertMerkzettel = (PreparedStatement) con
				.prepareStatement("INSERT INTO merkzettel(gemerktesProfil, merkendesProfil) VALUES ("
						+ "'"
						+ mz.getGemerktesProfil()
						+ "', '"
						+ mz.getMerkendesProfil() + "')");
		insertMerkzettel.execute();
	}

	public void deleteMerkzettel(String email) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteMerkzettel = (PreparedStatement) con
				.prepareStatement("DELETE FROM merkzettel WHERE gemerkteProfile='"
						+ email + "'");
		deleteMerkzettel.execute();
	}
	
	public Vector<Merkzettel> getMerkzettelByOwner(String email) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con.prepareStatement(
				"SELECT gemerktesProfil FROM merkzettel WHERE merkendesProfil="
				+ "'" + email + "'");
		ResultSet result = select.executeQuery();
		Vector<Merkzettel> merkzettel = new Vector<Merkzettel>();
		while(result.next()){
			Merkzettel m = new Merkzettel();
			m.setGemerktesProfil(result.getString("gemerktesProfil"));
			m.setMerkendesProfil(email);
			merkzettel.add(m);
		}
		return merkzettel;
	}
	
	public Vector<Profil> getMerkzettelProfileByOwner(String email)
			throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con
		.prepareStatement("SELECT * FROM profil INNER JOIN merkzettel ON profil.email = merkzettel.gemerktesProfil WHERE merkzettel.merkendesProfil="
						+ "'" + email + "'");
		ResultSet result = select.executeQuery();
		Vector<Profil> merkzettelProfile = new Vector<Profil>();
		while (result.next()) {
			Profil p = new Profil();
			p.setFname(result.getString("fname"));
			p.setLname(result.getString("lname"));
			merkzettelProfile.add(p);
		}
		return merkzettelProfile;
	}
}
