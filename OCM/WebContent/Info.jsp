<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Validation.js">

</script>
<title>Info Page</title>

</head>
<body>
	<div id="information">
		<form id="info" name="info" method="POST">
			<table id="infotab">
				<tr>
					<th colspan=4>Information Form<br>
					<br></th>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Name");%>
					</td>
					<td class="col2"><input type="text" name="name" id="name" /></td>
					<td class="col3"><span id="name_error"></span></td>
					<td class="col4"><select name="name_list" id="name_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Gender");%>
					</td>
					<td class="col2"><input type="text" name="gender" id="gender" /></td>
					<td class="col3"><span id="gender_error"></span></td>
					<td class="col4"><select name="gender_list" id="gender_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Occupation");%>
					</td>
					<td class="col2"><select name="occ" id="occ" onchange="expr()"><option
								value="Select">--Select--</option>
							<option value="Alumni">Alumni</option>
							<option value="Student">Student</option>
							<option value="Faculty">Faculty</option>
					</select></td>
					<td class="col3"></td>
					<td class="col4"><select name="occ_list" id="occ_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Email");%>
					</td>
					<td class="col2"><input type="text" name="email" id="email" /></td>
					<td class="col3"><span id="email_error"></span></td>
					<td class="col4"><select name="email_list" id="email_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Class");%>
					</td>
					<td class="col2"><input type="text" name="cls" id="cls" /></td>
					<td class="col3"><span id="cls_error"></span></td>
					<td class="col4"><select name="cls_list" id="cls_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Batch");%>
					</td>
					<td class="col2"><input type="text" name="batch" id="batch" /></td>
					<td class="col3"><span id="batch_error"></span></td>
					<td class="col4"><select name="batch_list" id="batch_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("School");%>
					</td>
					<td class="col2"><input type="text" name="school" id="school" /></td>
					<td class="col3"><span id="school_error"></span></td>
					<td class="col4"><select name="school_list" id="school_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("School Batch");%>
					</td>
					<td class="col2"><input type="text" name="sbatch" id="sbatch" /></td>
					<td class="col3"><span id="sbatch_error"></span></td>
					<td class="col4"><select name="sbatch_list" id="sbatch_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Hobbies");%>
					</td>
					<td class="col2"><textarea name="hoby" id="hoby" rows="4"
							cols="20"></textarea></td>
					<td class="col3"><span id="hoby_error"></span></td>
					<td class="col4"><select name="hoby_list" id="hoby_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Interests");%>
					</td>
					<td class="col2"><textarea name="inter" id="inter" rows="4"
							cols="20"></textarea></td>
					<td class="col3"><span id="inter_error"></span></td>
					<td class="col4"><select name="inter_list" id="inter_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr id="Exp">
					<td class="col1">
						<%out.println("Experience");%>
					</td>
					<td class="col2"><textarea name="exp" id="exp" rows="4"
							cols="20"></textarea></td>
					<td class="col3"><span id="exp_error"></span></td>
					<td class="col4"><select name="exp_list" id="exp_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Favorite Quotations");%>
					</td>
					<td class="col2"><textarea name="quote" id="quote" rows="4"
							cols="20"></textarea></td>
					<td class="col3"><span id="quote_error"></span></td>
					<td class="col4"><select name="quote_list" id="quote_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Favourite Books");%>
					</td>
					<td class="col2"><textarea name="book" id="book" rows="4"
							cols="20"></textarea></td>
					<td class="col3"><span id="book_error"></span></td>
					<td class="col4"><select name="book_list" id="book_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("About me");%>
					</td>
					<td class="col2"><textarea name="abtme" id="abtme" rows="5"
							cols="20"></textarea></td>
					<td class="col3"><span id="abtme_error"></span></td>
					<td class="col4"><select name="abtme_list" id="abtme_list"><option
								value="Public">Public</option>
							<option value="Private">Private</option>
							<option value="Only me">Only me</option>
					</select></td>
				</tr>
			</table>
			<div id="but2">
				<input type="button" id="sub" value="Submit" onclick="tab('info')" />
				<%out.println("&nbsp &nbsp &nbsp");%>
				<input type="button" value="Reset"
					onclick="window.location.href='http://localhost:8080/OCM/Info.jsp'" />
			</div>
		</form>
	</div>
</body>
</html>