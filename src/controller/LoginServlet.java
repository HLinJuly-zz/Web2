package controller;
import model.User;
import dao.AuthDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("beginLogin");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String msg = "";
		int id=0;
		// System.out.println("username=" + username);
		// System.out.println("password=" + password);
		// System.out.println("msg=" + msg);
		AuthDAO auth_login=new AuthDAO();
		
		if ((username.equals("")||username.equals(null))&& (password.equals("")||password.equals(null))) {
			request.getSession().setAttribute("msg", "Enter username and password.");
			response.sendRedirect("/Web2/login.jsp");

		} else if (username.equals("")||username.equals(null)) {
			request.getSession().setAttribute("msg", "Enter username.");
			response.sendRedirect("/Web2/login.jsp");

		} else if (password.equals("")||password.equals(null)) {
			request.getSession().setAttribute("msg", "Enter password.");
			response.sendRedirect("/Web2/login.jsp");

		} else if (username.equals(null) && password.equals(null)) {
			request.getSession().setAttribute("msg", "Enter username and password");
			response.sendRedirect("/Web2/login.jsp");

		} 
		
		else if((id=auth_login.checkUserPass(username,password,role))!=-1){
			
	
			User user=new User();
			user=auth_login.getUserById(id);
			/*
			request.getSession().setAttribute("username", newuser.getUsername());
			request.getSession().setAttribute("role", newuser.getRole());
			request.getSession().setAttribute("firstname", newuser.getFirstName());
			request.getSession().setAttribute("lastname", newuser.getLastName());
			request.getSession().setAttribute("log","true");
			*/
			user.setLoggedin(true);
			request.getSession().setAttribute("user",user);
			response.sendRedirect("/Web2/index.jsp");
		}
		else{
			request.getSession().setAttribute("msg", "Invalid username or Password or role!");
			response.sendRedirect("/Web2/login.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
