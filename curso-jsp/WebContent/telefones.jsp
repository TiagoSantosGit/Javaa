<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="resourse/css/cadastro.css">
<link rel="shortcut icon" href="resourse/img/web.ico">
<meta charset="ISO-8859-1">
<title>Cadastro de Telefones</title>
</head>
<body>
	<a href="acessoliberado.jsp">In�cio</a>
	<a href="index.jsp">Sair</a>
	<h1>Cadastro de Telefones</h1>
	<h3 style="color: orange;">${msg}</h3>
	<form action="salvarTelefone" method="post" id="formUser"
		onsubmit="return validarCampos()? true : false;">
		<div class="form-style-1">
			<table>
				<tr>
					<td>Usu�rio:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${userEscolhido.id}"></td>
					<td><input type="text" readonly="readonly" id="nome"
						name="nome" value="${userEscolhido.nome}"></td>
				</tr>
				<tr>
					<td>Telefone:</td>
					<td><input type="text" id="telefone" name="telefone"
						value="${userEscolhido.telefone}"></td>
					<td><select id="tipo" name="tipo">
							<option></option>
							<option>Casa</option>
							<option>Contato</option>
							<option>Celular</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="salvar"></td>
				</tr>
			</table>
		</div>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption>Usuarios cadastros</caption>
			<tr>
				<th>Id</th>
				<th>Numero</th>
				<th>Tipo</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td style="width: 100px"><c:out value="${fone.id}"></c:out></td>
					<td style="width: 100px"><c:out value="${fone.numero}"></c:out></td>
					<td style="width: 100px"><c:out value="${fone.tipo}"></c:out></td>
					<td><a href="salvarTelefone?acao=delete&foneId=${fone.id}"><img
							src="resourse/img/excluir.jpg" alt="excluir" title="Excluir"
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
                        } else {
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