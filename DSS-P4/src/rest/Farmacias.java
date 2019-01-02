package rest;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import facade.FarmaciaFacade;
//import servidor.Producto;
import servidor.Usuario;

@Path("/farmacia")
public class Farmacias {
	
	private FarmaciaFacade farmafac;
	
	public Farmacias() {
		farmafac = new FarmaciaFacade();
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public ArrayList<servidor.Farmacia> getFarmacias(){
		
		ArrayList<servidor.Farmacia> farmacias = farmafac.getFarmacias();
		
		return farmacias;
	}

}
