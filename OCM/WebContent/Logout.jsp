<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%


Connection conn = null;


try
{
	Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
	Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
	Statement st1=con.createStatement();
	
	int i = st1.executeUpdate("");

	if(i>0)
	{
	session.invalidate();
	response.sendRedirect("/OCM");
	}
	conn.close();
}
catch (Exception e)
{
	e.printStackTrace();
}

%>
</body>
</html>