<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="model.User"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>signup</title>
<link rel="stylesheet" type="text/css" href="style.css" />

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
		
		out.println("<h2 align=\"center\">" + "<font color=\"red\">" + "You are already a member." + "</font>"
				+ "</h2>");
%>
<div class="style1">
<jsp:include page="logout.jsp"></jsp:include>
</div>
<br/>
<%
	}
	else{
%>
<div class="style1">
<h1>Signup</h1>
<br />
<%
	if(session.getAttribute("msg")==null||session.getAttribute("msg")==""){
		out.println("<h4 align=\"center\">"+"<font color=\"red\">"+"Enter list"+"</font>"+"</h4>");
	}
	else{
		out.println("<h4 align=\"center\">"+"<font color=\"red\">"+session.getAttribute("msg")+"</font>"+"</h4>");
	}
	session.removeAttribute("msg");
%>
<form action="/Web2/SignupServlet" method="get">
<h3>
	username:<input type="text" name="username" /><br />
</h3>
<h3>
	password:&nbsp;<input type="password" name="password" /><br />
</h3>
<h3>
	FirstName:<input type="text" name="firstname" /><br />
</h3>
<h3>
	FirstName:<input type="text" name="lastname" /><br />
</h3>
<h5>
	Choose your role: <input type="radio" name="role" value="supplier" />supplier&nbsp;
	<input type="radio" name="role" value="wholesaler" />wholesaler&nbsp;
	<input type="radio" name="role" value="retailer" />retailer&nbsp;&nbsp;&nbsp;
	<input type="radio" name="role" value="customer" checked />customer&nbsp;
</h5>
<h3>
	<input type="submit" value="submit" />
	
</h3>
</form>

</div>
<%} %>

<jsp:include page="nav.jsp"></jsp:include>
	
</body>
</html>