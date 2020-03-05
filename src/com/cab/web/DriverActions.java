package com.cab.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cab.dao.Dao;
import com.cab.dao.DaoImpl;
import com.cab.domain.Cabdriver;
import com.cab.driverdao.DDao;
import com.cab.driverdao.DDaoImpl;

/**
 * Servlet implementation class DriverActions
 */
@WebServlet(urlPatterns = { "/dsignup", "/dlogin", "/dboard" })
public class DriverActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = new DaoImpl();
	private DDao ddao = new DDaoImpl();

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
			vechileno = request.getParameter("vlno");
			address = request.getParameter("address");

			Cabdriver driver = new Cabdriver(name, age, email, phone, dlid, carname, vechileno, address, status);
			
			if(dao.driverSignup(driver)) {
				session.setAttribute("dmsg", "you have succesfully signed up, Go login now");
				response.sendRedirect("dmsg.jsp");
			}else {
				session.setAttribute("dmsg", "Duplicate Entry, Sorry..");
				response.sendRedirect("dmsg.jsp");
			}

		}else if(url.endsWith("dlogin")) {
			String email = request.getParameter("email");
			String dlid = request.getParameter("dlid");
			Cabdriver driver = ddao.login(email, dlid);
			if(driver != null) {
				// go driver dashboard
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
