<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<link href="style.css" type="text/css" rel="stylesheet"/>
<title>Add Word of the day</title>
</head>
<body>

	<div id="div_wod">
		<form id="from_wod" name="form_wod" method="POST" action="http://localhost:8080/OCM/addword">  <%--change the method as u wish--%>
			<table id="table_wod">
				<tr>
					<th colspan=2>Add a new word<br><br></th>
				<tr>
				<tr>
					<td class="col1"><%out.println("Word");%></td>
					<td class="col2"><input type="text" name="text_word" id="text_word"/></td>
				</tr>
				<tr>
					<td class="col1"><%out.println("Meaning");%></td>
					<td class="col2"><textarea name="text_mean" id="text_mean" rows=4 cols=15></textarea></td>	
				</tr>
			</table>
			<div id="but_wod">
				<input type="Submit" id="sub" value="Submit" /><%out.println("&nbsp &nbsp &nbsp");%>
				<input type="button" value="Reset" onclick="window.location.href='http://localhost:8080/OCM/submitwod.jsp'"/>
	 	 	</div>
		</form>
	</div>

</body>
</html>