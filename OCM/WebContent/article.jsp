<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/editor.js"></script>
<script type="text/javascript" src="Validation.js">

</script>

<title>Post Article</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	<div id="article">
		<form enctype="multipart/form-data" id="articl" action="http://localhost:8080/OCM/PostArticle" method="post">
			<table id="arttab">
				<tr>
					<th colspan=4>Article Form<br>
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
						<%out.println("Content"); %>
					</td>
					<td><script type="text/javascript">
						    var editor1 = new WYSIWYG_Editor('editor1');
						    editor1.display();
						</script></td>
					<td class="col3"><span id="art_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Photo"); %>
					</td>
					<td class="col2"><input type="file" name="foto" id="foto" accept="image/*" /></td>
					<td class="col3"><span id="pht"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Category"); %>
					</td>
					<td><div id="append"></div></td>
					<td class="col3"><span id="cat_error"></span></td>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("keywords"); %>
					</td>
					<td class="col2"><input type="text" name="keyw" id="keyw" /></td>
					<td class="col3"><span id="keyw_error"></span></td>
				</tr>
			</table>
			<div id="but3">
				<input type="Submit" id="formsubmit"/>
				<input type="button" id="sub" value="Submit" onclick="display()" />
				
			</div>
				<input type="hidden" id="hiddencont" name="hiddencont"></input>
			 <input type="hidden" id="hiddencat" name="hiddencat"></input>
		</form>
		<h3>Important Notice: Please type your article manually or 'PASTE AS PLAIN TEXT' or else sometimes the article may not be uploaded</h3>
	</div>
	<script type="text/javascript">
							function loadXMLDoc(dname)
							{
							if (window.XMLHttpRequest)
							  {
							  xhttp=new XMLHttpRequest();
							  }
							else
							  {
							  xhttp=new ActiveXObject("Microsoft.XMLHTTP");
							  }
							xhttp.open("GET",dname,false);
							xhttp.send();
							return xhttp.responseXML;
							}
							xmlDoc=loadXMLDoc("http://localhost:8080/OCM/xml/mainpage.xml");
							
							x=xmlDoc.getElementsByTagName('category');
							for (i=0;i<x.length;i++)
							{
								
								var cb = document.createElement( "input" );
						        cb.type = "checkbox";
						        cb.name = "categories";
						        cb.value = x[i].childNodes[0].nodeValue;
						        cb.label=x[i].childNodes[0].nodeValue;
						        cb.checked = false;
						        var text = document.createTextNode( x[i].childNodes[0].nodeValue );
						        cb.width="50px";
						        document.getElementById( 'append' ).appendChild( text );
						        document.getElementById( 'append' ).appendChild( cb );
							}
							function display()
							{
								if(document.getElementById('title').value.length<1)
								{
								document.getElementById('title_error').innerHTML="Title cannot be empty";
								}
							else
								{
								document.getElementById('title_error').innerHTML="";
										
								}
								
								var checked="";
								
								var a=document.getElementsByName("categories");
								for(var i=0;i<a.length;i++)
									{
									if(a[i].checked)
									{
									checked=checked+a[i].value+"_";
									
									document.getElementById('hiddencat').value=checked;
									
									}
									}
								if(checked.length<1)
									{
									document.getElementById( 'cat_error' ).innerHTML="You must check one or more categories";
									}
								else
									{
									document.getElementById( 'cat_error' ).innerHTML="";
									document.getElementById('hiddencat').value=checked;
											if(document.getElementById('keyw').value.length<1)
											{
											document.getElementById('keyw_error').innerHTML="You must specify a keyword";
											}
										else
											{
											document.getElementById('keyw_error').innerHTML="";
											if(document.getElementById('foto').value.length<1)
												{
												document.getElementById('pht').innerHTML="Photo is Mandatory";
												}
											replace();
											}
									
									}
								
									
							}
							
				</script>
	
</body>
</html>