<%@ page
  language="java"
  contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
  rel="stylesheet"
  href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<script
  type="text/javascript"
  src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<meta charset="ISO-8859-1">
<title>DataTable JQuery</title>
</head>
<body>
  <table
    id="minhatabela"
    class="display"
    style="width: 100%">
    <thead>
      <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Senha</th>
      </tr>
    </thead>
    <tfoot>
      <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Senha</th>
      </tr>
    </tfoot>
  </table>
</body>
<script type="text/javascript">
	$(document).ready(function() {// faz o processamento a hora que abre a tela.
		$('#minhatabela').DataTable({
			"processing" : true,
			"serverSide" : true,
			"ajax" : "carregarDadosDataTable" // URL de retorno dos dados do servidor (RESPOSTA DO )
		});
	});
</script>
</html>