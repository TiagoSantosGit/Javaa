<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bem vindo ao curso jsp</title>
</head>
<body>
	<jsp:include page="cabecalho.jsp" />
	<h1>bem vindo ao curso jsp</h1>
	<%="seu sucesso garantido"%>
	<%-- tag de expressão --%>
	<%-- <% out.print("seu sucesso garantido");"); %> --%>
	<!-- tag declarativa -->
	<%!int cont = 2;

    public int retorna(int n) {
	return n * 3;
    }%>
	<%="show" + cont%>
	<%
        session.setAttribute("user", "java show");
    %>
	<a href="receber-nome.jsp">Ver teste</a>
	<form action="receber-nome.jsp" method="post">
		<input type="text" id="nome" name="nome"> <input type="text"
			id="ano" name="ano"> <input type="text" id="sexo" name="sexo">
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
	<%=calcula.calcula(50)%>
	<%-- <myprefix:minhatag/> --%>
	<%-- <jsp:forward page="receber-nome.jsp"> --%>
	<%-- <jsp:param value="Curso de jsp site javaavançado.com" name="paramforward"/> --%>
	<%-- </jsp:forward> --%>
	<form action="LoginSerlet" method="post">
		Login: <input type="text" id="login" name="login"> <br>
		Senha: <input type="text" id="senha" name="senha"> <br>
		<input type="submit" value="Logar" />
	</form>
	<p />
	<p />
	<p />
	<p />
	<p />
	<%-- comandos jstl --%>
	<c:out value="${'bem vindo ao JSTL'}" />
	<br>
	<c:import var="data" url="http://www.google.com.br" />
	<c:remove var="data" />
	<c:out value="${data}" />
	<br>
	<c:set var="data1" scope="page" value="${500*6}" />
	<c:out value="${data1}" />
	<br>
	<c:catch var="erro">
		<%=100 / 0%>
	</c:catch>
	<c:if test="${erro != null }">
	  ${erro.message}
	</c:if>
	<br>
	<c:set var="numero" value="${100/2}" />
	<c:choose>
		<c:when test="${numero >= 50}">
			<c:out value="${'Maior e igual que 50'}" />
		</c:when>
		<c:when test="${numero < 50}">
			<c:out value="${'Menor que 50'}" />
		</c:when>
		<c:otherwise>
			<c:out value="${'Não encontrou valor correto1'}" />
		</c:otherwise>
	</c:choose>
	<br>
	<c:forEach var="n" begin="1" end="${numero}">
	  Item: ${n}
	</c:forEach>
	<br>
	<c:forTokens items="Alex-Fernando-Egidio" delims="-" var="nome">
      Nome: <c:out value="${nome}" />
		<br>
	</c:forTokens>
	<br>
	<c:url value="acessoliberado.jsp" var="acesso">
		<c:param name="param1" value="show" />
		<c:param name="param2" value="legal" />
	</c:url>
	${acesso}
	<br>
	<%-- 
	<c:if test="${numero >= 50}">
		<c:redirect url="http://www.google.com.br" />
    </c:if>
	<c:if test="${numero < 50}">
		<c:redirect url="http://javaavancado.com" />
	</c:if>
    --%>
</body>
</html>