package de.server.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import de.shared.BO.Kontaktsperre;
import de.shared.BO.Profil;

/**
 * Die KontaktsperreMapper ist zuständig für alle Profile
 * die gesperrt werden sollen
 * 
 * @author Thomas Bauer
 *
 * @version 1.0
 */

public class KontaktsperreMapper {
	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static KontaktsperreMapper kontaktsperreMapper = null;
	
	/**
	 * Protected (geschützer) Konstruktur
	 */
	protected KontaktsperreMapper() {	
	}
	
	/**
	 * Statische Methode vom Typ KontaktsperreMapper
	 * 
	 * @return kontaktsperremapper
	 */
	public static KontaktsperreMapper kontaktsperreMapper() {
		if (kontaktsperreMapper == null) {
			kontaktsperreMapper = new KontaktsperreMapper();
		}
		return kontaktsperreMapper;	
	}
	
	/**
	 * Erstellt eine Tabelle in der die gesperrten Profile aufgelistet sind
	 */
	public void createKontaktsperreTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement createKontaktsperre = (PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS kontaktsperre(gesperrtesProfil varchar(45), "
				+ "sperrendesProfil varchar(45), PRIMARY KEY(gesperrtesProfil, sperrendesProfil),"
				+ "FOREIGN KEY(gesperrtesProfil) REFERENCES profil(email) ON DELETE CASCADE, "
				+ "FOREIGN KEY (sperrendesProfil) REFERENCES profil(email) ON DELETE CASCADE)");
		createKontaktsperre.execute();
	}
	
	/**
	 * Fügt Profile die gesperrt worden sind in die Tabelle 
	 * abhängig vom sperrenden Profil
	 * 
	 * @param k
	 */
	public void insertKontaktsperre(Kontaktsperre k) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insertKontaktsperre = (PreparedStatement) con.prepareStatement(
				"INSERT INTO kontaktsperre(gesperrtesProfil, sperrendesProfil) VALUES ("
				+ "'" + k.getGesperrtesProfil() + "','"+k.getSperrendesProfil()+"')");
		insertKontaktsperre.execute();
	}
	
	/**
	 * Zeigt alle gesperrten Profil vom Sperrer
	 * 
	 * @param email
	 * @return Vector<Kontaktsperre>
	 */
	public Vector<Kontaktsperre> getKontaktsperreByOwner(String email) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con.prepareStatement(
				"SELECT gesperrtesProfil FROM kontaktsperre WHERE sperrendesProfil="
				+ "'" + email + "'");
		ResultSet result = select.executeQuery();
		Vector<Kontaktsperre> kontaktsperren = new Vector<Kontaktsperre>();
		while(result.next()){
			Kontaktsperre k = new Kontaktsperre();
			k.setGesperrtesProfil(result.getString("gesperrtesProfil"));
			k.setSperrendesProfil(email);
			kontaktsperren.add(k);
		}
		return kontaktsperren;
	}
	
	/**
	 * Gibt die Kontaktsperren an, auf denen der User gesperrt wurde
	 * 
	 * @param email
	 * @return Vector<Kontaktsperre>
	 */
		public Vector<Kontaktsperre> getKontaktsperren(String email) throws Exception{
			Connection con = (Connection) DBConnection.connection();
			PreparedStatement select = (PreparedStatement) con.prepareStatement(
					"SELECT sperrendesProfil FROM kontaktsperre WHERE gesperrtesProfil="
					+ "'" + email + "'");
			ResultSet result = select.executeQuery();
			Vector<Kontaktsperre> kontaktsperren = new Vector<Kontaktsperre>();
			while(result.next()){
				Kontaktsperre k = new Kontaktsperre();
				k.setSperrendesProfil(result.getString("sperrendesProfil"));
				k.setGesperrtesProfil(email);
				kontaktsperren.add(k);
			}
			return kontaktsperren;
		}
	
	/**
	 * Ruft alle gesperrten Profile vom Sperrer auf
	 * 
	 * @param email
	 * @return Vector<Profil>
	 */
	public Vector<Profil> getKontaktsperreProfileByOwner(String email)
			throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement select = (PreparedStatement) con
				.prepareStatement("SELECT * FROM profil INNER JOIN kontaktsperre ON profil.email = kontaktsperre.gesperrtesProfil WHERE kontaktsperre.sperrendesProfil="
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
	
	/**
	 * Ruft alle gesperrten Profile vom Sperrer auf
	 * 
	 * @param ks
	 */
	public void deleteKontaktsperre(Kontaktsperre ks) throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement deleteKontaktsperre = (PreparedStatement) con.prepareStatement(
				"DELETE FROM kontaktsperre WHERE gesperrtesProfil='" + ks.getGesperrtesProfil()+ "'"
				+ " AND sperrendesProfil='" + ks.getSperrendesProfil() + "'");
		deleteKontaktsperre.execute();
	}
	
}