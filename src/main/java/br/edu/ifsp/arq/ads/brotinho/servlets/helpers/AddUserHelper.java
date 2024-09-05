package br.edu.ifsp.arq.ads.brotinho.servlets.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsp.arq.ads.brotinho.model.daos.UserDao;
import br.edu.ifsp.arq.ads.brotinho.model.entities.User;
import br.edu.ifsp.arq.ads.brotinho.utils.PasswordEncode;
import br.edu.ifsp.arq.ads.brotinho.utils.SearcherDataSource;


public class AddUserHelper implements Helper {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(PasswordEncode.encode(password));
		
		System.out.println("name " + name);
		System.out.println("email " + email);
		System.out.println("pw " + password);
		
		UserDao userDao = new UserDao(SearcherDataSource.getInstance().getDataSource());
		
		if(userDao.save(user)) {
			req.setAttribute("result", "registered");
			return "/login.jsp";
		}else {
			req.setAttribute("result", "notRegistered");
			return "/user-register.jsp";
		}
	}

}

