<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Validation.js">
</script>
<title>Registration</title>
</head>
<jsp:include page="Header.jsp"></jsp:include>
<body onLoad="dateClick()">
<center>
	<div id="register">
		<form id="reg" name="reg" method="POST">
			<table id="regtab">
				<tr>
					<th colspan=3>Registration<br>
					<br></th>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("First name*");%>
					</td>
					<td class="col2"><input type="text" name="fname" id="fname" /></td>
					<td class="col3"><span Id="fname_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Last name*");%>
					</td>
					<td class="col2"><input type="text" name="lname" id="lname" /></td>
					<td class="col3"><span id="lname_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Username*");%>
					</td>
					<td class="col2"><input type="text" name="uname" id="uname" /></td>
					<td class="col3"><span id="uname_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Password*");%>
					</td>
					<td class="col2"><input type="Password" name="pass" id="pass"
						onclick="passChar()" /></td>
					<td class="col3"><span id="pass_error"></span></td>
				</tr>
				<tr id=pass_char>
					<td class="col1"></td>
					<td class="col2">
						<%out.println("Use 6 to 12 characters"); %>
					</td>
					<td class="col3"></td>
				</tr>

				<tr>
					<td class="col1">
						<%out.println("Confirm Password*");%>
					</td>
					<td class="col2"><input type="Password" name="cpass"
						id="cpass" /></td>
					<td class="col3"><span id="cpass_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Email*");%>
					</td>
					<td class="col2"><input type="text" name="email" id="email" /></td>
					<td class="col3"><span id="email_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Class**");%>
					</td>
					<td class="col2"><input type="text" name="cls" id="cls" /></td>
					<td class="col3"><span id="cls_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Batch**");%>
					</td>
					<td class="col2"><input type="text" name="batch" id="batch" /></td>
					<td class="col3"><span id="batch_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Roll No**");%>
					</td>
					<td class="col2"><input type="text" name="rno" id="rno" /></td>
					<td class="col3"><span id="rno_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Address");%>
					</td>
					<td class="col2"><textarea name="adr" id="adr" rows="5"
							cols="20"></textarea></td>
					<td class="col3" rowspan=2><div id="adr_error"></div></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Date of Birth*");%>
					</td>
					<td class="col2"><select name="day" id="day">
						</select>
						<%out.println("&nbsp &nbsp"); %> <select name="month" id="month"></select>
						<%out.println("&nbsp &nbsp"); %> <select name="year" id="year"></select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Gender*");%>
					</td>
					<td class="col2"><input type="text" name="gender" id="gender" /></td>
					<td class="col3"><span id="gender_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Occupation*");%>
					</td>
					<td class="col2"><select name="occ" id="occ">
							<option value="Select">--Select--</option>
							<option value="Alumni">Alumni</option>
							<option value="Student">Student</option>
							<option value="Faculty">Faculty</option>
					</select></td>
				</tr>
			</table>
			<div id="but1">
				<input type="button" id="sub" value="Submit" onclick="tab('reg')" />
				<%out.println("&nbsp &nbsp &nbsp");%>
				<input type="button" value="Reset"
					onclick="window.location.href='http://localhost:8080/OCM/Register.jsp'" />
			</div>
		</form>
		<h6>*Mandatory for all</h6>
		<h6>**Mandatory for students only</h6>
	</div>
	</center>
</body>
</html>