<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

</script>
<title>Update Profile Pic</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div id="updatepic">
		<form enctype="multipart/form-data" action="http://localhost:8080/OCM/Updatedp" method="post">
			<table id="uppictab">
				<tr>
					<th colspan=3>Update Pic Form<br>
					<br></th>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Photo"); %>
					</td>
					<td class="col2"><input type="file" name="foto" id="foto" /></td>
					<td class="col3"><span id="pht"></span></td>
				</tr>

			</table>
			<div id="but6">
				<input type="Submit" id="sub" value="Submit" onclick="" />
			</div>
		</form>
		
	</div>
</body>
</html>