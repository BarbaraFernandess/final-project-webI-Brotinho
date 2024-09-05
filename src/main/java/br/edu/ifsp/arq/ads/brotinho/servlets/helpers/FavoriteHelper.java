package br.edu.ifsp.arq.ads.brotinho.servlets.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.ads.brotinho.model.daos.RecipeDao;
import br.edu.ifsp.arq.ads.brotinho.model.daos.UserDao;
import br.edu.ifsp.arq.ads.brotinho.model.entities.Recipe;
import br.edu.ifsp.arq.ads.brotinho.model.entities.User;
import br.edu.ifsp.arq.ads.brotinho.utils.SearcherDataSource;


public class FavoriteHelper implements Helper {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("[FAV]");
		HttpSession session = req.getSession(false);
		
		String recipeId = req.getParameter("recipe_id");
		String favoriteAction = req.getParameter("favorite_action");
		String sessionUserId = 
				session != null && 
				session.getAttribute("user_id") != null 
					? (String) session.getAttribute("user_id") 
					: "0";
		
		UserDao userDao = new UserDao(SearcherDataSource.getInstance().getDataSource());
		switch(favoriteAction) {
			case "save":
				userDao.saveUserFavorite(sessionUserId, recipeId);
				break;
			case "delete":
				userDao.deleteUserFavorite(sessionUserId, recipeId);
				break;
		}
		
		Recipe recipe = new RecipeDao(
				SearcherDataSource.getInstance().getDataSource()
		).getRecipeById(recipeId, sessionUserId);
		req.setAttribute("recipe", recipe);
		req.setAttribute("user", (User) session.getAttribute("user"));
		
		return "/recipe.jsp?id=" + recipeId;
	}

}
