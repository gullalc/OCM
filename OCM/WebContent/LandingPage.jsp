<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.regex.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online College Magazine</title>
<style type="text/css">

body {background-image:url("images/landingpage.png");}
.center {
   width: 500px;
   height: 300px;
   position: absolute;
   left: 50%;
   top: 30%;
   margin-left: -250px;
   margin-top: -150px;
   border:5px solid #E6E6FA;
   border-radius: 10px;
   background-color:#fff;
}
table
{
width:100%;
height:100%;
}
table td
{

vertical-align:top;
color:#708090;
padding:0 10px;
}
.cont
{
width:200px;
}
.subm
{
border:none;
background-color:#E6E6FA;
color:#708090;
}
</style>
<script type="text/javascript">
var req=null;
function createXmlHttpRequest()
{
	if(window.ActiveXObject)
 	{
    	req=new ActiveXObject("Microsoft.XMLHTTP");
    }

    else if(window.XMLHttpRequest)
    {
        req=new XMLHttpRequest();
    }

}
function Login()
{
	createXmlHttpRequest();
	var username=document.getElementById("username").value;
	var password=document.getElementById("pass").value;
	var params="username="+username+"&password="+password;
	req.open("GET","http://localhost:8080/OCM/Login?"+params);
	req.onreadystatechange=processChange;
	req.send(null);
}
function processChange()
{

					if(req.readyState==4&&req.status==200)
					{
					
					var response=req.responseXML.getElementsByTagName("error");
					
					if(response.length>0)
					{
						document.getElementById("error").innerHTML="Invalid username on password";
					}
					else
						{
						window.location.href="http://localhost:8080/OCM/index.jsp";
						}
					}			
						
}
</script>
</head>
<body>
<div class="center">
		<table id="table_center">
				<tr>
				<td class="col_guest" ><h3>Guest</h3>
				<div class="cont">
				<form method="post">
				Email<br>
					<input type="text" name="email"></input><br><br>
					<input type="Submit" value="Submit" ></input>
				</form>
				<%
				String email=request.getParameter("email");
				if(email!=null)
				{
				 Pattern pattern;
				 Matcher matcher;
			 		
				  final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				  pattern = Pattern.compile(EMAIL_PATTERN);
				  matcher=pattern.matcher(email);
				  if(matcher.matches())
				  {
					  session.setAttribute("email", email);
					  response.sendRedirect("/OCM");
				  }
				  
				  
				  {
					  out.println("Invalid email address");
				  }
				}
				%>
				</div>
				</td>
				<td style="background-color:#E6E6FA"></td>
				<td class="col_user"><h3>Registered User</h3>
				<div class="cont">
					<form>
													Username:
													<br></br>
													<input type="text" id="username"></input>
													<br></br>
													Password
													<br></br>
													<input type="password" id="pass"></input>
													<br></br>

													<input type="button" class="subm" onclick="Login()"
														value="Login"></input>
													<br>
													<span id="error"></span>
												</form>
												
										
											
													
				</div>
				</td>
				</tr>
			</table>
</div>
</body>
</html>