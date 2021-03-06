<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="BeanCursoJsp" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<!-- Adicionando JQuery -->
<!-- 
integrity � autoexplicativo: verifica��o de integridade. Isso � um checksum para garantir que o script que foi baixado n�o teve nada corrompido nem � uma vers�o desatualizada no seu HD, independente do motivo.
crossorigin � pra habilitar CORS, e com isso informa��es de erros de execu��o s�o recebidos pelo seu site. S� funciona se o CORS estiver habilitado do outro lado, que no caso dos CDN's, isso normalmente est� ativo.
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
    integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"
></script>
-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel=stylesheet href="resourse/css/cadastro.css">
<link rel="shortcut icon" href="resourse/img/web.ico">
<meta charset="ISO-8859-1">
<title>Cadastro de Usu�rio</title>
</head>
<body>
	<a href="acessoliberado.jsp"><img src="resourse/img/home.jpg"
		alt="In�cio" title="In�cio" width="30px" height="30px"></a>
	<a href="index.jsp"><img src="resourse/img/exit.jpg" alt="Sair"
		title="Sair" width="30px" height="30px"></a>
	<h1>Cadastro de Usu�rio</h1>
	<h3 style="color: orange;">${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos()? true : false;"
		enctype="multipart/form-data">
		<div class="form-style-1">
			<table>
				<tr>
					<td>C�digo:</td>
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
					<td><input type="text" id="cep" name="cep" maxlength="20"
						placeholder="CEP da cidade" onblur="consultaCep();"
						value="${user.cep}"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"
						placeholder="Digite uma senha" value="${user.senha}"
						maxlength="20"></td>
					<td>Cidade:</td>
					<td><input type="text" id="cidade" name="cidade"
						placeholder="Coloque a cidade" value="${user.cidade}"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						placeholder="Nome do usuario" value="${user.nome}"></td>
					<td>Ativo:</td>
					<td><input type="checkbox" id="ativo" name="ativo"
						checked=${usuario.ativo ? 'checked': ''}<%-- <%if (request.getAttribute("user") != null) {
				BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
				if (user.IsAtivo()) {
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%> --%>
			></td>
				</tr>
				<tr>
					<td>Foto:</td>
					<td><input type="file" name="foto" value="foto"><input
						type="text" style="display: none;" name="fotoTemp"
						readonly="readonly" value="${user.fotoBase64}"><input
						type="text" style="display: none;" name="contenTypeTemp"
						readonly="readonly" value="${user.contenType}"></td>
					<td>Sexo:</td>
					<td><input type="radio" id="sexo" name="sexo"
						${user.sexo == "M" ? 'checked': ''}
						<%-- <%if (request.getAttribute("user") != null) {
				BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
				if (user.getSexo().equalsIgnoreCase("M")) {
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%> --%>
						value="M">masculino
						<input type="radio" id="sexo"
						${user.sexo == "F" ? 'checked': ''}
						<%-- <%if (request.getAttribute("user") != null) {
				BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
				if (user.getSexo().equalsIgnoreCase("F")) {
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%> --%>
						name="sexo"
						value="F">feminino</td>
				</tr>
				<tr>
					<td>Curriculo:</td>
					<td><input type="file" name="curriculo" value="curriculo"><input
						type="text" style="display: none;" name="curriculoTemp"
						readonly="readonly" value="${user.curriculoBase64}"><input
						type="text" style="display: none;" name="contenTypeCurriculoTemp"
						readonly="readonly" value="${user.contenTypeCurriculo}"></td>
					<td>Perfil:</td>
					<td><select id="perfil" name="perfil">
							<option value="nao-informado">[--SELECIONE--]</option>
							<option value="administrador"<%-- <%if (request.getAttribute("user") != null) {
				BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
				if (user.getPerfil().equalsIgnoreCase("Administrador")) {
					out.print(" ");
					out.print("selected=\"selected\"");
					out.print(" ");
				}
			}%> --%>		
							>Administrador</option>
							<option value="secretario">Secretario</option>
							<option value="gerente">Gerente</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="salvar"> <input
						type="submit" value="Cancelar"
						onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
				</tr>
			</table>
		</div>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption>Usuarios cadastros</caption>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Foto</th>
				<th>Curriculo</th>
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
					<c:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
						<td style="width: 100px"><a
							href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}">
								<img src='<c:out value="${user.tempFotoUser}"/>'
								alt="Imagem User" title="Imagem User" width="32px" height="32px"
								onclick="alert('N�o possui imagem!')" />
						</a></td>
					</c:if>
					<c:if test="${user.fotoBase64Miniatura.isEmpty() == true}">
						<td style="width: 100px"><a
							href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}">
								<img src="./resourse/img/usuario.jpg" alt="Imagem User"
								title="Imagem User" width="32px" height="32px" />
						</a></td>
					</c:if>
					<c:if test="${user.curriculoBase64.isEmpty() == false }">
						<td style="width: 100px"><a
							href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img
								alt="Curriculo" src="./resourse/img/pdf.png" width="32px"
								height="32px"></a></td>
					</c:if>
					<c:if test="${user.curriculoBase64.isEmpty() == true }">
						<td style="width: 100px"><a href="#"><img
								alt="Inserir Curriculo" src="./resourse/img/nopdf.png"
								width="32px" height="32px"
								onclick="alert('N�o possui curriculo!')"></a></td>
					</c:if>
					<td style="width: 100px"><c:out value="${user.senha}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.nome}"></c:out></td>
					<td style="width: 100px"><c:out value="${user.telefone}"></c:out></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.id}"
						onclick="return confirm('Confirmar a exclus�o?');"><img
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
			if (document.getElementById("login").value == ''){
				alert('Informe o login');
				return false;
			} else
						if (document.getElementById("senha").value==
						'') {
				alert('Informe a senha');
				return false;
			} else
						if (document.getElementById("nome").value==
						'') {
				alert('Informe o nome');
				return false;
			} else
						if (document.getElementById("telefone").value==
						'') {
				alert('Informe o telefone');
				return
						false;
			}
			return true;
		}
	    <%--https://viacep.com.br/exemplo/jquery/--%>
		functionconsultaCep() {
		    var cep = $("#cep").val();
	 		//Consulta o webservice viacep.com.br
					 $.getJSON("https://viacep.com.br/ws/" + cep
							+ "/json/?callback=?", function(dados) {
						if (!("erro" in dados)) {
							//alert(dados.localidade);
							var cidade = $("#cidade").val(dados.localidade);
							document.setElementById("#cidade");
						} else {
							alert("CEP n�o enontrado!");
							limpa_formul�rio_cep();
						}
					});
		}
		function limpa_formul�rio_cep() {
			// Limpa valores do formul�rio decep.
			$("#cidade").val("");
			$("#cep").val("");
		}
	</script>
</body>
</html>