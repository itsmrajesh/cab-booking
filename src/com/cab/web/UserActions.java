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
import com.cab.domain.Ride;
import com.cab.domain.Route;
import com.cab.domain.User;
import com.cab.driverdao.DDao;
import com.cab.driverdao.DDaoImpl;
import com.cab.userdao.UserDao;
import com.cab.userdao.UserDaoImpl;

@WebServlet(urlPatterns = { "/signup", "/login", "/dashboard", "/checkride", "/confirmbooking", "/enduserride",
		"/myprofile", "/myrides" })
public class UserActions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dao dao = new DaoImpl();
	User user = null;
	private UserDao userDao = new UserDaoImpl();
	private DDao dDao = new DDaoImpl();
	private Cabdriver driver;
	private Route routeObj;
	private boolean canRide = true;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Booking rideinfo = null;
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		String message = "no message";
		session.setAttribute("usermessage", "you are cool!!!");
		if (url.endsWith("signup")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			User newUser = new User(name, email, phone, password);
			if (dao.userSignup(newUser)) {
				message = "Hey " + name
						+ " you have successfully signup, go Login now <a href='login.html'>click here</a> ";
				session.setAttribute("usermessage", message);
				response.sendRedirect("showstatus.jsp");
			}
		} else if (url.endsWith("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (email.equalsIgnoreCase("admin@cab.com") && password.equals("admin")) {
				response.sendRedirect("admin.html");
			} else if ((user = dao.userLogin(email, password)) != null) {
				session.setAttribute("name", user.getName());
				session.setAttribute("email", email);
				response.sendRedirect("dashboard");
			} else {
				String errorMsg = "Invalid Login!! <br> New User, Signup: <a href='signup.html'> Click Here </a>";
				errorMsg += "<br> Login : <a href='login.html'> Click Here </a>";
				session.setAttribute("dmsg", errorMsg);
				response.sendRedirect("dmsg.jsp");
			}
		} else if (url.endsWith("dashboard")) {
			List<Route> avalRoutes = dao.getAllRoutes();
			request.setAttribute("routes", avalRoutes);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("checkride")) {
			String email = session.getAttribute("email").toString();
			String route = request.getParameter("route");
			// boolean status = userDao.canRide(email);
			if (canRide) {
				rideinfo = userDao.getRideDetails(route);
				try {
					driver = rideinfo.getCab();
					routeObj = rideinfo.getRoute();
				} catch (Exception e) {
					message = "No cabs available to ride now, try later";
					session.setAttribute("usermessage", message);
					response.sendRedirect("showstatus.jsp");
				}
				List<Cabdriver> driverList = new ArrayList<>();
				List<Route> routeList = new ArrayList<>();
				if (driver != null && routeObj != null) {
					driverList.add(driver);
					routeList.add(routeObj);
					request.setAttribute("driver", driverList);
					request.setAttribute("routes", routeList);
					RequestDispatcher rd = request.getRequestDispatcher("rideinfo.jsp");
					rd.forward(request, response);
				}
			} else {
				message = "Your are already in ride...";
				session.setAttribute("usermessage", message);
				response.sendRedirect("showstatus.jsp");
			}
		} else if (url.endsWith("confirmbooking")) {
			String email = session.getAttribute("email").toString();
			boolean status = userDao.confirmBooking(new Booking(routeObj, driver), email);
			if (status) {
				canRide = false;
				dDao.assignDriver(driver.getDlid(), "ongoing");
				message = "Your ride has booked successfully, enjoy your ride now";
				session.setAttribute("usermessage", message);
				response.sendRedirect("showstatus.jsp");
			} else {
				message = "Something went wrong, Try again";
				session.setAttribute("usermessage", message);
				response.sendRedirect("showstatus.jsp");
			}
		} else if (url.endsWith("enduserride")) {
			canRide = true;
			session.setAttribute("dmsg", "Ride ended successfully");
			response.sendRedirect("dmsg.jsp");
		} else if (url.endsWith("myprofile")) {
			// display user profile
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("myrides")) {
			// display user rides
			String email = session.getAttribute("email").toString();
			List<Ride> rides = userDao.getUserRides(email);
			request.setAttribute("rides", rides);
			RequestDispatcher rd = request.getRequestDispatcher("userrides.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
