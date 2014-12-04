<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<title>SisMed</title>
<link type="text/css" rel="stylesheet" href="css/estilo.css">
	<h1>SISMED</h1>
	<hr>
	<br>
	<h3>Página de Erro</h3>
	
	<h4>Mensagem:</h4>
	<h4>
	<c:if test="${erro != null}">
		<p>${erro.message}</p>
	</c:if>
	</h4>
	<br><br>
	<p>
	<a href="telaInicialDiretor.html">Retornar ao Menu Principal</a></p>
	<br><br><br>
