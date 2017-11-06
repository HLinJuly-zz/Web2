package controller;
import model.User;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("beginLogout");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		//String role = request.getParameter("role");
		String msg = "";
		// System.out.println("username=" + username);
		// System.out.println("password=" + password);
		// System.out.println("msg=" + msg);
		request.getSession().setAttribute("msg", "You have logged out.");
		User user=(User)request.getSession().getAttribute("user");
		user.setLoggedin(false);
		//request.getSession().setAttribute("user", user);
		response.sendRedirect("/Web2/login.jsp");
		request.getSession().removeAttribute("user");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
