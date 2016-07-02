package de.server.db;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;

/**
 * Die MerktzettelMapper ist zuständig für alle Profile 
 * die gemerkt werden sollen
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class MerkzettelMapper {

	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static MerkzettelMapper merkzettelMapper = null;

	/**
	 * Protected (geschützer) Konstruktur
	 */
	protected MerkzettelMapper() {
	}

	/**
	 * Statische Methode vom Typ MerkzettelMapper
	 * 
	 * @return merkzettelmapper
	 */
	public static MerkzettelMapper merkzettelMapper() {
		if (merkzettelMapper == null) {
			merkzettelMapper = new MerkzettelMapper();
		}
		return merkzettelMapper;
	}
	
	/**
	 * Erstellt eine Tabelle in der die gemerkten Profile aufgelistet sind
	 */
	public void createMerkzettelTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createMerkzettel = (PreparedStatement) con
				.prepareStatement("CREATE TABLE IF NOT EXISTS merkzettel(gemerktesProfil varchar(45), "
						+ "merkendesProfil varchar(45), PRIMARY KEY("
						+ "gemerktesProfil, merkendesProfil), FOREIGN KEY(gemerktesProfil) "
						+ "REFERENCES profil(email) ON DELETE CASCADE, FOREIGN KEY (merkendesProfil) "
						+ "REFERENCES profil(email) ON DELETE CASCADE)");
		createMerkzettel.execute();
	}

	/**
	 * Fügt Profile die gemerkt worden sind in die Tabelle 
	 * abhängig vom merkenden Profil
	 * 
	 * @param mz
	 */
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
	
	/**
	 * Enfernt das gemerkte Profil vom Merkzettel
	 * 
	 * @param mz
	 */
	public void deleteMerkzettel(Merkzettel mz) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteMerkzettel = (PreparedStatement) con.prepareStatement(
				"DELETE FROM merkzettel WHERE gemerktesProfil='" + mz.getGemerktesProfil()+ "'"
				+ " AND merkendesProfil='" + mz.getMerkendesProfil() + "'");
		deleteMerkzettel.execute();
	}
	
	/**
	 * Ruft alle gemerkten Profile vom Merkenden auf
	 * 
	 * @param email
	 * @return merkzettel
	 */
	public Vector<Merkzettel> getMerkzettelByOwner(String email)
			throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con
				.prepareStatement("SELECT gemerktesProfil FROM merkzettel WHERE merkendesProfil="
						+ "'" + email + "'");
		ResultSet result = select.executeQuery();
		Vector<Merkzettel> merkzettel = new Vector<Merkzettel>();
		while (result.next()) {
			Merkzettel m = new Merkzettel();
			m.setGemerktesProfil(result.getString("gemerktesProfil"));
			m.setMerkendesProfil(email);
			merkzettel.add(m);
		}
		return merkzettel;
	}

	/**
	 * Ruft alle gemerkten Profile vom Merkenden auf
	 * 
	 * @param email
	 * @return merkzettelProfile
	 */
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
			p.setEmail(result.getString("email"));
			p.setFname(result.getString("fname"));
			p.setLname(result.getString("lname"));
			merkzettelProfile.add(p);
		}
		return merkzettelProfile;
	}
}
