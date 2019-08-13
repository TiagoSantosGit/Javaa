<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="resourse/css/cadastro.css">
<meta charset="ISO-8859-1">
<title>Cadastro de Telefones</title>
</head>
<body>
<a href="acessoliberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>
	<h1>Cadastro de Telefones</h1>
	<h3 style="color: orange;">${msg}</h3>
	<form action="salvarTelefone" method="post" id="formUser"
		onsubmit="return validarCampos()? true : false;">
		<ul class="form-style-1">
			<table>
				<tr>
					<td>Usuário:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${userEscolhido.id}"></td>
					<td><input type="text" readonly="readonly" id="nome" name="nome"
						value="${userEscolhido.nome}"></td>
				</tr>
				<tr>
					<td>Telefone:</td>
					<td><input type="text" id="telefone" name="telefone"
						value="${userEscolhido.telefone}"></td>
					<td>
					<select id="tipo" name="tipo">
					<option>Casa</option>
					<option>Contato</option>
					<option>Celular</option>
					</select>					
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="salvar">
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
				<th>Telefones</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 100px"><c:out value="${user.id}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.login}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.senha}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.nome}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.telefone}"></c:out></td>
					<td><a href="salvarTelefone?acao=delete&user=${user.id}"><img
							src="resourse/img/excluir.jpg" alt="excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td><a href="salvarTelefone?acao=editar&user=${user.id}"><img
							src="resourse/img/editar.jpg" alt="editar" title="Editar"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("telefone").value == '') {
				alert('Informe o telefone');
				return false;
			}else{
				if (document.getElementById("tipo").value == '') {
					alert('Informe o tipo');
					return false;
				}
			}
			return true;
		}
	</script>
</body>
</html>