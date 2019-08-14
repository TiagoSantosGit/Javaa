<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

<link rel=stylesheet href="resourse/css/cadastro.css">
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
</head>
<body>
	<a href="acessoliberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>
	<h1>Cadastro de Usuário</h1>
	<h3 style="color: orange;">${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos()? true : false;"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<table>
				<tr>
					<td>Código:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${user.id}"></td>
					<td>Telefone:</td>
					<td><input type="text" id="telefone" name="telefone"
						value="${user.telefone}"></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.login}"></td>
					<td>CEP:</td>
					<td><input type="text" id="cep" name="cep"
						placeholder="CEP da cidade" onblur="consultaCep();"
						value="${user.cep}"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"
						placeholder="Digite uma senha" value="${user.senha}"></td>
					<td>Cidade:</td>
					<td><input type="text" id="cidade" name="cidade"
						placeholder="Coloque a cidade" value="${user.cidade}"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						placeholder="Nome do usuario" value="${user.nome}"></td>
				</tr>
				<tr>
					<td>Foto:</td>
					<td><input type="file" name="foto" value="foto"></td>
				</tr>
				<tr>
					<td><input type="submit" value="salvar"> <input
						type="submit" value="Cancelar"
						onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
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
					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							src="resourse/img/excluir.jpg" alt="excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							src="resourse/img/editar.jpg" alt="editar" title="Editar"
							width="20px" height="20px"></a></td>
					<td><a href="salvarTelefone?acao=listarFone&user=${user.id}"><img
							src="resourse/img/telefone.png" alt="telefone" title="Telefone"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o login');
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe a senha');
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o nome');
				return false;
			} else if (document.getElementById("telefone").value == '') {
				alert('Informe o telefone');
				return false;
			}
			return true;
		}
		function consultaCep() {
	<%--https://viacep.com.br/exemplo/jquery/--%>
		var cep = $("#cep").val();
			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {
						if (!("erro" in dados)) {
							//alert(dados.localidade);
							var cidade = $("#cidade").val(dados.localidade);
							document.setElementById("#cidade");
						} else {
							alert("CEP não encontrado!");
							limpa_formulário_cep();
						}
					});
		}
		function limpa_formulário_cep() {
			// Limpa valores do formulário de cep.
			$("#cidade").val("");
			$("#cep").val("");
		}
	</script>
</body>
</html>