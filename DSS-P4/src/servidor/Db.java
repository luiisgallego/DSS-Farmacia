package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	String url = "jdbc:mysql://localhost:3306/consorcio";
	String username = "root";
	//String password = "root";
	String password = "";
	
	private Connection connection;

	public Db() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		try {	// Conectamos
			//System.out.println("------CONECTANDO BD------");
			this.connection = DriverManager.getConnection(url, username,password);
			//System.out.println("------BD CONECTADA------");			
		}catch(SQLException ex) {
			System.out.println("-----ERROR SQL-----");
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch(Exception e){
			System.out.println("-----ERROR CONEXIÓN BD-----");
		}		
	}
	public Connection getDB() {
		return this.connection;
	}
}
