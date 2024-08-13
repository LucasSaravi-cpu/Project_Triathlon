package dataaccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	//------------------------------------------------>||VARIABLE||<--------------------------------------------------------\\

	private Connection connection = null;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public DBManager(String _driver, String _url, String _username, String _password) {
		super();
		try { 
			Class.forName(_driver);
			connection = DriverManager.getConnection(_url, _username, _password);
		} 
		catch (ClassNotFoundException ex) {
			System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	public Connection getConnection() {
		return connection;
	}
	
}
