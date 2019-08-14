package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.BeanTelefones;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/salvarTelefone")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoUsuario daoUsuario = new DaoUsuario();
	DaoTelefones daoTelefones = new DaoTelefones();

	public TelefonesServlets() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao.equalsIgnoreCase("listarFone")) {
				String user = request.getParameter("user");
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
				request.getSession().setAttribute("userEscolhido", beanCursoJsp);
				request.setAttribute("userEscolhido", beanCursoJsp);
				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("delete")) {
				String foneId = request.getParameter("foneId");
				daoTelefones.delete(foneId);
				BeanCursoJsp usuario = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
				request.setAttribute("msg", "Excluído Com Sucesso!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String numero = request.getParameter("telefone");
			String tipo = request.getParameter("tipo");
			BeanCursoJsp usuario = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
			BeanTelefones fone = new BeanTelefones();
			fone.setNumero(numero);
			fone.setTipo(tipo);
			fone.setUsuario(usuario.getId());

			daoTelefones.Salvar(fone);
			request.getSession().setAttribute("userEscolhido", usuario);
			request.setAttribute("userEscolhido", usuario);
			RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
			request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
			request.setAttribute("msg", "Salvo com sucesso!");
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
