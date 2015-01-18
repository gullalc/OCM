<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<link href="style.css" type="text/css" rel="stylesheet"/>
<title>Add Thought of the day</title>
</head>

<body>

	<div id="div_tod">
		<form id="from_tod" name="form_tod" method="POST" action="http://localhost:8080/OCM/tod">  <%--change the method as u wish--%>
			<table id="table_tod">
				<tr>
					<th colspan=2>Add a new Thought<br><br></th>
				<tr>
				<tr>
					<td class="col1"><%out.println("Thought");%></td>
					<td class="col2"><textarea name="text_thought" id="text_thought" rows=6 cols=20></textarea></td>
				</tr>
				<tr>
					<td class="col1"><%out.println("Author");%></td>
					<td class="col2"><input type="text" name="text_author" id="text_author"/></td>	
				</tr>
			</table>
			<div id="but_tod">
				<input type="Submit" id="sub" value="Submit" onclick= ""  /><%out.println("&nbsp &nbsp &nbsp");%>
				<input type="button" value="Reset" onclick="window.location.href='http://localhost:8080/OCM/submittod.jsp'"/>
	 	 	</div>
	 	 	
		</form>
		
	</div>
<%
	 	 	
	 	 	%>
</body>
</html>