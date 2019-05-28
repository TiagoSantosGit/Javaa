<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="cabecalho.jsp" />
	<h1>bem vindo ao curso jsp</h1>

	<%="seu sucesso garantido"%>
	<!-- tag de expressão -->
	<!-out.print("seu sucesso garantido");"); %>
	-->

	<!-- tag declarativa -->
	<%!int cont = 2;

	public int retorna(int n) {
		return n * 3;
	}%>

	<%="show" + cont%>

	<form action="receber-nome.jsp" method="post">
		<input type="text" id="nome" name="nome"> 
		<input type="text" id="ano" name="ano"> 
		<input type="text" id="sexo" name="sexo"> 
		<input type="submit" value="Enviar" />


	</form>
	<br>
	<!-- pega os parametro definidos no web.xml tag context-param -->
	<%=application.getInitParameter("estado")%>
	<%=application.getServerInfo()%>

	<br>

	<%
		session.setAttribute("curso", "curso de jsp");
	%>

	<br>
	<%--directivas --%>
	<%@ page import="java.util.Date"%>
	<%="Data de hoje" + new Date()%>
	<%@ page info="Pagina do curso de jsp"%>
	<%-- metodo pega o valor no lado da servelet getServletInfo() --%>

	<%@ page errorPage="receber-nome.jsp"%>
	<%-- <%= 100/0 --%>

	<%@ include file="pagina-include.jsp"%>


	<jsp:include page="rodape.jsp" />
	<br>
    <h2>usando o bean</h2>
    <%= calcula.calcula(50) %>    

    <%-- <myprefix:minhatag/> --%>
    <%-- <jsp:forward page="receber-nome.jsp"> --%>
    <%-- <jsp:param value="Curso de jsp site javaavançado.com" name="paramforward"/> --%>
    <%-- </jsp:forward> --%>

</body>
</html>