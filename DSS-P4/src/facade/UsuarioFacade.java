package facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.core.Response;

import servidor.Db;
import servidor.Usuario;

public class UsuarioFacade {
	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	public UsuarioFacade() {
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
	
	public boolean newUsuario(Usuario u) {
		String insertarUsuario = "INSERT INTO USER"
				+ "(NOMBRE,NICK,ROL,EMAIL,PASS) VALUES"
				+ "(?,?,?,?,?)"; 

		try {
			pstmt = this.con.prepareStatement(insertarUsuario);
			pstmt.setString(1, u.getNombre()); 
			pstmt.setString(2, u.getNick());
			pstmt.setString(3, u.getRol());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getPass());
			
			System.out.println(pstmt);
			pstmt.execute(); 
			this.con.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();			
		}
		
		return false;
	}
	
	public boolean updateUsuario(Usuario u) {
		
		String updateUsuario = "UPDATE USER SET NOMBRE=?, NICK=?, ROL=?, EMAIL=?, PASS=? where ID=?";
		try {
			pstmt= this.con.prepareStatement(updateUsuario);
			pstmt.setString(1, u.getNombre());
			pstmt.setString(2, u.getNick());
			pstmt.setString(3, u.getRol());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getPass());
			pstmt.setInt(6, u.getID());
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteUsuario(Usuario u) {
		
		String deleteUsuario = "DELETE FROM USER WHERE ID=?";
		try {
			pstmt= this.con.prepareStatement(deleteUsuario);
			pstmt.setInt(1, u.getID());
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Usuario> getUsuarios(){
		String getUsers = "SELECT * FROM USER";
		ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
		
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getUsers);
			
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getInt("ID"),rs.getString("NICK"),rs.getString("NOMBRE"),rs.getString("ROL"),rs.getString("EMAIL"),rs.getString("PASS")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public Response getUsuario(String username, String password){
		String getUsers = "SELECT * FROM USER WHERE NOMBRE="+username+"PASS="+password;
		ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
		Integer status;
		System.out.println(getUsers);
		
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getUsers);
			
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getInt("ID"),rs.getString("NICK"),rs.getString("NOMBRE"),rs.getString("ROL"),rs.getString("EMAIL"),rs.getString("PASS")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		if(usuarios == null || usuarios.size() == 0) status = 404;
		else status = 200;
		
		return Response.status(status).build();
	}
	
	
	
}
