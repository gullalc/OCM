<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Validation.js">

</script>
<title>Send Message</title>
</head>
<body>
<%
if(session.getAttribute("username")==null)
	response.sendRedirect("/OCM");
%>
<jsp:include page="Header.jsp"></jsp:include>
<center>
	<div id="sendmsg">
		<form enctype="multipart/form-data">
			<table id="msgtab">
				<tr>
					<th colspan=3>Send Message Form<br>
					<br></th>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Username"); %>
					</td>
					<td class="col2"><input type="text" name="uname" id="uname" /></td>
					<td class="col3"><span id="uname_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Message"); %>
					</td>
					<td class="col2"><textarea name="message" id="message" rows=4
							cols=20 onkeyup="msgCount()" onkeydown="msgCount()"></textarea><br>
						Characters Remaining:<input readonly size="4" value="140"
						name="msglen" id="msglen" /></td>
					<td class="col3"><span id="message_error"></span></td>
				</tr>
			</table>
			<div id="but7">
				<input type="button" id="sub" value="Submit" onclick="verify()" />
			</div>
		</form>
		<script type="text/javascript">
		function verify()
		{
			if(document.getElementById("uname").value.length<1)
				{
				document.getElementById("uname_error").innerHTML="Username cannot be empty";
				}
			else if(document.getElementById("message").value.length<1)
				{
				document.getElementById("uname_error").innerHTML="";
				document.getElementById("message_error").innerHTML="Message cannot be empty";
				}
			else
				{
				document.getElementById("uname_error").innerHTML="";
				document.getElementById("message_error").innerHTML="";
				subMessage();
				}
		}
		</script>
	</div>

</center>
</body>
</html>