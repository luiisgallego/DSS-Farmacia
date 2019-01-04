package rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.UsuarioFacade;

@Path("/usuarios")
public class Usuarios {
	
	private UsuarioFacade usuarioFacade;
	private Gson gson = new Gson();
	
	public Usuarios() {
		usuarioFacade = new UsuarioFacade();
	}
	
	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response updateUsuario(servidor.Usuario usuario) {
		boolean updateOK = usuarioFacade.updateUsuario(usuario);
		
		if(updateOK) return Response.status(200).entity("UpdateOK usuario").build();
		else return Response.status(404).entity("Error POST usuario").build();
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
