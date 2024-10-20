package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/Project_triathlon";
    private static final String username = "postgres";
    private static final String password = "1234";

    
   //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
