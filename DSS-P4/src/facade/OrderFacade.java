package facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import servidor.Db;
import servidor.Order;

public class OrderFacade {
	
	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	public OrderFacade() {
		try {
			this.database = new Db();
			this.con = this.database.getDB();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// No sabemos para que es el campo ID_ORDER en la base de datos.
	public boolean newOrder(Order o) {
		String insertarOrder = "INSERT INTO ORDERS"
				+ "(PRECIO,fk_producto,fk_farmacia,fk_usuario) VALUES"
				+ "(?,?,?,?)";
		System.out.println(insertarOrder);

		try {
			pstmt = this.con.prepareStatement(insertarOrder);
			pstmt.setInt(1, o.getPrecio());
			pstmt.setInt(2, o.getProductoID());
			pstmt.setInt(3, o.getFarmaciaID());
			pstmt.setInt(4, o.getUsuarioID());
			
			pstmt.execute();
			this.con.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public boolean updateOrder(Order o) {
		
		String updateOrder = "UPDATE ORDERS SET PRECIO=?, fk_producto=?, fk_farmacia=?, fk_usuario=? where ID=?";
		try {
			pstmt= this.con.prepareStatement(updateOrder);
			pstmt.setInt(1, o.getPrecio());
			pstmt.setInt(2, o.getProductoID());
			pstmt.setInt(3, o.getFarmaciaID());
			pstmt.setInt(4, o.getUsuarioID());
			pstmt.setInt(5, o.getID());
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteOrder(Order o) {
		
		String deleteOrder = "DELETE FROM ORDERS WHERE ID=?";
		try {
			pstmt= this.con.prepareStatement(deleteOrder);
			pstmt.setInt(1, o.getID());
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Order> getOrder(){
		String getUsers = "SELECT * FROM ORDERS";
		ArrayList<Order> orders= new ArrayList<Order>();
		
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getUsers);
			
			while(rs.next()) {
				orders.add(new Order(rs.getInt("ID"),rs.getInt("PRECIO"),rs.getInt("fk_usuario"),rs.getInt("fk_producto"),rs.getInt("fk_farmacia")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
}
