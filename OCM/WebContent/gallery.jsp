<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<title>Image Gallery</title>
<link href='style2.css' type='text/css' rel='stylesheet'>
<link href='style/Profile.css' type='text/css' rel='stylesheet'>
<script type='text/javascript' src='jquery-1.7.1.min.js'></script>

<script type="text/javascript">
var lft=8;
$(document).ready(function(){
$("#next").click(function(){
var p = $("#sd");
var position = p.position();
if(lft<3000)
	{
	lft=lft+204;
}
else
{
lft=8;
}
	
    $("#sd").animate({ "right" : lft }, 1000);
    });
$("#prev").click(function(){
	var p = $("#sd");
	var position = p.position();
	if(lft<=8)
	{
	lft=3000;
}
else
{
lft=lft-204;

}
	
	
    $("#sd").animate({ "right" : lft }, 1000);
    });
  });

</script>
<style type="text/css">

</style>
</head>
<body>
<br><br><br>
<div id="cnt">
<button type="button" id="next">Next</button>
<div id="ab">

<div id="sd">

<script type="text/javascript">
var i=1;
for(i=0;i<=7;i++)
{    
var url = "http://localhost:8080/OCM/Preview?id=?"+i;

var img = document.createElement("IMG");
img.src = "http://localhost:8080/OCM/ViewPhoto?id="+i+"&post=photos"
var link = document.createElement('a'); // create the link
  link.href = url; //can be done this way too
  link.appendChild(img); // append to link
 document.getElementById('sd').appendChild(link);
      
}
</script>
</div>

</div>
<button type="button" id="prev">Previous</button>
</div>
</body>
</html>