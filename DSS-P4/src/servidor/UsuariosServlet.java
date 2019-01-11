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


@WebServlet("/UsuariosServlet")
public class UsuariosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String URL = "http://localhost:8080/DSS-P4/rest";
	private String opcion;
	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(); 		

	Client client = ClientBuilder.newClient();
	WebTarget servicio;
	Response respuesta;
	HttpServletResponse responseRedirect; 
       
    public UsuariosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		// Tomamos la opción enviada en el formulario
		opcion = request.getParameter("opcionServlet");
		map.clear();
		
		// Recogemos los datos y realizacion la peticion que corresponda
		switch(opcion){
			
		case "login":
			boolean resp;
			map.add("username", request.getParameter("username"));
			map.add("password", request.getParameter("password"));
			
			servicio = client.target(URL).path("/usuarios/login");
			respuesta = servicio.request().post(Entity.form(map));
			
			if(respuesta.getStatus() == 200) response.sendRedirect("../DSS-P4/principal.jsp");	
			else response.sendRedirect("../DSS-P4/index.jsp");	
			
			break;	
			
		case "registrar":
			map.add("nombre", request.getParameter("nombre"));
			map.add("nick", request.getParameter("nick"));
			map.add("pass", request.getParameter("pass"));
			map.add("rol", request.getParameter("rol"));
			map.add("email", request.getParameter("email"));
			
			System.out.println(map);
			
			servicio = client.target(URL).path("/usuarios/registro");
			respuesta = servicio.request().post(Entity.form(map));
			
			if(respuesta.getStatus() == 200) response.sendRedirect("../DSS-P4/principal.jsp");	
			else response.sendRedirect("../DSS-P4/index.jsp");
		}		
	}
	
}
