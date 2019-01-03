package rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import facade.UsuarioFacade;

@Path("/usuarios")
public class Usuarios {
	
	private UsuarioFacade usuarioFacade;
	
	public Usuarios() {
		usuarioFacade = new UsuarioFacade();
	}
	
	@GET	
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<servidor.Usuario> getUsuarios(){
		ArrayList<servidor.Usuario> usuarios = usuarioFacade.getUsuarios();
		return usuarios;
	}
}
