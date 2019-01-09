package artistas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/artistas")
public class ArtistasRecurso {
	
	// Objetos contextuales
	@Context
    UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Artista> getArtistas(){ // Para la aplicacion
		List<Artista> artistas = new ArrayList<Artista>();
		artistas.addAll(ArtistaDao.INSTANCE.getModel().values());
		return artistas;
	}
	
	@GET
	@Produces( MediaType.TEXT_XML )
	public List<Artista> getArtistasHTML(){ // Para el navegador
		List<Artista> artistas = new ArrayList<Artista>();
		artistas.addAll(ArtistaDao.INSTANCE.getModel().values());
		return artistas;		
	}
	
	@GET
	@Produces( MediaType.TEXT_PLAIN )
	@Path("cont")
	public String getCount(){
		int cont = ArtistaDao.INSTANCE.getModel().size();
		return "Actualmente hay " + String.valueOf(cont) + " artistas";
	}
	
	// Creamos un nuevo artista
	@POST
	@Produces( MediaType.TEXT_HTML )
	@Consumes( MediaType.APPLICATION_FORM_URLENCODED )
	public void newArtista(
			@FormParam("ID") Integer ID,
			@FormParam("nombre") String nombre,
			@FormParam("tipo") String tipo,
			@FormParam("edad") Integer edad,
			@FormParam("descripcion") String descripcion,
			@Context HttpServletResponse servletResponse) throws IOException{
		
		Artista artista = new Artista(ID, nombre, tipo, edad, descripcion);
		
		// Ajustes y comprobaciones
		if(nombre == null) artista.setNombre(ID + "_NoNombre");
		if(edad < 0) artista.setEdad(0);
		
		// Creamos
		ArtistaDao.INSTANCE.getModel().put(ID, artista);
		
		servletResponse.sendRedirect("../crearArtista.html");
	}
		
	// Para poder pasarle argumentos a las operaciones en el servidor
	@Path("{artista}")
	public ArtistaRecurso getArtista(@PathParam("artista") Integer ID){
		return new ArtistaRecurso(uriInfo, request, ID);
	}
}
