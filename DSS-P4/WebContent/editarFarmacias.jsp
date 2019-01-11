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
		<%
			String ID = request.getParameter("ID");
			String nombre = request.getParameter("nombre");
			String latitud = request.getParameter("latitud");
			String longitud = request.getParameter("longitud");
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
		<div class="container" style="margin-top:30px;">
			<div class="row">
				<h3 class="col-md-12" style="margin-bottom:20px;">Edita la información de la farmacia:</h3>
				<form action="../DSS-P4/FarmaciasServlet" method="POST">
					<input name="ID" type="hidden" value="<%= ID %>" />  	
					<div class="form-group row">
					  <label class="col-2 col-form-label">Nombre</label>
					  <div class="col-md-8" style="margin-left:50px;">
					    <input class="form-control" type="text" name="farmaciaNombre" placeholder="<%= nombre %>" value="<%= nombre %>">
					  </div>
					</div>
					<div class="form-group row">
					  <label class="col-2 col-form-label">Latitud</label>
					  <div class="col-8" style="margin-left:50px;">
					    <input class="form-control" type="text" name="farmaciaLatitud" placeholder="<%= latitud %>" value="<%= latitud %>">
					  </div>
					</div>
					<div class="form-group row">
					  <label class="col-2 col-form-label">Longitud</label>
					  <div class="col-8" style="margin-left:50px;">
					    <input class="form-control" type="text" name="farmaciaLongitud" placeholder="<%= longitud %>" value="<%= longitud %>" >
					  </div>
					</div>	
					<input name="opcionServlet" type="hidden" value="editarFarmacias" />  						   
			        <div class="form-group">
			            <button type="submit" class="btn btn-primary btn-block">Editar</button>
			        </div>						
				</form>
			</div>
		</div>	  		
	</body>
</html>