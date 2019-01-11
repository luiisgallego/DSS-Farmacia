package rest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.UsuarioFacade;
import servidor.Farmacia;
import servidor.Usuario;

@Path("/usuarios")
public class Usuarios {
	
	private UsuarioFacade usuarioFacade;
	private Gson gson = new Gson();
	
	public Usuarios() {
		usuarioFacade = new UsuarioFacade();
	}
	
	@POST	// CREAR
	@Produces( MediaType.TEXT_HTML )
	@Path("registro")
	public Response postUsuario(@FormParam("nombre") String nombre,
			@FormParam("nick") String nick,
			@FormParam("pass") String pass,
			@FormParam("rol") String rol,
			@FormParam("email") String email) {
		
		Usuario usuario = new Usuario(0, nick, nombre, rol,email, pass); 
		boolean postOK = usuarioFacade.newUsuario(usuario);
		
		if(postOK) return Response.status(200).build();
		else return Response.status(404).build();		
	}
	
	@POST	
	@Produces( MediaType.TEXT_HTML )	
	@Path("login")
	public Response login(@FormParam("username") String username, @FormParam("password") String password){ 		
		Response respuesta = usuarioFacade.getUsuario(username, password);	
		return respuesta;
	}
	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response getUsuarios(){
		ArrayList<servidor.Usuario> usuarios = usuarioFacade.getUsuarios();
		String usuariosJSON = gson.toJson(usuarios);
		
		return Response.status(200).entity("{\n\"usuarios\" :" + usuariosJSON+ "\n}").build();
	}
	
	@GET	
	@Produces( MediaType.TEXT_HTML )	
	@Path("getMovil/{nick}")
	public Response getMovil(@PathParam("nick") String nick){ 		
		Response respuesta = usuarioFacade.getUsuarioNick(nick);	
		return respuesta;
	}	
}
