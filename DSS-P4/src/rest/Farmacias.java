package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.2/ --> Para importar la libreria
import com.google.gson.Gson;


import facade.FarmaciaFacade;

@Path("/farmacias")
public class Farmacias {
	
	private FarmaciaFacade farmaciaFacade;
	
	public Farmacias(){
		farmaciaFacade = new FarmaciaFacade();
	}
	
	@PUT	
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putFarmacia(servidor.Farmacia farmacia){
		boolean putOK = farmaciaFacade.newFarmacia(farmacia);
		
		if(putOK) return Response.status(200).entity("PutOK farmcia").build();
		else return Response.status(404).entity("Error PUT farmacia").build();
	}
	
	@GET	
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<servidor.Farmacia> getFarmacias(){
		ArrayList<servidor.Farmacia> farmacias = farmaciaFacade.getFarmacias();
		// Antes de devolver este array, habr� que pasarlo a JSON
		// https://kodejava.org/how-do-i-convert-array-into-json/
		
		
		
		return farmacias;
	}
	

}
