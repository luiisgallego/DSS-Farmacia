import java.sql.SQLException;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import servidor.Db;
import servidor.Db2;

public class Test {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		//Producto p = new Producto(9999, "producto json");
		
		/*ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource servicio = client.resource(UriBuilder.fromUri("http://localhost:8080/DSS-P4/rest").build());*/
		
		Db bd = new Db();
		
	}

}
