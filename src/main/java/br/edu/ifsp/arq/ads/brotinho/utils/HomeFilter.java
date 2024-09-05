package br.edu.ifsp.arq.ads.brotinho.utils;

import java.io.IOException;
import java.util.List;

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

@WebFilter(urlPatterns = {"/home.jsp", "/"}, filterName = "HydrateHome")
public class HomeFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		System.out.println("Hidrating  Home...");

		String sessionUserId = 
				session != null && 
				session.getAttribute("user_id") != null 
					? (String) session.getAttribute("user_id") 
					: "0";
		List<Recipe> recipes = new RecipeDao(
				SearcherDataSource.getInstance().getDataSource()
		).getRecipesByIds("4,5,7,8", sessionUserId != null ? (String) sessionUserId : "0");
		System.out.println(recipes.size());		
		req.setAttribute("recipes", recipes);
		
		chain.doFilter(req, resp);	
	}

}
