<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Validation.js">

</script>
<title>Post Photo</title>
</head>
<body>

<center>
<jsp:include page="Header.jsp"></jsp:include>
	<div id="postphoto">
		<form enctype="multipart/form-data" action="http://localhost:8080/OCM/PostPhoto" id="photo" method="post" >
			<table id="phototab">
				<tr>
					<th colspan=3>Photo Form<br>
					<br></th>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Title"); %>
					</td>
					<td class="col2"><input type="text" name="title" id="title" /></td>
					<td class="col3"><span id="title_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Photo"); %>
					</td>
					<td class="col2"><input type="file" name="foto" id="foto" /></td>
					<td class="col3"><span id="foto_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Description"); %>
					</td>
					<td class="col2"><textarea name="descrip" id="descrip" rows=5
							cols=20></textarea></td>
					<td class="col3"><span id="descrip_error"></span></td>
				</tr>

			</table>
			<div id="but4">
				<input type="submit" id="formsubmit"/>
				<input type="button" id="sub" value="Submit" onclick="check_submit()" />
			</div>
		</form>
	</div>
	</center>
</body>
</html>