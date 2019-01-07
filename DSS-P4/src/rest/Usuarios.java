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
	@Consumes( MediaType.APPLICATION_FORM_URLENCODED )
	public void postUsuario(@FormParam("usuarioNick") String nick,
			@FormParam("usuarioNombre") String nombre,
			@FormParam("usuarioRol") String rol,
			@FormParam("usuarioEmail") String email,
			@FormParam("usuarioPass") String pass,
			@Context HttpServletResponse servletResponse ) throws IOException{
		
				Usuario usuario = new Usuario(0,nick,nombre,rol,email,pass); 
				boolean postOK = usuarioFacade.newUsuario(usuario);
		
				// Redirigir ??
				servletResponse.sendRedirect("../rest/usuarios");
	}
	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public String getUsuarios(){
		ArrayList<servidor.Usuario> usuarios = usuarioFacade.getUsuarios();
		// Tenemos que pasar los usuarios a JSON
		String usuariosJSON = gson.toJson(usuarios);
		return usuariosJSON;
	}
	
	/*@PUT	
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putUsuario(servidor.Usuario usuario){
		boolean putOK = usuarioFacade.newUsuario(usuario);
		
		if(putOK) return Response.status(200).entity("PutOK usuario").build();
		else return Response.status(404).entity("Error PUT usuario").build();
	}
	
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON})
	public Response deleteUsuario(servidor.Usuario usuario) {
		boolean deleteOK = usuarioFacade.deleteUsuario(usuario);
		
		if(deleteOK) return Response.status(200).entity("DeleteOK usuario").build();
		else return Response.status(404).entity("Error DELETE usuario").build();
	}*/	
	
}
