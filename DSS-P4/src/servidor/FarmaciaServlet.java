package servidor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

/**
 * Servlet implementation class FarmaciaServlet
 */
@WebServlet("/FarmaciaServlet")
public class FarmaciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String URL = "http://localhost:8080/DSS-P4/rest";
	private Gson gson = new Gson();
	private String opcion;
	 		

	
	
	
	HttpServletResponse responseRedirect;
       
    
    public FarmaciaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Tomamos la opción enviada en el formulario
		opcion = request.getParameter("opcionServlet");
		//map.clear();
		
		// Recogemos los datos y realizacion la peticion que corresponda
		switch(opcion){
		
		case "prueba":
			Client client = ClientBuilder.newClient();
			MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();
			
			System.out.println("Here");
			
			map.add("farmaciaNombre", request.getParameter("farmaciaNombre"));
			map.add("farmaciaLatitud", request.getParameter("farmaciaLatitud"));
			map.add("farmaciaLongitud", request.getParameter("farmaciaLongitud"));	
			
			System.out.println(map);
			
			WebTarget servicio = client.target(URL).path("/farmacias");
			System.out.println(servicio);
			servicio.request().post(Entity.form(map));
			//Response respuesta = servicio.request().post(Entity.form(map));	
			//System.out.println(respuesta.getStatus());
			
			//response.sendRedirect("http://localhost:8080/DSS-P4/rest/farmacias");		
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
		
	}

}
