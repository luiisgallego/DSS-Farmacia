<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</head>
	</head>
	<body>		
		<div class="registro-form">
		    <form action="../DSS-P4/UsuariosServlet" method="POST">
		        <h2 class="text-center">REGISTRAR</h2> 
		        <div class="container">
		        	<div class="row">
		        		<div class="col-md-5">
		        			<input name="nombre" type="text" class="form-control" placeholder="nombre" required="required">
		        		</div>
		        		<div class="col-md-5 offset-md-2" style="margin-bottom: 30px;">
		        			<input name="nick" type="text" class="form-control" placeholder="nick" required="required">
		        		</div>
		        	</div>
		        </div>
		        <div class="container">
		        	<div class="row">
		        		<div class="col-md-5">
		        			<input name="pass" type="text" class="form-control" placeholder="pass" required="required">
		        		</div>
		        		<div class="col-md-5 offset-md-2" style="margin-bottom: 30px;">
		        			<input name="rol" type="hidden" value="Farmaceutico" class="form-control" placeholder="username" required="required">
		        			<input name="email" type="text" class="form-control" placeholder="email" required="required">
		        		</div>
		        	</div>
		        </div>	   
		        <div class="form-group">
		            <button type="submit" class="btn btn-primary btn-block">Aceptar</button>
		        </div> 
		        <input name="opcionServlet" type="hidden" value="registrar" />       
		    </form>
		</div>  		
	</body>
</html>