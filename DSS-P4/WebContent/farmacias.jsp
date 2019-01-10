<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="servidor.Farmacia" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="java.util.ArrayList" %>

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
				<div class="col-md-6 offset-md-2 estiloHeaderH1">
					<h1 style="text-align: center;">Consorcio Farmacias</h1>
				</div>
			</div>
		</div>
		
		<%
		
		//Gson gson = new Gson();
		//String prueba = (String)session.getAttribute("prueba");
		//String json = gson.toJson(prueba);
		// ArrayList<Farmacia> ahora = gson.fromJson(json,new TypeToken<List<Farmacia>>());	
		
		String prueba = (String)session.getAttribute("resGET"); // toString()
		
		// de JSON a JSONArray
		
		Gson gson = new Gson();
		String json = gson.toJson(prueba);
		
		
		String inputJson = "{\n" +
          "    'page1':'true',\n" +
          "    'page2':'true',\n" +
          "    'page3':'false'\n" +
          "}";
	     
        //out.println(inputJson);
        //out.println(prueba);
	      
        out.println(prueba);
	   List<Farmacia> farmacias = new Gson().fromJson(prueba, new TypeToken<List<Farmacia>>() {}.getType());
	   //out.println(farmacias);
	            
	            
	            
		//Type type = new TypeToken<Map<String, String>>() {}.getType();
		//Map<String,String> map = new Gson().fromJson((String)session.getAttribute("resGET"), type);
		//ArrayList<Farmacia> ahora = gson.fromJson(json,new TypeToken<List<Farmacia>>());	
		
		//out.println(map);
		
		%>
		<!--  <p>Prueba: <strong>${sessionScope.resGET}</strong></p>
		<p>PRueba2: <strong><% //out.println(prueba); %></strong> -->
		
		
		
		<div class="container" style="margin-top:30px;">
			<div class="row">
				<h3 class="col-md-12">Añade una nueva farmacia:</h3>
				<form action="../DSS-P4/FarmaciasServlet" method="POST">
					<div class="form-group row">
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
		
		
		
		
		
		
		
		<!--<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">First</th>
		      <th scope="col">Last</th>
		      <th scope="col">Handle</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row">1</th>
		      <td>Mark</td>
		      <td>Otto</td>
		      <td>@mdo</td>
		    </tr>
		    <tr>
		      <th scope="row">2</th>
		      <td>Jacob</td>
		      <td>Thornton</td>
		      <td>@fat</td>
		    </tr>
		    <tr>
		      <th scope="row">3</th>
		      <td>Larry</td>
		      <td>the Bird</td>
		      <td>@twitter</td>
		    </tr>
		  </tbody>
		</table>  -->
		
		
		
  		
	</body>
</html>