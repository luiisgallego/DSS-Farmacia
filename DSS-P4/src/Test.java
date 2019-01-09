import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.catalina.WebResource;
import org.glassfish.jersey.client.ClientConfig;
/*
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;*/

import servidor.Db;

public class Test {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException{
		
		// Prueba 1
		Db bd = new Db();
		
		// Prueba 2
		/*FarmaciaFacade farmaciaFacade = new FarmaciaFacade();
		ArrayList<Farmacia> farmacias = farmaciaFacade.getFarmacias();
		
		for(Farmacia f: farmacias){
			System.out.println("FARMACIAS:");
			System.out.println(f.getID() + " " + f.getNombre());
		}*/
		
		// Prueba 3
		/*ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource servicio = client.resource(UriBuilder.fromUri("http://localhost:8080/DSS-P4/rest").build());
		
		//System.out.println("Farmacias actuales: ");
		//System.out.println(servicio.path("farmacias").accept(MediaType.APPLICATION_JSON).get(String.class));	
		
		Farmacia farmacia = new Farmacia(5000, "PruebaFarmacia", 17, 25);
		ClientResponse respuesta = servicio.path("farmacias").accept("application/json").type("application/json").put(ClientResponse.class, farmacia);
		System.out.println(respuesta.getEntity(String.class));	*/
		
		
		MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(); 		

		Client client = ClientBuilder.newClient();
		WebTarget servicio;
		Response respuesta;
		HttpServletResponse responseRedirect;
		
		map.add("farmaciaNombre", "66666");
		map.add("farmaciaLatitud", "93849");
		map.add("farmaciaLongitud", "8437983");	
		
		System.out.println("MAP: " + map);
		
		//servicio = client.target(UriBuilder.fromUri("http://localhost:8080/DSS-P4/rest").build()).path("farmacias");
		//System.out.println(servicio);
		//respuesta = servicio.path("rest").path("farmacias").request().post(Entity.form(map));	
		
		//System.out.println("Respuesta: " + respuesta.getStatus());
		//System.out.println(servicio.request().accept(MediaType.APPLICATION_JSON).get(String.class));	
		
		//responseRedirect.sendRedirect("http://localhost:8080/DSS-P4/rest/farmacias");
		
		WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/DSS-P4/rest/").path("farmacias");
		respuesta = target.request().post(Entity.form(map));	
		//System.out.println("Respuesta: " + target.request().accept(MediaType.APPLICATION_JSON).get(String.class));
		
	}
}
