package artistas;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.sun.research.ws.wadl.Response;

public class ArtistaTest {
	
	public static void main(String[] args){		
		ClientConfig config = new ClientConfig();
		Client cliente = ClientBuilder.newClient(config);
		WebTarget servicio = cliente.target(getBaseURI());
		
		System.out.println("Artistas actuales: ");
		System.out.println(servicio.path("rest").path("artistas").request().accept(MediaType.TEXT_XML).get(String.class));	
		
		System.out.println("Creamos un nuevo artista: ");
		Artista artista = new Artista(10, "Oscar Mulero", "Techno", 50, "Embajador español por el mundo");
		servicio.path("rest").path("artistas").path(artista.getID().toString()).request().accept(MediaType.APPLICATION_XML).put(Entity.entity(artista, MediaType.APPLICATION_XML), Response.class);
		System.out.println("Mostramos los artistas actuales: ");
		System.out.println(servicio.path("rest").path("artistas").request().accept(MediaType.TEXT_XML).get(String.class));
		
		System.out.println("Borramos ahora el primer artista:");
		servicio.path("rest").path("artistas/1").request().delete();
		System.out.println("Comprobamos:");
		System.out.println(servicio.path("rest").path("artistas").request().accept(MediaType.TEXT_XML).get(String.class));
		
		System.out.println("Fin TEST");		
	}
	
	private static URI getBaseURI(){
		return UriBuilder.fromUri("http://localhost:8080/GallegoLuis-P2").build();
	}
}
