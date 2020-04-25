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
	<% 
String nome = "Nome recebido : " + request.getParameter("nome");
out.print(nome); 
%>
	<p />
	<%= request.getContextPath() %>
	<p />
	<%-- objetos implicitos  estão disponiveis no servidor e na pagina util para mostrar informações --%>
	<%-- <% response.sendRedirect("http://www.uol.com.br");%> --%>
	<%-- estudar request e response --%>
	<%-- sessões --%>
	<br>
	<%--passa dados para todas as paginas da mesma sessão --%>
	<%= session.getAttribute("curso") %>

	<%@ page isErrorPage="true"%>
	<%= exception %>

	<br>

	<%= request.getParameter("paramforward") %>

	<br>
	<jsp:setProperty property="*" name="calcula" />
	<jsp:getProperty property="nome" name="calcula" />
	<jsp:getProperty property="ano" name="calcula" />
	<jsp:getProperty property="sexo" name="calcula" />

	<br>
	<%--usando expressões --%>
	Nome : ${param.nome}
	<br> Ano: ${param.ano}
	<br> Idade: ${param.idade}
	<br> ${sessionScope.user}

</body>
</html>