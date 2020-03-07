package com.cab.web;

import java.io.IOException;
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
import com.cab.domain.Cabdriver;
import com.cab.domain.Ride;
import com.cab.driverdao.DDao;
import com.cab.driverdao.DDaoImpl;

/**
 * Servlet implementation class DriverActions
 */
@WebServlet(urlPatterns = { "/dsignup", "/dlogin", "/driverdashboard","/endridedriver","/driverrides" })
public class DriverActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = new DaoImpl();
	private DDao ddao = new DDaoImpl();
	private Cabdriver driver;
	private Ride ride;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		if (url.endsWith("dsignup")) {
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String email, phone, dlid, carname, vechileno, address, status = "Pending";
			email = request.getParameter("email");
			phone = request.getParameter("phone");
			dlid = request.getParameter("dlid");
			carname = request.getParameter("carname");
			vechileno = request.getParameter("vechileno");
			address = request.getParameter("address");

			Cabdriver driver = new Cabdriver(name, age, email, phone, dlid, carname, vechileno, address, status);

			if (dao.driverSignup(driver)) {
				session.setAttribute("dmsg", "you have succesfully signed up, Go login now");
				response.sendRedirect("dmsg.jsp");
			} else {
				session.setAttribute("dmsg", "Duplicate Entry, Sorry..");
				response.sendRedirect("dmsg.jsp");
			}

		} else if (url.endsWith("dlogin")) {
			String email = request.getParameter("email");
			String dlid = request.getParameter("dlid");
			driver = ddao.login(email, dlid);
			if (driver != null) {
				session.setAttribute("dname", driver.getName());
				session.setAttribute("ddlid", dlid);
				response.sendRedirect("driverdashboard");
			}else {
				String message = "Invalid Login! <br>New User, Signup: <a href='driversignup.html'> Click Here </a>";
				session.setAttribute("dmsg", message);
				response.sendRedirect("dmsg.jsp");
			}
		} else if (url.endsWith("driverdashboard")) {
			String dlid = session.getAttribute("ddlid").toString();
			ride = ddao.getOngoingRide(dlid);
			if(ride == null) {
				ride = new Ride("example@cabs.com", "YOUR DL DISPLAYS HERE", "ROUTE DISPLAYS HERE EX:-KBS-MTHL-405", 250.00, "");
			}
			request.setAttribute("ride", ride);
			RequestDispatcher rd = request.getRequestDispatcher("driverdashboard.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("endridedriver")) {
			String dlid = session.getAttribute("ddlid").toString();
			ddao.assignDriver(dlid, "null");
			ddao.endRide(dlid);
			response.sendRedirect("enduserride");
		}else if(url.endsWith("driverrides")) {
			String dlid = session.getAttribute("ddlid").toString();
			List<Ride> rides = ddao.getAllRides(dlid);
			request.setAttribute("rides", rides);
			RequestDispatcher rd = request.getRequestDispatcher("driverrides.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
