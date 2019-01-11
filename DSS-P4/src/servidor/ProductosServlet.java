package servidor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;


@WebServlet("/ProductosServlet")
public class ProductosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String URL = "http://localhost:8080/DSS-P4/rest";
	private String opcion;
	Gson gson = new Gson();
	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(); 		

	Client client = ClientBuilder.newClient();
	WebTarget servicio;
	Response respuesta;
	HttpServletResponse responseRedirect;        

    public ProductosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tomamos la opción enviada en el formulario o en el query
		opcion = request.getParameter("opcionServlet");
		map.clear();
		
		switch(opcion){
		
		case "getProductos":
			servicio = client.target(URL).path("/productos");
			String res = servicio.request().get(String.class);
			
			HttpSession session = request.getSession();
			session.setAttribute("getProductosSesion", res);			
			break;
			
		case "deleteProductos":			
			String aux = "/productos/"+request.getParameter("ID");
			servicio = client.target(URL).path(aux);	
			Response respuesta = servicio.request().delete();
			
			response.sendRedirect("http://localhost:8080/DSS-P4/principal.jsp");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tomamos la opción enviada en el formulario
		opcion = request.getParameter("opcionServlet");
		map.clear();
		
		// Recogemos los datos y realizacion la peticion que corresponda
		switch(opcion){
		
		case "addProductos":
			map.add("productoNombre", request.getParameter("productoNombre"));
			map.add("productoCantidad", request.getParameter("productoCantidad"));
			map.add("productoPrecio", request.getParameter("productoPrecio"));			
			
			servicio = client.target(URL).path("/productos");	
			Response respuesta = servicio.request().post(Entity.form(map));
			
			response.sendRedirect("http://localhost:8080/DSS-P4/principal.jsp");	
			break;	
			
		case "editarProductos":						
			map.add("ID", request.getParameter("ID"));
			map.add("nombre", request.getParameter("productoNombre"));
			map.add("cantidad", request.getParameter("productoCantidad"));
			map.add("precio", request.getParameter("productoPrecio"));
			
			servicio = client.target(URL).path("productos");
			Response respuesta2 = servicio.request().put(Entity.form(map));
			
			response.sendRedirect("http://localhost:8080/DSS-P4/principal.jsp");			
			break;
			
		}
	}
}
