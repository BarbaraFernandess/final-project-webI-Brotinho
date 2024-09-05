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

import br.edu.ifsp.arq.ads.brotinho.model.daos.RecipeDao;
import br.edu.ifsp.arq.ads.brotinho.model.entities.Recipe;

@WebFilter(urlPatterns = {"/recipe.jsp"}, filterName = "HydrateRecipe")
public class RecipeFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession(false);		
		
		String id = req.getParameter("id");
		System.out.println("Hidrating  Recipe...ID: " + id);
		
		String sessionUserId = 
				session != null && 
				session.getAttribute("user_id") != null 
					? (String) session.getAttribute("user_id") 
					: "0";
		System.out.println(sessionUserId);
		Recipe recipe = new RecipeDao(
				SearcherDataSource.getInstance().getDataSource()
		).getRecipeById(id, sessionUserId);
		req.setAttribute("recipe", recipe);
		
		chain.doFilter(req, resp);	
	}

}
