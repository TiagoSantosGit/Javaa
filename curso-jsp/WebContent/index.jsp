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
		<div class="form">
			<form action="LoginSerlet" method="post">
				Login: <input type="text" id="login" name="login"> <br>
				Senha: <input type="password" id="senha" name="senha"> <br>
				<button type="submit" value="Logar">Logar</button>
			</form>
		</div>
	</div>
</body>
</html>