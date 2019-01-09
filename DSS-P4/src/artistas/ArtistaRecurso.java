package artistas;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

public class ArtistaRecurso {
	
	// Objetos contextuales
	@Context
    UriInfo uriInfo;
	@Context
	Request request;	
	Integer ID;
	
	public ArtistaRecurso(UriInfo uriInfo, Request request, Integer ID){
		this.uriInfo = uriInfo;
		this.request = request;
		this.ID = ID;
	}
	
	// Insertamos
	@PUT
	@Consumes( MediaType.APPLICATION_XML )
	public Response putArtista(JAXBElement<Artista> artista){
		Artista aux = artista.getValue();
		Response res;
		
		if(ArtistaDao.INSTANCE.getModel().containsKey(aux.getID())) res = Response.noContent().build(); 
		else res = Response.created(uriInfo.getAbsolutePath()).build();	// Si no existe, lo creamos
		
		ArtistaDao.INSTANCE.getModel().put(aux.getID(), aux);		
		return res;
	}
	
	// Consultamos
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Artista getArtista(){		// Para la aplicacion
		Artista artista = ArtistaDao.INSTANCE.getModel().get(ID);			
		if(artista == null) throw new RuntimeException("GET - Artista con " + ID + " no existe");	
		return artista;		
	}
	
	@GET
	@Produces( MediaType.TEXT_XML )
	public Artista getArtistaHTML(){	// Para el navegador
		Artista artista = ArtistaDao.INSTANCE.getModel().get(ID);			
		if(artista == null) throw new RuntimeException("GET - Artista con " + ID + " no existe"); 	
		return artista;
	}
	
	// Borramos
	@DELETE
	public void deleteArtista(){
		Artista aux = ArtistaDao.INSTANCE.getModel().remove(ID);
		if(aux == null) throw new RuntimeException("DELETE - Artista con " + ID + " no existe"); 
	}
}
