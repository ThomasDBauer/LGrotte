package de.server.db;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import de.shared.BO.Auswahl;
import de.shared.BO.Eigenschaft;
	/**
	 * Die AuswahlMapper ist zuständig für unsere Auswahleigenschaften
	 * 
	 * @author Thomas Bauer
	 * 
	 * @version 1.0
	 */
public class AuswahlMapper {
	
	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static AuswahlMapper auswahlmapper = null;
	
	/**
	 * Protected (geschützer) Konstruktur
	 */
	protected AuswahlMapper(){
	}
	
	/**
	 * Statische Methode vom Typ AuswahlMapper
	 * 
	 * @return auswahlmapper
	 */
	public static AuswahlMapper auswahlMapper(){
		if(auswahlmapper==null){
			auswahlmapper = new AuswahlMapper();
		}
		return auswahlmapper;
	}
	
	/**
	 * Erstellt eine Tabelle für unsere verschiedenen Auswahlmöglichkeiten
	 */
	public void createAuswahlTable() throws Exception {
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement create =(PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS auswahl (auswahl_id int NOT NULL AUTO_INCREMENT, "
				+ "value varchar(50), eigenschaft_id int, PRIMARY KEY(auswahl_id), "
				+ "FOREIGN KEY(eigenschaft_id) REFERENCES eigenschaft(eigenschaft_id) "
				+ "ON UPDATE CASCADE ON DELETE RESTRICT)");
		create.execute();
	}	
	
	/**
	 * Fügt eine gewählte Auswahleigenschaft hinzu
	 * 
	 * @param a
	 */
	public void insertAuswahl(Auswahl a) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) con.prepareStatement(
				"INSERT INTO auswahl (eigenschaft_id, value)"
				+ " VALUES ("+a.getEigenschaft_id()+ ",'"+a.getValue()+"')");
		insert.execute();
	}
	
	/**
	 * Liest die Auswahleigenschaften aus der Datenbank 
	 * 
	 * @param e
	 * @return auswahlopts
	 */
	public Vector<Auswahl> getAuswahlForEigenschaft(Eigenschaft e) throws Exception{
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement create =(PreparedStatement) con.prepareStatement(
				"SELECT value, eigenschaft_id FROM auswahl WHERE "
				+ "eigenschaft_id="+e.getId());
		ResultSet result = create.executeQuery();
		Vector<Auswahl>auswahlopts = new Vector<Auswahl>();
		while(result.next()){
			Auswahl a = new Auswahl();
			a.setValue(result.getString("value"));
			a.setEigenschaft_id(result.getInt("eigenschaft_id"));
			auswahlopts.add(a);
		}
		return auswahlopts;
	}
}