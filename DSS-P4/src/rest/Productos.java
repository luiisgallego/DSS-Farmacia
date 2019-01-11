package rest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.ProductoFacade;
import servidor.Farmacia;
import servidor.Producto;

@Path("/productos")
public class Productos {
	private ProductoFacade productoFacade;
	private Gson gson = new Gson();
	
	public Productos(){ 
		productoFacade = new ProductoFacade(); 
	}
	
	@POST	// CREAR
	@Produces(MediaType.TEXT_PLAIN)
	public Response postProducto(@FormParam("productoNombre") String nombre,
			@FormParam("productoCantidad") int cantidad,
			@FormParam("productoPrecio") int precio) {
		
		Producto producto = new Producto(0, nombre, cantidad, precio, "null"); 
		boolean postOK = productoFacade.newProducto(producto);
		
		if(postOK) return Response.status(200).build();
		else return Response.status(404).build();		
	}
	
	@GET	
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductos(){
		ArrayList<servidor.Producto> productos = productoFacade.getProductos();
		String productosJSON = gson.toJson(productos);
		
		return Response.status(200).entity("{\n\"productos\" :" + productosJSON+ "\n}").build();		 
	}
	
	@PUT	// EDITAR
	@Produces(MediaType.TEXT_PLAIN)
	public Response putProductos(@FormParam("ID") int ID,
			@FormParam("nombre") String nombre,
			@FormParam("cantidad") int cantidad,
			@FormParam("precio") int precio){
		
		System.out.println("ID: "+ ID);
		Producto producto = new Producto(ID, nombre, cantidad, precio, "null"); 
		boolean postOK = productoFacade.updateProducto(producto);			
		
		if(postOK) return Response.status(200).build();
		else return Response.status(404).build();				 
	}
	
	@DELETE
	@Path("{ID}")
	public Response deleteFarmacias(@PathParam("ID") int ID){
		Producto producto = new Producto(ID, "null", 0, 0, "null"); 	
		boolean deleteOK = productoFacade.deleteProducto(producto);
		
		if(deleteOK) return Response.status(200).build();
		else return Response.status(404).build();
	}
}
