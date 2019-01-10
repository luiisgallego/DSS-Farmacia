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


@WebServlet("/FarmaciasServlet")
public class FarmaciasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String URL = "http://localhost:8080/DSS-P4/rest";
	private String opcion;
	Gson gson = new Gson();
	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(); 		

	Client client = ClientBuilder.newClient();
	WebTarget servicio;
	Response respuesta;
	HttpServletResponse responseRedirect;       
    
    public FarmaciasServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		opcion = request.getParameter("opcionServlet");
		
		switch(opcion){
		
		case "getFarmacias":
			servicio = client.target(URL).path("/farmacias");
			String res = servicio.request().get(String.class);
			
			HttpSession session = request.getSession();
			//session.setAttribute("resGET", gson.toJson(res));
			session.setAttribute("resGET", res);
			
			System.out.println("JSON servlet Farmacias: " + res);
			break;
			
		case "deleteFarmacias":
			System.out.println(request.getParameter("ID"));
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tomamos la opción enviada en el formulario
		opcion = request.getParameter("opcionServlet");
		map.clear();
		
		// Recogemos los datos y realizacion la peticion que corresponda
		switch(opcion){
		
		case "addFarmacia":
			map.add("farmaciaNombre", request.getParameter("farmaciaNombre"));
			map.add("farmaciaLatitud", request.getParameter("farmaciaLatitud"));
			map.add("farmaciaLongitud", request.getParameter("farmaciaLongitud"));			
			
			servicio = client.target(URL).path("/farmacias");
			//String respuesta = servicio.request().accept(MediaType.APPLICATION_JSON).get(String.class);	
			Response respuesta = servicio.request().post(Entity.form(map));
			
			response.sendRedirect("http://localhost:8080/DSS-P4/farmacias.jsp");		
			break;	
			
		}
		
	}

}
