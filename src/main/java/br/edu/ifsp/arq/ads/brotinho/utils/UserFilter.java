package br.edu.ifsp.arq.ads.brotinho.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.ads.brotinho.model.entities.User;

@WebFilter(urlPatterns = {"/*"}, filterName = "HydrateUser")
public class UserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession(false);	
		
		User sessionUser = null;
		if(session != null && session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			System.out.printf("Hidrating  User...ID: %d | Email: %s\n", sessionUser.getId(), sessionUser.getEmail());
		} else {
			sessionUser = new User();
			System.out.printf("Hidrating  User...404_USER_NOT_FOUND\n");
		}
		
		req.setAttribute("user", sessionUser);
		chain.doFilter(req, resp);	
	}
	
}
