package facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import servidor.Db;
import servidor.Farmacia;

public class FarmaciaFacade {
	
	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;	
	PreparedStatement pstmt = null;
	
	public FarmaciaFacade() {
		try {
			this.database = new Db();
			this.con = this.database.getDB();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean newFarmacia(Farmacia f) {
		
		String insertarFarmacia = "INSERT INTO FARMACIA"
				+ "(NOMBRE,LATITUD,LONGITUD) VALUES"
				+ "(?,?,?)";

		try {
			pstmt = this.con.prepareStatement(insertarFarmacia);
			pstmt.setString(1, f.getNombre());
			pstmt.setFloat(2, f.getLatitud());
			pstmt.setFloat(3, f.getLongitud());
			System.out.println(pstmt);			
			
			pstmt.execute();
			this.con.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();			
		}
		
		return false;
	}
	
	public boolean updateFarmacia(Farmacia f) {
		
		String updateFarmacia = "UPDATE FARMACIA SET NOMBRE=?, LATITUD=?, LONGITUD=? where ID=?";
		try {
			pstmt= this.con.prepareStatement(updateFarmacia);
			pstmt.setString(1, f.getNombre());
			pstmt.setFloat(2, f.getLatitud());
			pstmt.setFloat(3, f.getLongitud());
			pstmt.setInt(4, f.getID());
			System.out.println(pstmt);
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteFarmacia(Farmacia f) {	
		String deleteFarmacia = "DELETE FROM FARMACIA WHERE ID=?";
		try {
			pstmt= this.con.prepareStatement(deleteFarmacia);
			pstmt.setInt(1, f.getID());
			System.out.println(pstmt);	
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Farmacia> getFarmacias(){
		String getUsers = "SELECT * FROM FARMACIA";
		ArrayList<Farmacia> farmacias= new ArrayList<Farmacia>();
		
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getUsers);
			
			while(rs.next()) {
				farmacias.add(new Farmacia(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getFloat("LATITUD"),rs.getFloat("LONGITUD")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return farmacias;
	}
}
