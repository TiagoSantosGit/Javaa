<%@ page
  language="java"
  contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script
  type="text/javascript"
  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h3>Pagina pai do jQuery</h3>
  <input
    type="button"
    value="Carregar p�gina"
    onclick="carregar();">
  <div id="mostrarPaginaFilha"></div>
  <!-- Local do carregamento da pagina filha -->
</body>
<script type="text/javascript">
	function carregar() {
		$("#mostrarPaginaFilha").load('paginaFilha.jsp'); // load p�gina em jQuery
	}
</script>
</html>