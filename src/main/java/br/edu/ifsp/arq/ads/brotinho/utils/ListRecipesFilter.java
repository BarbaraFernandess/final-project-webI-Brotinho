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

@WebFilter(urlPatterns = {"/list-recipes.jsp"}, filterName = "HydrateListRecipes")
public class ListRecipesFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		Boolean fav = String.valueOf(req.getParameter("fav")).equals("true");
		String search = req.getParameter("search");
		System.out.println("Hidrating  List Recipes...Search: " + search);
		
		String sessionUserId = 
				session != null && 
				session.getAttribute("user_id") != null 
					? (String) session.getAttribute("user_id") 
					: "0";
		RecipeDao dao = new RecipeDao(
				SearcherDataSource.getInstance().getDataSource()
		);
		List<Recipe> recipes = search == null 
				? fav ? dao.getAllFavoriteRecipes(sessionUserId) : dao.getAllRecipes(sessionUserId) 
				: fav ? dao.getFavoriteRecipes(search, sessionUserId) : dao.getRecipesBySearch(search, sessionUserId);
		System.out.println(recipes.size());
		
		req.setAttribute("recipes", recipes);
		req.setAttribute("fav", fav);
		chain.doFilter(req, resp);	
	}
	
}
