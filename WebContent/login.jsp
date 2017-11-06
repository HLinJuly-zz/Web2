<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.User"
	pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Login</title>
</head>
<body>
<br />
<br />
<br />
<br />

<%
	User user=new User();
	if(session.getAttribute("user")!=""&&session.getAttribute("user")!=null){
	user=(User)session.getAttribute("user");
	
	}
	//out.println(session.getAttribute("user"));
	if(user.getLoggedin()){
		
		out.println("<h2 align=\"center\">" + "<font color=\"red\">" + "You are already logged in." + "</font>"
				+ "</h2>");
%>
<div class="style1">
<jsp:include page="logout.jsp"></jsp:include><br/>
</div>
<%
	}
	else{
%>
<div class="style1">
<h1>Login</h1>
<br />

<%
	if (session.getAttribute("msg") == null || session.getAttribute("msg") == "") {
		out.println("<h4 align=\"center\">" + "<font color=\"red\">" + "Enter username and password" + "</font>"
				+ "</h4>");
	} else {
		out.println("<h4 align=\"center\">" + "<font color=\"red\">" + session.getAttribute("msg") + "</font>"
				+ "</h4>");
	}
	session.removeAttribute("msg");
%>
<form action="/Web2/LoginServlet" method="get">
<h3>
	username:<input type="text" name="username" /><br />
</h3>
<h3>
	password:&nbsp;<input type="password" name="password" />
</h3>
	
<h6>
	Choose your role: <input type="radio" name="role" value="supplier" />supplier&nbsp;
	<input type="radio" name="role" value="wholesaler" />wholesaler&nbsp;
    <input type="radio" name="role" value="retailer" />retailer&nbsp;
	<input type="radio" name="role" value="customer" checked />customer&nbsp;
</h6>

<h3>
<input type="submit" value="login" />	
</h3>

</form>
</div>
<%} %>
<jsp:include page="nav.jsp"></jsp:include>
</body>
</html>