package servidor;

import java.io.IOException;
import java.util.HashMap;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rest.Productos;


@WebServlet("/ServletPrueba")
public class ServletPrueba extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String URL = "http://localhost:8080/DSS-P4/rest";
	private Gson gson = new Gson();
	private String opcion;
	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(); 	
	Client client = ClientBuilder.newClient();
	WebTarget servicio;
	Response respuesta;
	HttpServletResponse responseRedirect;
       
	
    public ServletPrueba() {
        super();
    }

    // Para las consultas (GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		//Productos producto = new Productos();
		
		System.out.println("Dentro de Servlet");
		servicio = client.target(URL).path("/farmacias");
		String result = servicio.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
		//JsonObject obj = new JsonObject();
	    //JsonArray productosObtenidos = obj.getAsJsonArray("result"); 

		//System.out.println(productosObtenidos.toString());	
		System.out.println(result);	
		
		HttpSession session = request.getSession();
		session.setAttribute("prueba", result);
		
		String json = gson.toJson(result);
	    System.out.println(json);	

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("http://localhost:8080/DSS-P4/rest/farmacias");
		
	}
	
	// Para el resto de peticiones (POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tomamos la opción enviada en el formulario
		opcion = request.getParameter("opcionServlet");
		
		// Recogemos los datos y realizacion la peticion que corresponda
		switch(opcion){
		
		case "prueba":
			map.add("farmaciaNombre", request.getParameter("farmaciaNombre"));
			map.add("farmaciaLatitud", request.getParameter("farmaciaLatitud"));
			map.add("farmaciaLongitud", request.getParameter("farmaciaLongitud"));
			
			servicio = client.target(URL).path("/farmacias");
			respuesta = servicio.request().post(Entity.form(map));	
			
			response.sendRedirect("http://localhost:8080/DSS-P4/rest/farmacias");		
			break;
		
		}				
		
		
		
	}
}
