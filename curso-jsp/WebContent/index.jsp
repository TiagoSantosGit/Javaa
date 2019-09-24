<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resourse/css/estilo.css">
<link rel="shortcut icon" href="resourse/img/web.ico">
<meta charset="ISO-8859-1">
<title>Login no sistema</title>
</head>
<body>
	<div class="login-page">
		<center><h3>Projeto Didádico</h3></center>
		<center><h4>JSP+ Servlet + JDBC</h4></center>
		<center><span><b>USUÁRIO:</b> admin e a <b>SENHA:</b> admin</span></center></br>
		<div class="form">
			<form action="LoginSerlet" method="post">
				Login: <input type="text" id="login" name="login" autofocus>
				<br> Senha: <input type="password" id="senha" name="senha">
				<br>
				<button type="submit" value="Logar">Logar</button>
			</form>			
		</div>
		<center><h3><a style="text-decorate: none;" href="https://www.jdevtreinamento.com.br/formacao-java-web-profissional/index.html?gclid=EAIaIQobChMIhNL4rI3o5AIVggiRCh0nvQ8PEAAYAiAAEgIcPvD_BwE&ref=F13871365J&hsrc=ZGVzaw%3D%3D"> Formação Java Web</a></h3></center>
	</div>
</body>
</html>