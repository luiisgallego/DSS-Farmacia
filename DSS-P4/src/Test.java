import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.WebResource;
import org.glassfish.jersey.client.ClientConfig;
/*
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;*/

public class Test {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		// Prueba 1
		// Db bd = new Db();
		
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
		
	}
}
