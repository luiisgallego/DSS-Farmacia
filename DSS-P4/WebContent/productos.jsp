<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="servidor.Farmacia" %>
<%@ page import="servidor.Producto" %>
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
				$.get("../DSS-P4/ProductosServlet?opcionServlet=getProductos",function(response) { });	 
			});
		</script>
		<%		
			Gson gson = new Gson();
			ArrayList<Producto> productosTotal = new ArrayList<Producto>();
			
			if(session.getAttribute("getProductosSesion") != null) {
				String productosSesion = session.getAttribute("getProductosSesion").toString();		
				JSONObject obj = new JSONObject(productosSesion);
				JSONArray jsonArray = obj.getJSONArray("productos");	
				
				for(int i=0; i<jsonArray.length(); i++){
					JSONObject item = jsonArray.getJSONObject(i);
					Producto producto = new Producto();
					
					String aux1 = item.get("ID").toString();
					int ID = Integer.parseInt(aux1);
					String nombre = item.get("nombre").toString();
					String aux2 = item.get("cantidad").toString();
					int cantidad = Integer.parseInt(aux2);
					String aux3 = item.get("precio").toString();
					int precio = Integer.parseInt(aux3);
					
					producto.setID(ID);
					producto.setNombre(nombre);
					producto.setCantidad(cantidad);
					producto.setPrecio(precio);
					producto.setImagen("null");						
					productosTotal.add(producto);
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
		  <div class="collapse navbar-collapse navegacionEstilo" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item">
		        <a class="nav-link" href="../DSS-P4/principal.jsp">Inicio</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="../DSS-P4/farmacias.jsp">Farmacias</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="../DSS-P4/productos.jsp">Productos</a>
		      </li>
		      <li class="nav-item">
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
				<div class="col-md-6 offset-md-3 estiloHeaderH1">
					<h1 style="text-align: center;">Consorcio Farmacias</h1>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="container" style="margin-top:30px;">
						<div class="row">
							<h3 class="col-md-12">Añade un nuevo producto:</h3>
							<form action="../DSS-P4/ProductosServlet" method="POST">
								<div class="form-group row" style="margin-top:20px;">
								  <label class="col-2 col-form-label">Nombre</label>
								  <div class="col-md-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="productoNombre">
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-2 col-form-label">Cantidad</label>
								  <div class="col-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="productoCantidad">
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-2 col-form-label">Precio</label>
								  <div class="col-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="productoPrecio">
								  </div>
								</div>	
								<input name="opcionServlet" type="hidden" value="addProductos" />  						   
						        <div class="form-group">
						            <button type="submit" class="btn btn-primary btn-block">Añadir</button>
						        </div>						
							</form>
						</div>
					</div>				
				</div>
				<div class="col-md-6" style="margin-top:30px;">
					<h3 style="margin-bottom: 20px;">Listado de productos:</h3>
					<div class="container">
						<div class="row">
							<div class="col-md-6">
								<table class="table table-striped">
								  <thead>
								    <tr>
								      <th scope="col">ID</th>
								      <th scope="col">Nombre</th>
								      <th scope="col">Cantidad</th>
								      <th scope="col">Precio</th>
								      <th scope="col">Borrar</th>
								      <th scope="col">Editar</th>
								    </tr>
								  </thead>
								  <tbody>
								  <%									  
									  for(int i=0; i<productosTotal.size(); i++){
										  Producto producto = new Producto();
										  producto = productosTotal.get(i);
										  
										  String urlBorrar = "../DSS-P4/ProductosServlet?opcionServlet=deleteProductos&ID="+ producto.getID();
										  String urlEditar = "../DSS-P4/editarProductos.jsp?ID="
										  + producto.getID() + "&nombre=" + producto.getNombre() + "&cantidad=" + producto.getCantidad() + "&precio=" + producto.getPrecio();
										  out.println("<tr>");
										  out.println("<th>"+ producto.getID()  +"</th>");
										  out.println("<td>"+ producto.getNombre()  +"</td>");
										  out.println("<td>"+ producto.getCantidad()  +"</td>");
										  out.println("<td>"+ producto.getPrecio() +"</td>");
										  out.println("<td><a href=\"" + urlBorrar +"\">BORRAR</a></td>");
										  out.println("<td><a href=\"" + urlEditar +"\">EDITAR</a></td>");
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