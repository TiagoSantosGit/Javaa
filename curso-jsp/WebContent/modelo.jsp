<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:setProperty property="*" name="calcula" />
	<h2>Seja bem vindo ao sistema jsp</h2>
	<a href="/cadastroUsuario.jsp">Cadastro de usuários...</a>
</body>
</html>