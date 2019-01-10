package rest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.OrderFacade;
import servidor.Order;

public class Orders {
	
	private OrderFacade orderFacade;
	private Gson gson = new Gson();
	
	public Orders(){
		orderFacade = new OrderFacade();
	}
	
	/*
	@POST	// CREAR
	@Produces(MediaType.TEXT_PLAIN)
	public Response postOrder(@FormParam("farmaciaNombre") String nombre,
			@FormParam("farmaciaLatitud") float latitud,
			@FormParam("farmaciaLongitud") float longitud,
			@Context HttpServletResponse servletResponse) throws IOException{
		
		Order order = new Order(); 
		boolean postOK = orderFacade.newOrder(order);
		
		if(postOK) return Response.status(200).build();
		else return Response.status(404).build();				 
	}*/
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmacias(){
		ArrayList<servidor.Order> orders = orderFacade.getOrder();
		String ordersJSON = gson.toJson(orders);
		
		return Response.status(200).entity(ordersJSON).build();
	}
}
