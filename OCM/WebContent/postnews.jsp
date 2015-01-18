<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#div_news{width: 800px;}
.col1{width: 200px;}
.col2{width: 400px;}
.col3{width: 200px;}
textarea{resize: none;
		 text-size: 14px;
		 width: 400px;}
.col2 input{width: 400px;}
#but_postnews{text-align:center;}
</style>
<title>Post College News</title>
</head>
<body>
	<div id="div_news">
			<form action="http://localhost:8080/OCM/storenews" method="POST">
				<table id="msgtab">
					<tr>
						<th colspan=3>Post College News<br><br></th></tr>
					<tr>
						<td class="col1"><%out.println("Title"); %></td>
						<td class="col2"><input type="text" name="news_title" id="news_title"/></td> 	
						<td class="col3"><span id="news_error"></span></td>
					</tr>
					<tr>
						<td class="col1"><%out.println("News Content"); %></td>
						<td class="col2"><textarea name="news_content" id="news_content" rows=5 cols=20></textarea></td> 	
						<td class="col3"><span id="content_error"></span></td>
					</tr>
				</table>
				<div id="but_postnews">
				<input type="Submit" id="sub" value="Submit"/>
				<input type="button" value="Reset" onclick="window.location.href='http://localhost:8080/OCM/postnews.jsp'"/>
				</div>
			</form>
		</div>
</body>
</html>