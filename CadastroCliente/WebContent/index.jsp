<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema web java</title>
</head>
<body>
	<jsp:include page="cabCli.jsp" />
	<h1>Cadastro de Clientes</h1>

	<form action="CadCliServlet" method="post">
		C�digo : <input type="text" id="codigo" name="codigo"> <br>
		Nome : <input type="text" id="nome" name="nome"> <br>
		Endere�o : <input type="text" id="endereco" name="endereco"> <br>
		N�mero : <input type="text" id="numero" name="numero"> <br>
		<input type="submit" value="Salvar" />
	</form>

	<jsp:include page="rodCli.jsp" />
</body>
</html>