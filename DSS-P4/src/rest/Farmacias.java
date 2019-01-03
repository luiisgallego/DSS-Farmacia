package rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public ArrayList<servidor.Farmacia> getFarmcias(){
		ArrayList<servidor.Farmacia> farmacias = farmaciaFacade.getFarmacias();
		return farmacias;
	}
	

}
