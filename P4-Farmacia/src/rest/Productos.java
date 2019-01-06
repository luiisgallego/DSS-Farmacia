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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import facade.ProductoFacade;
import servidor.Producto;

@Path("/productos")
public class Productos {
	private ProductoFacade productoFacade;
	private Gson gson = new Gson();
	
	public Productos(){ 
		productoFacade = new ProductoFacade(); 
	}
	
	@POST	// CREAR
	@Produces( MediaType.TEXT_HTML )
	@Consumes( MediaType.APPLICATION_FORM_URLENCODED )
	public void postProducto(@FormParam("productoNombre") String nombre,
			@FormParam("productoCantidad") int cantidad,
			@FormParam("productoPrecio") int precio,
			@Context HttpServletResponse servletResponse ) throws IOException{
		
		Producto producto = new Producto(0, nombre, cantidad, precio,null); 
		boolean postOK = productoFacade.newProducto(producto);
		
		// Redirigir ??
		servletResponse.sendRedirect("../rest/productos");
		
		//if(putOK) return Response.status(200).entity("PutOK farmacia").build();
		//else return Response.status(404).entity("Error PUT farmacia").build();		
	}
	
	/*@PUT	// INSERTAR
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putFarmacia(servidor.Farmacia farmacia){
		boolean putOK = farmaciaFacade.newFarmacia(farmacia);
		
		if(putOK) return Response.status(200).entity("PutOK farmcia").build();
		else return Response.status(404).entity("Error PUT farmacia").build();
	}*/
	
	//@Path("/getFarmacias") // En get no debería ser necesario
	@GET	
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getFarmacias(){
		ArrayList<servidor.Producto> productos = productoFacade.getProductos();
		String farmaciasJSON = gson.toJson(productos);
		return Response.status(200).entity(farmaciasJSON).build();
	}
}
