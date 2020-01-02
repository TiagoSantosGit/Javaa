package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import user.UserLogado;

@WebFilter(
           urlPatterns = { "/pages/acessoAoSistema.jsp" })
public class FilterAutenticacao implements Filter {

// faz alguma coisa quando a aplicação e derrubada
    @Override
    public void destroy() {
    }

// imtercepta todas as requisições
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        session.getAttribute("usuario");

        UserLogado userLogado = (UserLogado) session.getAttribute("usuario");
        if (userLogado == null) { // usuario não logado
            RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
            dispatcher.forward(request, response);
            return; // para o processamento para redirecionar
        }

        chain.doFilter(request, response);
        System.out.println("interceptando");
    }

// executa alguma coisa quando a aplicação é iniciada
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
