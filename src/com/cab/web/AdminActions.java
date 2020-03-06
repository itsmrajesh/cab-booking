package com.cab.web;

import java.io.IOException;
import java.util.List;
import java.util.Random;

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
import com.cab.domain.Route;
import com.cab.domain.User;

@WebServlet(urlPatterns = { "/addnewroute", "/assigndriver", "/viewroute", "/editroute", "/viewalldrivers",
		"/reviewstatus", "/updatestatus", "/viewallroutes", "/updateroute", "/viewallusers", "/viewallrides" })
public class AdminActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = new DaoImpl();
	private String message = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		if (url.endsWith("newroute")) {
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			String route, dlid;
			Random rand = new Random();
			int random = rand.nextInt(1000);
			route = start + "-" + end + "-" + random;
			dlid = request.getParameter("dlid");
			int kms = Integer.parseInt(request.getParameter("kms"));
			String fairStr = request.getParameter("fair");
			double fair = kms * 2.5;
			try {
				if (fairStr.length() > 0) {
					fair = Double.parseDouble(fairStr);
				}
			} catch (NullPointerException e) {
				fair = kms * 10.5;
			}
			Route routeObj = new Route(route, kms, fair);
			if (dao.addNewRoute(routeObj)) {
				message = "Route : " + route + " added successfully";
				session.setAttribute("message", message);
				response.sendRedirect("adminmsg.jsp");
			} else {
				message = "Route : " + route + " already exists";
				session.setAttribute("message", message);
				response.sendRedirect("adminmsg.jsp");
			}

		} else if (url.endsWith("assigndriver")) {
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			String route, dlid;
			route = start + "-" + end;
			dlid = request.getParameter("dlid");
			int kms = Integer.parseInt(request.getParameter("kms"));
			String fairStr = request.getParameter("fair");
			double fair = kms * 2.5;
			try {
				if (fairStr.length() > 0) {
					fair = Double.parseDouble(fairStr);
				}
			} catch (NullPointerException e) {
				fair = kms * 2.5;
				e.printStackTrace();
			}
			Route routeObj = new Route(route, kms, fair);

		} else if (url.endsWith("viewallroutes")) {
			List<Route> routes = dao.getAllRoutes();
			request.setAttribute("routes", routes);
			RequestDispatcher rd = request.getRequestDispatcher("viewroutes.jsp");
			rd.forward(request, response);

		} else if (url.endsWith("viewroute")) {
			String route = request.getParameter("route");
			Route routeObj = dao.getRoute(route);
			session.setAttribute("myroute", routeObj.getRoute());
			List<Cabdriver> drivers = dao.getAllCabDrivers();
			request.setAttribute("drivers", drivers);
			request.setAttribute("route", routeObj);
			RequestDispatcher rd = request.getRequestDispatcher("assignroute.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("updateroute")) {
			String route = request.getParameter("route");
			int kms = Integer.parseInt(request.getParameter("kms"));
			String dlid = request.getParameter("dlid");
			String fairStr = request.getParameter("fair");
			double fair = Double.parseDouble(fairStr);
			Route routeObj = new Route(route,kms, fair);
			String myRoute = session.getAttribute("myroute").toString();
			if (dao.assignRoute(routeObj, myRoute)) {
				message = "Route updated successfully ";
				session.setAttribute("message", message);
				response.sendRedirect("adminmsg.jsp");
			}else {
				message = "Route updated failed ";
				session.setAttribute("message", message);
				response.sendRedirect("adminmsg.jsp");
			}
		} else if (url.endsWith("viewallusers")) {
			List<User> users = dao.getAllUsers();
			request.setAttribute("users", users);
			RequestDispatcher rd = request.getRequestDispatcher("viewallusers.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("viewalldrivers")) {
			List<Cabdriver> drivers = dao.getAllCabDrivers();
			request.setAttribute("drivers", drivers);
			RequestDispatcher rd = request.getRequestDispatcher("viewalldrivers.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("reviewstatus")) {
			String dlid = request.getParameter("dlid"); // updatedriverstatus.jsp
			session.setAttribute("dlid", dlid);
			RequestDispatcher rd = request.getRequestDispatcher("updatedriverstatus.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("updatestatus")) {
			String status = request.getParameter("status");
			String dlid = session.getAttribute("dlid").toString();
			if (dao.updateStatus(dlid, status.toLowerCase())) {
				message = "Status updated succesfully";
				session.setAttribute("message", message);
				response.sendRedirect("adminmsg.jsp");
			} else {
				message = "Some thing went wrong";
				session.setAttribute("message", message);
				response.sendRedirect("adminmsg.jsp");
			}
		} else if(url.endsWith("viewallrides")) {
			List<Ride> allRides = dao.getAllRides();
			request.setAttribute("rides", allRides);
			RequestDispatcher rd = request.getRequestDispatcher("allrides.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
