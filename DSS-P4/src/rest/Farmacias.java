package rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.FarmaciaFacade;
import servidor.Farmacia;


@Path("/farmacias")
public class Farmacias {
	
	private FarmaciaFacade farmaciaFacade;
	private Gson gson = new Gson();
	
	public Farmacias(){ 
		farmaciaFacade = new FarmaciaFacade(); 
	}
	
	@POST	// CREAR
	public Response postFarmacia(@FormParam("farmaciaNombre") String nombre,
			@FormParam("farmaciaLatitud") float latitud,
			@FormParam("farmaciaLongitud") float longitud,
			@Context HttpServletResponse servletResponse) throws IOException{
		
		System.out.println("Here2");
		
		Farmacia farmacia = new Farmacia(0, nombre, latitud, longitud); 
		boolean putOK = farmaciaFacade.newFarmacia(farmacia);	
		
		//servletResponse.sendRedirect("../rest/farmacias");		
		return Response.status(200).build();
	}
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmacias(){
		ArrayList<servidor.Farmacia> farmacias = farmaciaFacade.getFarmacias();
		String farmaciasJSON = gson.toJson(farmacias);
		
		return Response.status(200).entity(farmaciasJSON).build();
	}
	
}
