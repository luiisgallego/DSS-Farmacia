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
		
		<script type="text/javascript"> 
		$(document).ready(function() {		 
			$.get("../DSS-P4/FarmaciasServlet?opcionServlet=getFarmacias",function(response) { });		 
		});
		</script>
		
		<% //HttpSession session = request.getSession(); %>
	</head>
	</head>
	<body>	
	
		<div class="login-form">
		    <form action="../DSS-P4/UsuariosServlet" method="POST"">
		        <h2 class="text-center">LOGIN</h2>       
		        <div class="form-group">
		            <input name="username" type="text" class="form-control" placeholder="username" required="required">
		        </div>
		        <div class="form-group">
		            <input name="password" type="password" class="form-control" placeholder="password" required="required">
		        </div>
		        <div class="form-group">
		            <button type="submit" class="btn btn-primary btn-block">Aceptar</button>
		        </div> 
		        <input name="opcionServlet" type="hidden" value="login" />       
		    </form>
		    <p class="text-center"><a href="../DSS-P4/registrar.jsp">REGISTRATE</a></p>
		</div>
		
  		
	</body>
</html>