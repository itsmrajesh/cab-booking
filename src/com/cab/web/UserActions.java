package com.cab.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cab.dao.Dao;
import com.cab.dao.DaoImpl;
import com.cab.domain.Booking;
import com.cab.domain.Cabdriver;
import com.cab.domain.Route;
import com.cab.domain.User;
import com.cab.userdao.UserDao;
import com.cab.userdao.UserDaoImpl;

@WebServlet(urlPatterns = { "/signup", "/login", "/dashboard", "/checkride" })
public class UserActions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dao dao = new DaoImpl();
	User user = null;
	private UserDao userDao = new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Booking rideinfo = null;
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		String message = "no message";
		session.setAttribute("message", "you are cool!!!");
		if (url.endsWith("signup")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			User userObj = new User(name, email, phone, password);
			if (dao.userSignup(userObj)) {
				message = "Hey " + name + " you have successfully signup, go Login now";
				session.setAttribute("message", message);
				response.sendRedirect("showstatus.jsp");
			}
		} else if (url.endsWith("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if ((user = dao.userLogin(email, password)) != null) {
				session.setAttribute("name", user.getName());
				session.setAttribute("email", email);
				response.sendRedirect("dashboard");
			} else {
				response.sendRedirect("invalidlogin.html");
			}
		} else if (url.endsWith("dashboard")) {
			List<Route> avalRoutes = dao.getAllRoutes();
			request.setAttribute("routes", avalRoutes);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("checkride")) {
			String email = session.getAttribute("email").toString();
			String route = request.getParameter("route");
			boolean status = userDao.canRide(email);
			if (true) {
				rideinfo = userDao.getRideDetails(route);
				Cabdriver driver = rideinfo.getCab();
				Route routeObj = rideinfo.getRoute();
				List<Cabdriver> driverList = new ArrayList<>();
				driverList.add(driver);
				List<Route> routeList = new ArrayList<>();
				routeList.add(routeObj);
				request.setAttribute("driver", driverList);
				request.setAttribute("route", routeList);
				RequestDispatcher rd = request.getRequestDispatcher("rideinfo.jsp");
				rd.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
