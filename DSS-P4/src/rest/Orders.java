package rest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.OrderFacade;
import servidor.Order;

@Path("/pedidos")
public class Orders {
	
	private OrderFacade orderFacade;
	private Gson gson = new Gson();
	
	public Orders(){
		orderFacade = new OrderFacade();
	}
	
	
	@POST	// CREAR
	@Produces(MediaType.TEXT_PLAIN)
	public Response postOrder(@FormParam("orderPrecio") int precio,
			@FormParam("orderUsuarioID") int usuarioID,
			@FormParam("orderProductoID") int productoID,
			@FormParam("orderFarmaciaID") int farmaciaID,
			@Context HttpServletResponse servletResponse) throws IOException{
		
		Order order = new Order(0, precio, usuarioID, productoID, farmaciaID); 
		boolean postOK = orderFacade.newOrder(order);
		
		if(postOK) return Response.status(200).build();
		else return Response.status(404).build();				 
	}
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmacias(){
		ArrayList<servidor.Order> orders = orderFacade.getOrder();
		String ordersJSON = gson.toJson(orders);
		
		return Response.status(200).entity("{\n\"pedidos\" :" + ordersJSON+ "\n}").build();
		//return Response.status(200).entity(ordersJSON).build();
	}
}
