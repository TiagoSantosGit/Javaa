package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/carregarDadosDataTable")
public class carregarDadosDataTable extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public carregarDadosDataTable() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = "{" + "\"draw\": 9," + "\"recordsTotal\": 57, " + "\"recordsFiltered\": 57, " + "\"data\": ["
                + " [" + "\"Garrett\", " + "\"Winters\", " + "\"Accountant\", " + "\"Tokyo\", " + "\"25th Jul 11\", "
                + "\"$170,750\" " + " ]," + " [" + "\"Airi\"," + "\"Satou\"," + "\"Accountant\"," + "\"Tokyo\","
                + "\"28th Nov 08\"," + "\"$162,700\"" + "] " + "]" + "}";
        response.setStatus(200); // reposta completa
        response.getWriter().write(json); // json de resposta
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
