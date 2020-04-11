<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="BeanCursoJsp" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="resourse/css/cadastro.css">
<link rel="shortcut icon" href="resourse/img/web.ico">
<meta charset="ISO-8859-1">
<title>Cadastro de Produto</title>
</head>
<body>
    <a href="acessoliberado.jsp"><img src="resourse/img/home.jpg" alt="Início" title="Início"
        width="30px" height="30px"
    ></a>
    <a href="index.jsp"><img src="resourse/img/exit.jpg" alt="Sair" title="Sair" width="30px"
        height="30px"
    ></a>
    <h1>Cadastro de Produto</h1>
    <h3 style="color: orange;">${msg}</h3>
    <form action="salvarProduto" method="post" id="formProdut"
        onsubmit="return validarCampos()? true : false;" enctype="multipart/form-data"
    >
        <div class="form-style-1">
            <table>
                <tr>
                    <td>Código:</td>
                    <td><input type="text" readonly="readonly" id="codigo" name="codigo"
                        value="${produt.codigo}"
                    ></td>
                    <td>Nome:</td>
                    <td><input type="text" id="nome" name="nome" value="${produt.nome}"></td>
                </tr>
                <tr>
                    <td>Quantidade:</td>
                    <td><input type="text" id="quantidade" name="quantidade"
                        value="${produt.quantidade}"
                    ></td>
                    <td>Preco:</td>
                    <td><input type="text" id="preco" name="preco" value="${produt.preco}"></td>
                </tr>
                <tr>
                    <td>Unidade:</td>
                    <td><input type="text" id="unidade" name="unidade"
                        value="${produt.unidade}"
                    ></td>
                    <td>Categoria:</td>
                    <td><select id="categoria" name="categoria">
                            <option value="" selected disabled>Selecione Categoria</option>
                            <c:forEach items="${categoria}" var="categ">
                                <option value="${categ.nomeCategoria}" id="${categ.nomeCategoria}">${categ.nomeCategoria}</option>
                            </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td><input type="submit" value="salvar"> <input type="submit"
                        value="Cancelar"
                        onclick="document.getElementById('formProdut').action = 'salvarProduto?acao=reset'"
                    ></td>
                </tr>
            </table>
        </div>
    </form>
    <div class="container">
        <table class="responsive-table">
            <caption>Produtos cadastros</caption>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Unidade</th>
                <th>Quantidade</th>
                <th>Preco</th>
            </tr>
            <c:forEach items="${produtos}" var="produt">
                <tr>
                    <td style="width: 100px"><c:out value="${produt.codigo}"></c:out></td>
                    <td style="width: 100px"><c:out value="${produt.nome}"></c:out></td>
                    <td style="width: 100px"><c:out value="${produt.unidade}"></c:out></td>
                    <td style="width: 100px"><c:out value="${produt.quantidade}"></c:out></td>
                    <td style="width: 100px"><c:out value="${produt.preco}"></c:out></td>
                    <td><a href="salvarProduto?acao=delete&produt=${produt.codigo}"
                        onclick="return confirm('Confirmar a exclusão?');"
                    ><img src="resourse/img/excluir.jpg" alt="excluir" title="Excluir"
                            width="20px" height="20px"
                        ></a></td>
                    <td><a href="salvarProduto?acao=editar&produt=${produt.codigo}"><img
                            src="resourse/img/editar.jpg" alt="editar" title="Editar" width="20px"
                            height="20px"
                        ></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <script type="text/javascript">
                    function validarCampos() {
                        if (document.getElementById("nome").value == '') {
                            alert('Informe a nome');
                            return false;
                        } else if (document.getElementById("unidade").value == '')
                        {
                            alert('Informe o unidade');
                            return false;
                        } else if (document.getElementById("quantidade").value == '')
                        {
                            alert('Informe o quantidade');
                            return false;
                        } else if (document.getElementById("preco").value == '')
                        {
                            alert('Informe o preco');
                            return false;
                        }
                        return true;
                    }
                </script>
</body>
</html>