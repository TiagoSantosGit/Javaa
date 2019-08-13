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
			String user = request.getParameter("user");
			BeanCursoJsp usuario = daoUsuario.consultar(user);
			request.getSession().setAttribute("userEscolhido", usuario);
			request.setAttribute("userEscolhido", usuario);
			RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
			// request.setAttribute("usuario", daoUsuario.listar());
			// request.setAttribute("msg", "Salvo com sucesso!");
			request.setAttribute("userEscolhido", usuario);
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String numero = request.getParameter("numero");
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
			request.setAttribute("msg", "Salvo com sucesso!");
		    request.setAttribute("usuario", daoTelefones.listar());
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
