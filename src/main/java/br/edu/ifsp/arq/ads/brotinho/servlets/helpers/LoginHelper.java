package br.edu.ifsp.arq.ads.brotinho.servlets.helpers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.ads.brotinho.model.daos.RecipeDao;
import br.edu.ifsp.arq.ads.brotinho.model.daos.UserDao;
import br.edu.ifsp.arq.ads.brotinho.model.entities.Recipe;
import br.edu.ifsp.arq.ads.brotinho.model.entities.User;
import br.edu.ifsp.arq.ads.brotinho.utils.SearcherDataSource;

public class LoginHelper implements Helper {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println("Login Helper");
		System.out.println("email " + email);
		System.out.println("password " + password);
		
		UserDao userDao = new UserDao(SearcherDataSource.getInstance().getDataSource());
		Optional<User> optional = userDao.getUserByEmailAndPassword(email, password);
		if (optional.isPresent()) {
			User user = optional.get();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("user", user);
			session.setAttribute("user_id", String.valueOf(user.getId()));
			
			
			System.out.println(user.getId());
			System.out.println("Hydrating  Home...in LoginHelper");

			List<Recipe> recipes = new RecipeDao(
					SearcherDataSource.getInstance().getDataSource()
			).getRecipesByIds("4,5,7,8", String.valueOf(user.getId()));
			
			req.setAttribute("recipes", recipes);
			req.setAttribute("user", user);
			
			return "/home.jsp";
		} else {
			req.setAttribute("result", "loginError");
			return "/login.jsp";
		}
	}

}
