<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="Validation.js">
</script>

<title>Contact Us</title>
</head>
<body>
	<%! String email;
		String username;
	%>
	<%  
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<div id="div_complaint">
		<form action="http://localhost:8080/OCM/complaint" id="form_complaint" name="form_complaint" method="POST">
			<table id="comptab">
				<tr>
				<th colspan=2>Complaint Form<br><br></th>
				</tr>
				<tr>
					<td class="col1"><%out.println("Subject"); %></td>
					<td class="col2"><input type="text" id="subject" name="subject"/></td>
				</tr>
				<tr>
					<%
					if(session.getAttribute("username")!=null&&session.getAttribute("username")!=null)
					{
						email=session.getAttribute("email").toString();
						username=session.getAttribute("username").toString();
					
					if (session.getAttribute("username")==null)
					  {
							out.println("<td class='col1'>Email</td>");
							out.println("<td class='col2'><input type='text' id='email' name='email' readonly value ="+email+"></td>");
					  }
					  else
					  {
						    out.println("<td class='col1'>Username</td>");
							out.println("<td class='col2'><input type='text' id='username' name='username' readonly value ="+username+"></td>");
					  }
					
					}
					
					%>
				</tr>
				<tr>
					<td class="col1"><%out.println("Complaint/Suggestion"); %></td>
					<td class="col2"><textarea name="comp_sugg" id="comp_sugg" rows="5" cols="20"></textarea></td>
			</table>
			<div id="but8">
				<input type="Submit" id="Submit" value="Submit" onclick= ""  /><%out.println("&nbsp &nbsp &nbsp");%>
				<input type="button" value="Reset" onclick="window.location.href='http://localhost:8080/OCM/complaint.jsp'"/>
	 	 	</div>
		</form>
	</div>
	
</body>
</html>