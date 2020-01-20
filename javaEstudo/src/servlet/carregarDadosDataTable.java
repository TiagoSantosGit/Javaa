package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import entidades.Usuario;

@WebServlet("/pages/carregarDadosDataTable")
public class carregarDadosDataTable extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DaoUsuario daoUsuario = new DaoUsuario();

    public carregarDadosDataTable() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = daoUsuario.listar();

            if (!usuarios.isEmpty()) {
                String data = "";
                int totalUsuarios = usuarios.size();
                int index = 1;

                for (Usuario usuario : usuarios) {
                    data += " [\"" + usuario.getId() + "\", \"" + usuario.getLogin() + "\", \""
                            + usuario.getSenha() + "\"]";
                    if (index < totalUsuarios) {
                        data += ",";
                    }
                    index++;
                }
                String json = "{\"draw\": 1, \"recordsTotal\": " + usuarios.size() 
                        + ",\"recordsFiltered\": " + usuarios.size() + ",\"data\": [" + data + "]}";

                response.setStatus(200); // resposta completa
                response.getWriter().write(json); // json de resposta
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /*
     * Concatenar json na String String json = "{" + "\"draw\": 9," +
     * "\"recordsTotal\": 57, " + "\"recordsFiltered\": 57, " + "\"data\": [" + " ["
     * + "\"Garrett\", " + "\"Winters\", " + "\"Accountant\", " + "\"Tokyo\", " +
     * "\"25th Jul 11\", " + "\"$170,750\" " + " ]," + " [" + "\"Airi\"," +
     * "\"Satou\"," + "\"Accountant\"," + "\"Tokyo\"," + "\"28th Nov 08\"," +
     * "\"$162,700\"" + "] " + "]" + "}";
     */
}
