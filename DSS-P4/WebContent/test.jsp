<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Prueba crear farmacia</title>		
		<!-- BOOTSTRAP -->
		<link href="CSS/bootstrap.css" rel="stylesheet" />
		<script src="JS/jquery-1.11.0.min.js"></script>
		<script src="JS/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<h1>PROBANDO</h1>
			</div>
		</div>
	
	
		<form action="../DSS-P4/rest/farmacias" method="POST">
			<label for="nombre">Nombre</label><br/>
			<input name="farmaciaNombre"><br/>
			<label for="farmaciaLatitud">Latitud</label><br/>
			<input name="farmaciaLatitud"><br/>
			<label for="farmaciaLongitud">Longitud</label><br/>
			<input name="farmaciaLongitud"><br/>
			<input type="submit" value="Guardar" />
		</form>
		<form action="../DSS-P4/rest/farmacias/" >
			<input name="prueba" value="5"><br/>
   			<input type="submit" value="Ver listado de farmacias" />
		</form>
	</body>
</html>