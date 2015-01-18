<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="Header.jsp"/>
<body>
<%!static String id,title,desc; %>
<% 

if(session.getAttribute("username")==null)
	{
	out.println("Only registered users can participate in Discussion Forum");
	}

else
{
	try
	{
		out.println("<center>");
		out.println("<h3><a href='http://localhost:8080/OCM/threadcreate.jsp'>Create new Thread</a></h3>");
		out.println("<h5>Or view existing thread by clicking below</h5>");
		out.println("<table>");
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
		Statement st1=con.createStatement();
		ResultSet rs1 = st1.executeQuery("select id,title from discussionforum order by postdate");
		while(rs1.next())
		{
			id=rs1.getString(1);
			title=rs1.getString(2);
			
			
			out.println("<tr><td>"+id+"</td><td>"+title+"</td><td><a href='http://localhost:8080/OCM/ViewThread'"+">Go to thread</a>");
			
		}
		
	}
	catch(Exception e)
	{
		
	}
}
%>
</body>
</html>