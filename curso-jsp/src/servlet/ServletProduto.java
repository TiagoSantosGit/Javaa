package servlet;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D.Float;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import beans.BeanProduto;
import dao.DaoProduto;

/**
 * Servlet implementation class ServletProduto
 */
@WebServlet("/salvarProduto")
@MultipartConfig
public class ServletProduto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DaoProduto daoProduto = new DaoProduto();
    private ByteArrayOutputStream baos;

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
            String codigo = request.getParameter("codigo");
            if (acao.equalsIgnoreCase("delete")) {
                daoProduto.delete(codigo);
                RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response);
            } else {
                if (acao.equalsIgnoreCase("editar")) {
                    BeanProduto beanCursoJsp = daoProduto.consultar(codigo);
                    RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                    request.setAttribute("produto", beanCursoJsp);
                    request.setAttribute("produtos", daoProduto.listar());
                    view.forward(request, response);
                } else if (acao.equalsIgnoreCase("listartodos")) {
                    RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                    request.setAttribute("produtos", daoProduto.listar());
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

            BeanProduto produto = new BeanProduto();
            produto.setCodigo(!codigo.isEmpty() ? Long.parseLong(codigo) : 0);
            produto.setNome(nome);
            produto.setUnidade(unidade);
            produto.setQuantidade(Double.parseDouble(quantidade));
            produto.setPreco(Double.parseDouble(preco));

            try {
                if (codigo == null || codigo.isEmpty()) {
                    daoProduto.Salvar(produto);
                    request.setAttribute("msg", "Usuário gravado!");
                } else {
                    daoProduto.atualizar(produto);
                    request.setAttribute("msg", "Produto atualizado!");
                }
                request.setAttribute("produto", produto);
                RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
