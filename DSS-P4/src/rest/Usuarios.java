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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.UsuarioFacade;
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
	public Response postUsuario(@Context HttpServletResponse servletResponse) throws IOException {
		servletResponse.sendRedirect("http://localhost:8080/DSS-P4/rest/farmacias");		
		System.out.println("PROBANDOO");
		
		return Response.status(201).entity("OK").build();
		
	}
	
	/*@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public String getUsuarios(){
		ArrayList<servidor.Usuario> usuarios = usuarioFacade.getUsuarios();
		// Tenemos que pasar los usuarios a JSON
		String usuariosJSON = gson.toJson(usuarios);
		return usuariosJSON;
	}*/
	
	@POST	
	@Path("login")
	@Produces( MediaType.TEXT_HTML )
	public Response login(@FormParam("username") String username, @FormParam("password") String password){ 		
		Response respuesta = usuarioFacade.getUsuario(username, password);	
		return respuesta;
	}
	
}
