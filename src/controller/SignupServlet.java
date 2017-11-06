package controller;

import dao.AuthDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignupServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("beginSignup");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String msg = "";
		// System.out.println("username=" + username);
		// System.out.println("password=" + password);
		// System.out.println("msg=" + msg);
		// System.out.println("role=" + role);
		// System.out.println("firstname=" + firstName);
		// System.out.println("lastname=" + lastName);

		AuthDAO auth_signup = new AuthDAO();

		if (username.equals("") || username.equals(null)) {
			request.getSession().setAttribute("msg", "Enter username");
			response.sendRedirect("/Web2/signup.jsp");
		} else if (password.equals("") || password.equals(null)) {
			request.getSession().setAttribute("msg", "Enter password");
			response.sendRedirect("/Web2/signup.jsp");
		} else if (firstName.equals("") || firstName.equals(null)) {
			request.getSession().setAttribute("msg", "Enter FitstName");
			response.sendRedirect("/Web2/signup.jsp");
		} else if (lastName.equals("") || lastName.equals(null)) {
			request.getSession().setAttribute("msg", "Enter LastName");
			response.sendRedirect("/Web2/signup.jsp");
		} else {
			if (auth_signup.checkUserNameAvailable(username)) {
				auth_signup.enterNewUser(username, password, role, firstName, lastName);
				/*
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("firstname", firstName);
				request.getSession().setAttribute("lastname", lastName);
				response.sendRedirect("/Web2/index.jsp");
				*/
				request.getSession().setAttribute("msg", "Account Created Successfully.");
				response.sendRedirect("/Web2/login.jsp");
				
			} else {
				request.getSession().setAttribute("msg", "Invalid username");
				response.sendRedirect("/Web2/signup.jsp");
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
