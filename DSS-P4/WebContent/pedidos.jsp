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
				//HttpSession session = request.getSession();
				String prueba = session.getAttribute("getFarmaciasSesion").toString();		
				JSONObject obj = new JSONObject(prueba);
				JSONArray jsonArray = obj.getJSONArray("farmacias");		
				//JSONArray jsonArray = new JSONArray();		
				
				//String aux = request.getAttribute("prueba99").toString();
				//out.println("REQUEST: " + aux);			
				
				for(int i=0; i<jsonArray.length(); i++){
					JSONObject item = jsonArray.getJSONObject(i);
					Farmacia farmacia = new Farmacia();
					
					String prueba1 = item.get("ID").toString();
					int num = Integer.parseInt(prueba1);
					//farmacia.setID(Integer.parseInt(item.getString("ID")));
					String nombre = item.get("nombre").toString();
					//farmacia.setNombre(item.getString("nombre"));
					String prueba2 = item.get("latitud").toString();			
					float num2 = Float.parseFloat(prueba2);
					//farmacia.setLatitud(Float.parseFloat(item.getString("latitud")));
					String prueba3 = item.get("longitud").toString();
					float num3 = Float.parseFloat(prueba3);
					//farmacia.setLatitud(Float.parseFloat(item.getString("longitud")));
					
					farmacia.setID(num);
					farmacia.setNombre(nombre);
					farmacia.setLatitud(num2);
					farmacia.setLongitud(num3);			
					
					//out.println("\n\n\nFarmacia " + i + ":  " + farmacia.getID());
					farmaciasTotal.add(farmacia);
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
				<div class="col-md-6">
					<div class="container" style="margin-top:30px;">
						<div class="row">
							<h3 class="col-md-12">Añade un nuevo pedido:</h3>
							<form action="../DSS-P4/OrdersServlet" method="POST">
								<div class="form-group row" style="margin-top:20px;">
								  <label class="col-2 col-form-label">precio</label>
								  <div class="col-md-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="precio">
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-2 col-form-label">usuarioID</label>
								  <div class="col-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="usuarioID">
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-2 col-form-label">productoID</label>
								  <div class="col-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="productoID">
								  </div>
								</div>	
								<div class="form-group row">
								  <label class="col-2 col-form-label">farmaciaID</label>
								  <div class="col-8" style="margin-left:50px;">
								    <input class="form-control" type="text" name="farmaciaID">
								  </div>
								</div>
								<input name="opcionServlet" type="hidden" value="addPedidos" />  						   
						        <div class="form-group">
						            <button type="submit" class="btn btn-primary btn-block">Añadir</button>
						        </div>						
							</form>
						</div>
					</div>				
				</div>
				<div class="col-md-6" style="margin-top:50px;">
									
				</div>
			</div>
		</div>  		
	</body>
</html>