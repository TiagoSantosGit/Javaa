package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BeanCadCli;

@WebServlet("/CadCliServLet")
public class CadCliServLet extends HttpServlet {

	private static final long serialVersionUID = 2L;

	public CadCliServLet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().append("Served at: ").append(req.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BeanCadCli cadcli = new BeanCadCli();
		cadcli.setCodigo(Integer.parseInt(req.getParameter("codigo")));
		cadcli.setEndereco(req.getParameter("endereco"));
		cadcli.setNome(req.getParameter("nome"));
		cadcli.setNumero(Integer.parseInt(req.getParameter("numero")));
		cadcli.salveCli(cadcli);

	}
}
