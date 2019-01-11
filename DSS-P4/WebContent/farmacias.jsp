<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="servidor.Farmacia" %>
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
				$.get("../DSS-P4/FarmaciasServlet?opcionServlet=getFarmacias",function(response) { });		 
			});
		</script>		
		<%		
			Gson gson = new Gson();
			ArrayList<Farmacia> farmaciasTotal = new ArrayList<Farmacia>();
			
			if(session.getAttribute("getFarmaciasSesion") != null) {
				String farmaciasSesion = session.getAttribute("getFarmaciasSesion").toString();		
				JSONObject obj = new JSONObject(farmaciasSesion);
				JSONArray jsonArray = obj.getJSONArray("farmacias");						
				
				for(int i=0; i<jsonArray.length(); i++){
					JSONObject item = jsonArray.getJSONObject(i);
					Farmacia farmacia = new Farmacia();
					
					String aux1 = item.get("ID").toString();
					int ID = Integer.parseInt(aux1);
					String nombre = item.get("nombre").toString();
					String aux2 = item.get("latitud").toString();			
					float latitud = Float.parseFloat(aux2);
					String aux3 = item.get("longitud").toString();
					float longitud = Float.parseFloat(aux3);
					
					farmacia.setID(ID);
					farmacia.setNombre(nombre);
					farmacia.setLatitud(latitud);
					farmacia.setLongitud(longitud);							
					farmaciasTotal.add(farmacia);
				}
			} else {			
				response.sendRedirect("http://localhost:8080/DSS-P4/principal.jsp");
			}
		%>
		
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
		      <li class="nav-item active">
		        <a class="nav-link" href="../DSS-P4/farmacias.jsp">Farmacias</a>
		      </li>
		      <li class="nav-item">
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
							<h3 class="col-md-12">Añade una nueva farmacia:</h3>
							<form action="../DSS-P4/FarmaciasServlet" method="POST">
								<div class="form-group row" style="margin-top:20px;">
								  <label class="col-2 col-form-label">Nombre</label>
								  <div class="col-md-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="farmaciaNombre">
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-2 col-form-label">Latitud</label>
								  <div class="col-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="farmaciaLatitud">
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-2 col-form-label">Longitud</label>
								  <div class="col-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="farmaciaLongitud">
								  </div>
								</div>	
								<input name="opcionServlet" type="hidden" value="addFarmacia" />  						   
						        <div class="form-group">
						            <button type="submit" class="btn btn-primary btn-block">Añadir</button>
						        </div>						
							</form>
						</div>
					</div>				
				</div>
				<div class="col-md-6" style="margin-top:30px;">
					<h3 style="margin-bottom: 20px;">Listado de farmacias:</h3>
					<div class="container">
						<div class="row">
							<div class="col-md-6">
								<table class="table table-striped">
								  <thead>
								    <tr>
								      <th scope="col">ID</th>
								      <th scope="col">Nombre</th>
								      <th scope="col">Latitud</th>
								      <th scope="col">Longitud</th>
								      <th scope="col">Borrar</th>
								      <th scope="col">Editar</th>
								    </tr>
								  </thead>
								  <tbody>
								  <%									  
									  for(int i=0; i<farmaciasTotal.size(); i++){
										  Farmacia farmacia = new Farmacia();
										  farmacia = farmaciasTotal.get(i);
										  
										  String urlBorrar = "../DSS-P4/FarmaciasServlet?opcionServlet=deleteFarmacias&ID="+ farmacia.getID();
										  String urlEditar = "../DSS-P4/editarFarmacias.jsp?ID="
										  + farmacia.getID() + "&nombre=" + farmacia.getNombre() + "&latitud=" + farmacia.getLatitud() + "&longitud=" + farmacia.getLongitud();
										  out.println("<tr>");
										  out.println("<th>"+ farmacia.getID()  +"</th>");
										  out.println("<td>"+ farmacia.getNombre()  +"</td>");
										  out.println("<td>"+ farmacia.getLatitud()  +"</td>");
										  out.println("<td>"+ farmacia.getLongitud()  +"</td>");
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