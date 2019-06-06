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

	<form action="CadCliServLet" method="post">
		<table>
			<tr>
				<td><label>Código :</label></td>
				<td><input type="text" id="codigo" name="codigo"></td>
			</tr>
			<tr>
				<td><label>Nome :</label></td>
				<td><input type="text" id="nome" name="nome"></td>
			</tr>
			<tr>
				<td><label>Endereço :</label></td>
				<td><input type="text" id="endereco" name="endereco"></td>
			</tr>
			<tr>
				<td><label>Número :</label></td>
				<td><input type="text" id="numero" name="numero"></td>
			</tr>
			<tr>
			<td/>
				<td><input type="submit" value="Consultar" />
				<input type="submit" value="Salvar" /></td>
			</tr>
		</table>
	</form>

	<jsp:include page="rodCli.jsp" />
</body>
</html>