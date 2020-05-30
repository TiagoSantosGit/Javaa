package br.com.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;
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

import br.com.JPAUtil.JPAUtil;
import br.com.entidades.Pessoa;

@WebFilter(urlPatterns = { "/*" })
public class FilterAutenticacao implements Filter, Serializable {

	private static final long serialVersionUID = -5253618778296673692L;
	@Inject
	private JPAUtil jpaUtil;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		String url = req.getServletPath();
		if (!url.equalsIgnoreCase("/index.jsf") && usuarioLogado == null) {
			RequestDispatcher dispacher = request.getRequestDispatcher("/index.jsf");
			dispacher.forward(request, response);
			return;
		} else {
			// executa as ações do request e do response
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		jpaUtil.getEntityManager();
	}

}
