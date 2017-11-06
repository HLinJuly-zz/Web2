<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.User"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<br />
<br />
<%
	User user=new User();
	if(session.getAttribute("user")!=""&&session.getAttribute("user")!=null){
	user=(User)session.getAttribute("user");
	
}
	//out.println(session.getAttribute("user"));
	if(!user.getLoggedin()){
		
		out.println("<h2 align=\"center\">" + "<font color=\"red\">" + "You need log in firstly." + "</font>"
				+ "</h2>");
	}
	else{
%>
<div class="style1">
<%
out.println(user.getUsername()+" has logged in Successfully as "+user.getRole());
%>
<%
out.println("<h5>Todays date is "+new java.util.Date()+"</h5>");
%>
<br/>
<h3>user info:</h3>
<table>
<tr><th>
<h4>UserName:</h4></th><th><h4><%=user.getUsername() %></h4></th>
</tr>
<tr><th>
<h4>Role:</h4></th><th><h4><%=user.getRole() %></h4></th>
</tr>
<tr><th>
<h4>FirstName:</h4></th><th><h4><%=user.getFirstName() %></h4></th>
</tr>
<tr><th>
<h4>LastName:</h4></th><th><h4><%=user.getLastName() %></h4></th>
</tr>
</table><br/>
<jsp:include page="logout.jsp"></jsp:include><br/>
</div>
<%} %>

<jsp:include page="nav.jsp"></jsp:include>
</body>
</html>