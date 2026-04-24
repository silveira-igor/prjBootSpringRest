package controller.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration 
public class AuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("AuthFilter " + req.getRequestURI() + "(" + req.getMethod() + ")");
		HttpSession sessao = req.getSession(false);
        if (sessao == null || sessao.getAttribute("conta") == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso Negado!");
            return;
        }
        String conta = (String) sessao.getAttribute("conta");

        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                conta,
                null,
                Collections.emptyList()
            );
        SecurityContextHolder.getContext().setAuthentication(auth);
        System.out.println("Autenticação verificada com sucesso!");
        chain.doFilter(req, res);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {

	    String path = request.getRequestURI();
	    if (path.equals("/") ||
	    	    path.equals("/index.html") ||
	    	    path.equals("/favicon.ico") ||
	    	    path.startsWith("/auth") ||
	    	    path.startsWith("/css/") ||
	    	    path.startsWith("/js/") ||
	    	    path.startsWith("/images/")) {
	    	    return true;
	    }

	    return false;
	}
}