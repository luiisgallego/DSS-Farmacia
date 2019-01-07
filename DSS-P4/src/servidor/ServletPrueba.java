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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rest.Productos;

/**
 * Servlet implementation class ServletFarmacia
 */
@WebServlet("/ServletPrueba")
public class ServletPrueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String URL = "http://localhost:8080/DSS-P4/rest";
	private Gson gson = new Gson();
	
	Client client = ClientBuilder.newClient();
	//Response respuesta;
	WebTarget servicio;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrueba() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		Productos producto = new Productos();
		
		System.out.println("Dentro de Servlet");
		servicio = client.target(URL).path("/productos");
		String result = servicio.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
		//JsonObject obj = new JsonObject();
	    //JsonArray productosObtenidos = obj.getAsJsonArray("result"); 

		//System.out.println(productosObtenidos.toString());	
		System.out.println(result);	
		
		HttpSession session = request.getSession();
		session.setAttribute("prueba", result);
		
		
		
		

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("http://localhost:8080/DSS-P4/test.jsp");
	}

	

}
