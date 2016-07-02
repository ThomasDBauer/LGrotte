package de.server.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.BO.SuchprofilInfo;
import de.shared.RO.ProfilEigenschaft;

/**
 * Die SuchprofilMapper zeigt auf welche Info zu welchem Suchprofil gehört
 * 
 * @author Thomas Bauer
 *
 * @version
 */

public class SuchprofilInfoMapper {

	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static SuchprofilInfoMapper suchprofilInfoMapper = null;

	/**
	 * Protected (geschützer) Konstruktur
	 */
	protected SuchprofilInfoMapper() {
	}

	/**
	 * Statische Methode vom Typ SuchprofilInfoMapper
	 * 
	 * @return suchprofilinfomapper
	 */
	public static SuchprofilInfoMapper suchprofilInfoMapper() {
		if (suchprofilInfoMapper == null) {
			suchprofilInfoMapper = new SuchprofilInfoMapper();
		}

		return suchprofilInfoMapper;
	}
	
	/**
	 * Erstellt eine Tabelle in der die Primary Keys vom Suchprofil und Info
	 * als Forgein Key eine Verbindung bilden
	 */
	public void createSuchProfilInfoTable() throws Exception {
		Connection conn = DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS suchprofil_info (suchprofilname varchar(35) NOT NULL, "
				+ "email varchar(35) NOT NULL, info_id int NOT NULL, PRIMARY KEY (suchprofilname, email, info_id), "
				+ "FOREIGN KEY(email) REFERENCES profil(email) ON DELETE CASCADE, "
				+ "FOREIGN KEY(info_id) REFERENCES infos(info_id) ON DELETE CASCADE,"
				+ "FOREIGN KEY (suchprofilname) REFERENCES suchprofil(suchprofilname) ON UPDATE RESTRICT ON DELETE CASCADE)");
		create.execute();
	}
	
	/**
	 * Fügt die Verbindung zwischen einem Suchprofil und einer Info ein
	 * 
	 * @param spi
	 */
	public void insertSuchprofilInfo(SuchprofilInfo spi) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO suchprofil_info (suchprofilname, email, info_id) VALUES "
				+ "('" + spi.getSp().getSuchprofilname() + "','" + spi.getProfil().getEmail() + "',"+spi.getInfo().getId()+")");
		stmt.execute();
	}

	/**
	 * Gibt alle Infos mit zugehöriger Eigenschaft zu eine Suchprofil aus
	 * 
	 * @param infoID
	 * @return pe
	 */
	public ProfilEigenschaft getSPInfosByInfoID(int infoID) throws Exception{
		Connection conn = DBConnection.connection();
		PreparedStatement select = conn.prepareStatement("SELECT infos.value, "
				+ "infos.info_id, eigenschaft.auswahl, eigenschaft.erlauterung, eigenschaft.eigenschaft_id "
				+ "FROM infos JOIN eigenschaft ON infos.eigenschaft_id = "
				+ "eigenschaft.eigenschaft_id WHERE info_id = " + infoID);
		ResultSet result = select.executeQuery();
		ProfilEigenschaft pe = new ProfilEigenschaft();
		while(result.next()){
			Info info = new Info();
			info.setEigenschaft(result.getInt("eigenschaft_id"));
			info.setId(result.getInt("info_id"));
			info.setValue(result.getString("value"));
			
			Eigenschaft eigenschaft = new Eigenschaft();
			eigenschaft.setErlaeuterung(result.getString("erlauterung"));
			eigenschaft.setId(result.getInt("eigenschaft_id"));
			eigenschaft.setAuswahl(result.getInt("auswahl"));

			pe.setInfo(info);
			pe.setEigenschaft(eigenschaft);
		}
		return pe;
	}
	
	/**
	 * Löscht die Verbindung zwischen einem Suchprofil und einer Info
	 * 
	 * @param spi
	 */
	public void deleteSuchprofilInfo(SuchprofilInfo spi) throws Exception{
		Connection conn = DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM suchprofil_info WHERE email = '" + spi.getProfil().getEmail() + 
				"' AND info_id="+ spi.getInfo().getId() + " "
						+ "AND suchprofilname='" +spi.getSp().getSuchprofilname()+"'");
		stmt.execute();
	}
	
	/**
	 * Löscht die Verbindung zwischen ALLEN Infos zu einem Suchprofil
	 * 
	 * @param sp
	 * @param user
	 */
	public void deleteAllSuchprofilInfos(Suchprofil sp, Profil user) throws Exception{
		Connection conn = DBConnection.connection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM suchprofil_info WHERE suchprofilname = '" + sp.getSuchprofilname() + "' "
						+ "AND email = '" + user.getEmail() + "'");
		stmt.execute();
	}
	
	/**
	 * Gibt alle Infos zu einem Suchprofil aus
	 * 
	 * @param email
	 * @param spname
	 * @return suchprofilinfos
	 */
	public Vector <ProfilEigenschaft> getSuchprofilInfosByEmail(String email, String spname) throws Exception{
		Connection conn = DBConnection.connection();
		PreparedStatement select = conn.prepareStatement("SELECT info_id FROM "
				+ "suchprofil_info WHERE email = '" + email + "' AND suchprofilname = '" + spname + "'");
		Vector<ProfilEigenschaft> suchprofilinfos = new Vector<ProfilEigenschaft>();
		ResultSet result = select.executeQuery();
		while(result.next()){
			ProfilEigenschaft pi = getSPInfosByInfoID(result.getInt("info_id"));
			suchprofilinfos.add(pi);
		}
		return suchprofilinfos;	
	}
	
}
