<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    <%@ page import="com.google.gson.Gson" %>
	<%@ page import="com.google.gson.reflect.TypeToken" %>
	<%@ page import="servidor.Farmacia" %>
	<%@ page import="servidor.Order" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="java.lang.reflect.Type" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="org.json.JSONArray,org.json.JSONObject,org.json.JSONException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Práctica 4 DSS - Farmacias</title>
		<!-- BOOTSTRAP -->
		<link href="CSS/bootstrap.css" rel="stylesheet" />
		<script src="JS/jquery-1.11.0.min.js"></script>
		<script src="JS/bootstrap.min.js"></script>
		<!--  CSS -->
		<link href="CSS/estilo.css" rel="stylesheet" />
		
		<script type="text/javascript"> 
		$(document).ready(function() {		 
			$.get("../DSS-P4/OrdersServlet?opcionServlet=getPedidos",function(response) { });		 
		});
		</script>		
		<%		
			Gson gson = new Gson();
			ArrayList<Order> pedidosTotal = new ArrayList<Order>();
			
			if(session.getAttribute("getPedidosSesion") != null) {
				String pedidosSesion = session.getAttribute("getPedidosSesion").toString();		
				JSONObject obj = new JSONObject(pedidosSesion);
				JSONArray jsonArray = obj.getJSONArray("pedidos");						
				
				for(int i=0; i<jsonArray.length(); i++){
					JSONObject item = jsonArray.getJSONObject(i);
					Order pedido = new Order();
					
					String aux1 = item.get("ID").toString();
					int ID = Integer.parseInt(aux1);
					String aux2 = item.get("precio").toString();
					int precio = Integer.parseInt(aux2);
					String aux3 = item.get("productoID").toString();
					int productoID = Integer.parseInt(aux3);
					String aux4 = item.get("farmaciaID").toString();
					int farmaciaID = Integer.parseInt(aux4);
					String aux5 = item.get("usuarioID").toString();
					int usuarioID = Integer.parseInt(aux5);
					
					
					pedido.setID(ID);
					pedido.setPrecio(precio);
					pedido.setProductoID(productoID);
					pedido.setFarmaciaID(farmaciaID);
					pedido.setUsuarioID(usuarioID);
					pedidosTotal.add(pedido);
				}
			} else {			
				response.sendRedirect("http://localhost:8080/DSS-P4/principal.jsp");
			}
		%>		
	</head>
	</head>
	<body>		
		<!-- NAVEGACION -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href="#"></a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item">
		        <a class="nav-link" href="../DSS-P4/principal.jsp">Inicio</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="../DSS-P4/farmacias.jsp">Farmacias</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="../DSS-P4/productos.jsp">Productos</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="../DSS-P4/pedidos.jsp">Pedidos</a>
		      </li>
		    </ul>
		    <div class="col-md-1">
		    	<a class="botonSalir" href="../DSS-P4/index.jsp" >Salir</a>
		    </div>		    
		  </div>
		</nav>		
		<div class="header estiloHeader">
			<div class="container">
				<div class="col-md-6 offset-md-2 estiloHeaderH1">
					<h1 style="text-align: center;">Consorcio Farmacias</h1>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-md-8 offset-md-1" style="margin-top:50px;">
					<h3 style="margin-bottom: 20px;">Listado de pedidos realizados:</h3>
					<div class="container">
						<div class="row">
							<div class="col-md-6">
								<table class="table table-striped">
								  <thead>
								    <tr>
								      <th scope="col">ID</th>
								      <th scope="col">Precio</th>
								      <th scope="col">ID Producto</th>
								      <th scope="col">ID Farmacia</th>
								      <th scope="col">ID Usuario</th>
								    </tr>
								  </thead>
								  <tbody>
								  <%									  
									  for(int i=0; i<pedidosTotal.size(); i++){
										  Order pedido = new Order();
										  pedido = pedidosTotal.get(i);
										  
										  //String urlBorrar = "../DSS-P4/FarmaciasServlet?opcionServlet=deleteFarmacias&ID="+ farmacia.getID();
										  //String urlEditar = "../DSS-P4/editarFarmacias.jsp?ID="
										  //+ farmacia.getID() + "&nombre=" + farmacia.getNombre() + "&latitud=" + farmacia.getLatitud() + "&longitud=" + farmacia.getLongitud();
										  out.println("<tr>");
										  out.println("<th>"+ pedido.getID()  +"</th>");
										  out.println("<td>"+ pedido.getPrecio() +"</td>");
										  out.println("<td>"+ pedido.getProductoID()  +"</td>");
										  out.println("<td>"+ pedido.getFarmaciaID()  +"</td>");
										  out.println("<td>"+ pedido.getUsuarioID()  +"</td>");
										  out.println("</tr>");						  
									  }													 
								  %>
								  </tbody>
								</table>
							</div>
						</div>
					</div>									
				</div>
			</div>
		</div>  		
	</body>
</html>