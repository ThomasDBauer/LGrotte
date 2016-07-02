package de.server.db;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Die DBConnection baut eine Verbindung zur Datenbank auf 
 * 
 * @author Thomas Bauer
 * 
 * @version 1.0
 */
public class DBConnection {

	/**
	 * Statische Variable, und ist deßhalb für alle Instanzen 
	 * dieser Klasse verfügbar
	 */
	private static Connection con = null;

	/**
	 * Baut Verbindung zur Datenbank auf
	 * 
	 * @return con
	 */
	public static Connection connection() throws Exception {

		if (con == null) {
			try {
				

				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/lg";
				String password = "";
				
				
				String username = "root";
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return con;

	}

}
