<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="ISO-8859-1">
<title>Upload files</title>
</head>
<body>
	<input type="file" id="file" name="file" onchange="uploadFile();">
	<img alt="Imagem" src="" id="target" width="200" height="200">

	<table>
		<c:forEach items="${listarUserImagem}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>
				<td><a href="fileUpload?acao=download&iduser=${user.id}">Download
						Imagem</a></td>
			</tr>

		</c:forEach>

	</table>

	<br />
	<br />
	<br />
	<br />
	<a href="fileUpload">Carregar imagens</a>
</body>

<script type="text/javascript">
	function uploadFile() {
		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();
		reader.onload = function() {
			target.src = reader.result;
			/////----- Upload ajax -----

			$.ajax({
				method : "POST",
				url : "fileUpload",
				data : {
					fileUpload : target.src
				}
			}).done(function(response) {
				alert("Sucesso: " + response);
			}).fail(function(hrx, status, errorThrown) {
				alert("Error: " + hxr.responseText);
			});
			/////-----
		};
		if (file) {
			reader.readAsDataURL(file);
		} else {
			target.src = "";
		}
	}
</script>
</html>