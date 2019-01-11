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


@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String URL = "http://localhost:8080/DSS-P4/rest";
	private String opcion;
	Gson gson = new Gson();
	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(); 		

	Client client = ClientBuilder.newClient();
	WebTarget servicio;
	Response respuesta;
	HttpServletResponse responseRedirect; 
       
    
    public OrdersServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tomamos la opción enviada en el formulario o en el query
		opcion = request.getParameter("opcionServlet");
		map.clear();
		
		switch(opcion){
		
		case "getPedidos":
			servicio = client.target(URL).path("/pedidos");
			String res = servicio.request().get(String.class);
			
			HttpSession session = request.getSession();
			session.setAttribute("getPedidosSesion", res);			
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tomamos la opción enviada en el formulario
		opcion = request.getParameter("opcionServlet");
		map.clear();
		
		// Recogemos los datos y realizacion la peticion que corresponda
		switch(opcion){
		
		case "addPedidos":					
			map.add("orderPrecio", request.getParameter("precio"));
			map.add("orderUsuarioID", request.getParameter("usuarioID"));
			map.add("orderProductoID", request.getParameter("productoID"));
			map.add("orderFarmaciaID", request.getParameter("farmaciaID"));	
			
			servicio = client.target(URL).path("/pedidos");	
			Response respuesta = servicio.request().post(Entity.form(map));
			
			response.sendRedirect("http://localhost:8080/DSS-P4/principal.jsp");	
			break;
		}
	}
}
