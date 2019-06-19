<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="resourse/css/cadastro.css">
<meta charset="ISO-8859-1">
<title>Cadastro de Usu�rio</title>
</head>
<body>
	<h1>Cadastro de Usu�rio</h1>
	<h3>${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser">
		<ul class="form-style-1">
			<table>
				<tr>
					<td>C�digo:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${user.id}"></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.login}"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"
						value="${user.senha}"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${user.nome}"></td>
				</tr>
				<tr>
					<td>Telefone:</td>
					<td><input type="text" id="telefone" name="telefone"
						value="${user.telefone}"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="salvar">  <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
				</tr>
			</table>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption>Usuarios cadastros</caption>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Delete</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 100px"><c:out value="${user.id}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.login}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.senha}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.nome}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.telefone}"></c:out></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							src="resourse/img/excluir.jpg" alt="excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							src="resourse/img/editar.jpg" alt="editar" title="Editar"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>