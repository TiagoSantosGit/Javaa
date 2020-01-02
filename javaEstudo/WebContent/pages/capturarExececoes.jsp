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
  <h3>Capturando exceções com jquery</h3>
  <input
    type="text"
    value="Valor informado"
    id="txtvalor">
  <input
    type="button"
    onclick="testarExcecao();"
    value="Testar Excecao">

</body>
<script type="text/javascript">
	function testarExcecao() {
		valorInformado = $('#txtvalor').val();
		$.ajax({
			method : "POST",
			url : "CapturarExecao", // para qual servlet
			data : {
				valorParam : valorInformado
			}
		}).done(function(response) { // resposta ok - nenhum erro
			alert("Sucesso: " + response);
			// fazer algo
		}).fail(function(xhr, status, errorThrown) { // resposta erro - algum problema ocorreu
			alert("Error: " + xhr.responseText); // mostra a resposta do servidor
			//fazer algo de ser errado
		}).always(function(response) { // sempre capta o retorno
			alert("Sempre: " + response);
			// sempre mostra
		})
	}
</script>

</html>