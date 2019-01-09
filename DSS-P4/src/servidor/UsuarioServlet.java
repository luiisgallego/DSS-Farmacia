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
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String URL = "http://localhost:8080/DSS-P4/rest";
	private Gson gson = new Gson();
	private String opcion;
	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(); 	
	Client client = ClientBuilder.newClient();
	WebTarget servicio;
	Response respuesta;
	HttpServletResponse responseRedirect;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// Tomamos la opción enviada en el formulario
				opcion = request.getParameter("opcionServlet");
				map.clear();
				servicio = null;
				
				// Recogemos los datos y realizacion la peticion que corresponda
				switch(opcion){
					
				case "login":
					System.out.println("Servlet2");
					map.add("username", request.getParameter("username"));
					map.add("password", request.getParameter("password"));
					
					servicio = client.target(URL).path("/usuarios");
					respuesta = servicio.request().post(Entity.form(map));
					
					if(respuesta.getStatus() == 200) response.sendRedirect("http://localhost:8080/DSS-P4/test.jsp");	
					else response.sendRedirect("http://localhost:8080/DSS-P4/error.jsp");	
					
					break;
				
				}
	}

}
