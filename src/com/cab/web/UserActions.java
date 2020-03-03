package com.cab.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cab.dao.Dao;
import com.cab.dao.DaoImpl;
import com.cab.domain.User;

/**
 * Servlet implementation class Testing
 */
@WebServlet(urlPatterns = { "/signup", "/login", "/showmyrides", "/dashboard" })
public class UserActions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dao dao = new DaoImpl();
	User user = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		if (url.endsWith("signup")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			User userObj = new User(name, email, phone, password);
			if (dao.userSignup(userObj)) {
				response.sendRedirect("showstatus.jsp");
			}
		} else if (url.endsWith("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if ((user = dao.userLogin(email, password)) != null) {

			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
