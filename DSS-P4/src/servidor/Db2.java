package servidor;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db2 {
	
	public Db2(){
		
		System.out.println("------CONECTANDO BD------");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = null;
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/consorcio", "root" ,"");
			
		} catch(Exception e){
			System.out.println("Error Conexion");
		}
		
	}

}
