package br.edu.ifsp.arq.ads.brotinho.servlets.helpers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.ads.brotinho.model.daos.RecipeDao;
import br.edu.ifsp.arq.ads.brotinho.model.entities.Recipe;
import br.edu.ifsp.arq.ads.brotinho.model.entities.User;
import br.edu.ifsp.arq.ads.brotinho.utils.SearcherDataSource;


public class LogoutHelper implements Helper {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
		
		List<Recipe> recipes = new RecipeDao(
				SearcherDataSource.getInstance().getDataSource()
		).getRecipesByIds("4,5,7,8", "0");
		System.out.println(recipes.size());		
		
		req.setAttribute("recipes", recipes);
		req.setAttribute("user", new User());
		return "/home.jsp";
	}

}
