package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DaoGanttChart;
import entidades.Projetos;
import entidades.Series;

@WebServlet("/pages/buscarDatasPlanejamento")
public class buscarDatasPlanejamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public buscarDatasPlanejamento() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Projetos> projetos = new DaoGanttChart().listarProjetos();
			if (!projetos.isEmpty()) {
				String gantJson = new Gson().toJson(projetos);
				response.setStatus(200);
				response.getWriter().write(gantJson);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Series series = new Series();
			series.setDataInicial(request.getParameter("start"));
			series.setDataFinal(request.getParameter("end"));
			series.setId(Long.parseLong(request.getParameter("serie")));
			series.setProjeto(Long.parseLong(request.getParameter("projeto")));
			new DaoGanttChart().atualizar(series);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
