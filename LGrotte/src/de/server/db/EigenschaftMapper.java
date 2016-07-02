package de.server.db;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;

import de.shared.BO.Eigenschaft;

/**
 * Die EigenschaftMapper ist zuständig für die Eigenschaften
 * vom Profil und Suchprofil
 * 
 * @author Thomas Bauer und Enrico Popaj
 *
 * @version 1.0
 */

public class EigenschaftMapper {

	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static EigenschaftMapper eigenschaftMapper = null;

	/**
	 * Protected (geschützer) Konstruktur
	 */
	protected EigenschaftMapper() {
	}
	
	/**
	 * Statische Methode vom Typ EigenschaftMapper
	 * 
	 * @return eigenschaftMapper
	 */
	public static EigenschaftMapper eigenschaftMapper(){
		if(eigenschaftMapper ==null){
			eigenschaftMapper = new EigenschaftMapper();
		}
		return eigenschaftMapper;
	}

	/**
	 * Erstellt eine Tabelle in der die Eigenschaften gespeichert werden
	 */
	public void createEigenschaftTable() throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement create = (PreparedStatement) conn.prepareStatement
				("CREATE TABLE IF NOT EXISTS eigenschaft (erlauterung varchar(255), "
						+ "eigenschaft_id int NOT NULL AUTO_INCREMENT, auswahl int (1), "
						+ "PRIMARY KEY(eigenschaft_id))");
		create.execute();
	}
	
	/**
	 * Fügt neue Eigenschaften in die Tabelle
	 * 
	 * @param eigenschaft
	 */
	public void insertEigenschaft(Eigenschaft eigenschaft) throws Exception {
		Connection conn = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) conn.prepareStatement
				("INSERT INTO eigenschaft(erlauterung, auswahl) VALUES ('" + 
						eigenschaft.getErlaeuterung() + "', "+eigenschaft.getAuswahl()+")");
		insert.execute();
	}
	
	/**
	 * Ruft die Eigenschaften aus der Datenbank auf
	 * 
	 * @return eigenschaften
	 */
	public Vector<Eigenschaft> getEigenschaften() throws Exception {
		Connection conn = (Connection)DBConnection.connection();
		PreparedStatement select = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM eigenschaft");
		ResultSet rs = select.executeQuery();
		Vector<Eigenschaft>eigenschaften = new Vector<Eigenschaft>();
		while(rs.next()){
			Eigenschaft e = new Eigenschaft();
			e.setErlaeuterung(rs.getString("erlauterung"));
			e.setId(rs.getInt("eigenschaft_id"));
			e.setAuswahl(rs.getInt("auswahl"));
			eigenschaften.add(e);
		}
		return eigenschaften;
	}

}
