package de.server.db;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import de.shared.BO.Auswahl;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Profil;

public class AuswahlMapper {
	private static AuswahlMapper auswahlmapper = null;
	
	protected AuswahlMapper(){
		
	}
	
	public static AuswahlMapper auswahlMapper(){
		if(auswahlmapper==null){
			auswahlmapper = new AuswahlMapper();
		}
		return auswahlmapper;
	}
	
	
	public void createAuswahlTable() throws Exception {
		
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement create =(PreparedStatement) con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS auswahl (auswahl_id int NOT NULL AUTO_INCREMENT, "
				+ "value varchar(50), eigenschaft_id int, PRIMARY KEY(auswahl_id), "
				+ "FOREIGN KEY(eigenschaft_id) REFERENCES eigenschaft(eigenschaft_id) "
				+ "ON UPDATE CASCADE ON DELETE RESTRICT)");
		create.execute();
	}	
	
	public void insertAuswahl(Auswahl a) throws Exception{
		
		Connection con = (Connection) DBConnection.connection();
		PreparedStatement insert = (PreparedStatement) con.prepareStatement(
				"INSERT INTO auswahl (eigenschaft_id, value)"
				+ " VALUES ("+a.getEigenschaft_id()+ ",'"+a.getValue()+"')");
		insert.execute();
	}
	
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