package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoEvento;
import entidades.Eventos;

@WebServlet("/pages/buscarCalendarioDatas")
public class buscarCalendarioDatas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public buscarCalendarioDatas() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Eventos> eventos = new DaoEvento().listar();
			int totalEventos = eventos.size();
			int index = 1;
			String datas = "[";
			// "[{ \"title\": \"All Day Event\", \"start\": \"2017-02-01\"}]";
			for (Eventos ev : eventos) {
				datas += "{ \"title\": \"" + ev.getDescricaoEvento() + "\", \"start\": \"" + ev.getDataEvento() + "\"}";
				if (index < totalEventos) {
					datas += ",";
				}
				index++;
			}
			datas += "]";
			response.setStatus(200);
			response.getWriter().write(datas);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
