package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoCategoria;
import dao.DaoProduto;

/**
 * Servlet implementation class ServletProduto
 */
@WebServlet("/salvarProduto")
@MultipartConfig
public class ServletProduto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DaoProduto daoProduto = new DaoProduto();
    DaoCategoria daoCategoria = new DaoCategoria();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProduto() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            String codigo = request.getParameter("produt");
            if (acao.equalsIgnoreCase("delete")) {
                daoProduto.delete(codigo);
                RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                request.setAttribute("categoria", daoCategoria.listar());
                view.forward(request, response);
            } else {
                if (acao.equalsIgnoreCase("editar")) {
                    BeanProduto beanCursoJsp = daoProduto.consultar(codigo);
                    RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                    request.setAttribute("produt", beanCursoJsp);
                    request.setAttribute("produtos", daoProduto.listar());
                    request.setAttribute("categoria", daoCategoria.listar());
                    view.forward(request, response);
                } else if (acao.equalsIgnoreCase("listartodos")) {
                    RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                    request.setAttribute("produtos", daoProduto.listar());
                    request.setAttribute("categoria", daoCategoria.listar());
                    view.forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao != null && acao.equalsIgnoreCase("reset")) {
            try {
                RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            String codigo = request.getParameter("codigo");
            String nome = request.getParameter("nome");
            String unidade = request.getParameter("unidade");
            String quantidade = request.getParameter("quantidade");
            String preco = request.getParameter("preco");
            String categoria = request.getParameter("categoria");

            BeanProduto produto = new BeanProduto();
            produto.setCodigo(!codigo.isEmpty() ? Long.parseLong(codigo) : 0);
            produto.setNome(nome);
            produto.setUnidade(unidade);
            produto.setQuantidade(Double.parseDouble(quantidade));
            produto.setPreco(Double.parseDouble(preco));
            produto.setCategoria(categoria);

            try {
                if (codigo == null || codigo.isEmpty()) {
                    daoProduto.Salvar(produto);
                    request.setAttribute("msg", "Produto gravado!");
                } else {
                    daoProduto.atualizar(produto);
                    request.setAttribute("msg", "Produto atualizado!");
                }
                request.setAttribute("produt", produto);
                RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                request.setAttribute("categoria", daoCategoria.listar());
                view.forward(request, response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
