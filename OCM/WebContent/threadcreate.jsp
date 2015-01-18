<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="discussion.js">

</script>
<title>Create Thread</title>
</head>
<body>
<% 
if(session.getAttribute("username")==null)
	response.sendRedirect("/OCM");
%>
<jsp:include page="Header.jsp"></jsp:include>
<center>
	<div id="div_create">
		<form action="http://localhost:8080/OCM/createthread" id="form_create" name="form_create" method="POST">
			<table id="table_create">
				<tr>
					<th colspan=3>Create Thread<br><br></th>
				</tr>
				<tr> 
					<td class="col1"><%out.println("Title*");%></td> 	
					<td class="col2"><input type="text" name="thread_title" id="thread_title"/></td> 	
					<td class="col3"><span id="title_error"></span></td> 
				</tr>	
				<tr> 
					<td class="col1"><%out.println("Description*");%></td> 	
					<td class="col2"><textarea name="thread_desc" id="thread_desc" rows=7 cols=20></textarea></td> 	
					<td class="col3"><span id="desc_error"></span></td>
				</tr>	 
				<tr>
					<td class="col1"><%out.println("(*) These fields are mandatory"); %></td>
					<td class="col2"></td>
					<td class="col3"></td>
				</tr>	
			</table>
			<div id="but_thread">
				<input type="Submit" id="thread_post" value="Post" onclick= "discussion()"  /><%out.println("&nbsp &nbsp &nbsp");%>
				<input type="button" value="Reset" onclick="window.location.href='http://localhost:8080/OCM/threadcreate.jsp'"/>
			</div>
		</form>
	</div>
</center>
</body>
</html>