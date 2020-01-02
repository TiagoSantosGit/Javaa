package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserLogado;

@WebServlet("/ServletAutenticacao")
public class ServletAutenticacao extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAutenticacao() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        // neste momento pode ser feito uma valida��o do banco de dados
        if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {
        
            UserLogado userLogado = new UserLogado();
            userLogado.setLogin(login);
            userLogado.setSenha(senha);
        
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            session.getAttribute("usuario");
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/AcessoAoSistema.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
            dispatcher.forward(request, response);
        }
    }

}
