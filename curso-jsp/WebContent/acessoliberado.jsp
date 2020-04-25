<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="shortcut icon" href="resourse/img/web.ico">
<title>Menu principal</title>
</head>
<body>
	<div align="center" style="padding-top: 10%;">
		<h1>Seja bem vindo ao sistema!</h1>
		<%-- <a href="cadastroUsuario.jsp">Cadastro de usuários...</a> --%>
		<table>
			<tr>
				<td><a href="salvarUsuario?acao=listartodos"><img
						src="resourse/img/users.png" alt="usuario"
						title="Cadastro de usuário" width="250px" height="250px"></a></td>
				<td><a href="salvarProduto?acao=listartodos"><img
						src="resourse/img/produt.jpg" alt="produto"
						title="Cadastro de produto" width="250px" height="250px"></a></td>
			</tr>
			<tr>
				<td><div align="center">Cad. Usuário</div></td>
				<td><div align="center">Cad. Produto</div></td>
			</tr>
		</table>
	</div>
</body>
</html>