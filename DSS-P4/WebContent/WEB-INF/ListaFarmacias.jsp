<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Lista de farmacias</title>
</head>
<body>

	<h1>LISTA DE FARMACIAS</h1>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="servidor.Farmacia" %>
	<%
	
		ArrayList<Farmacia> farmacias = (ArrayList<Farmacia>) request.getAttribute("farmacias");
		for( int i = 0; i < farmacias.size(); i++)
			out.println("<p>" + farmacias.get(i) + "</p>");
	
	%>
	

</body>
</html>