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
function tab(name)
{	
	createXmlHttpRequest();
	var a=document.getElementById("uid").value;
	switch(name)
	{
	case 'info':
		
		req.open("GET","http://localhost:8080/OCM/Info?id="+a);
		
		req.onreadystatechange=info;
		req.send(null);
		break;
	case 'articles':
		req.open("GET","http://localhost:8080/OCM/ViewUserArticle?id="+a);
		
		req.onreadystatechange=write;
		req.send(null);
		break;
	case 'photos':
		req.open("GET","http://localhost:8080/OCM/ViewUserPhotos?id="+a);
		
		req.onreadystatechange=write;
		req.send(null);
		break;
	case 'new_articles':
		window.location.href="http://localhost:8080/OCM/article.jsp";
		break;
	case 'new_photos':
		window.location.href="http://localhost:8080/OCM/photo.jsp";
		break;
	case 'contact_others':
		window.location.href="http://localhost:8080/OCM/sendmsg.jsp";
		break;
	case 'update_status':
		window.location.href="http://localhost:8080/OCM/status.jsp";
		break;
	case 'update_dp':
		window.location.href="http://localhost:8080/OCM/updatephoto.jsp";
		break;
	case 'regreq':
		window.location.href="http://localhost:8080/OCM/RegRequests";
		break;
	case 'promote':
		req.open("GET","http://localhost:8080/OCM/PromotionRequest");
		
		req.onreadystatechange=promote;
		req.send(null);
		break;
	case 'inbox':
		req.open("GET","http://localhost:8080/OCM/Inbox?id="+a);
		
		req.onreadystatechange=write;
		req.send(null);
		break;
	case 'postreq':
		window.location.href="http://localhost:8080/OCM/PostRequests";
	case 'genrep':
			req.open("GET","http://localhost:8080/OCM/Reports?id="+a);
			
			req.onreadystatechange=write;
			req.send(null);
			break;
	case 'viewrep':
		req.open("POST","http://localhost:8080/OCM/ViewReport?id="+a);
		
		req.onreadystatechange=write;
		req.send(null);
		break;
	case 'tod':
		req.open("GET","http://localhost:8080/OCM/submittod.jsp");
		
		req.onreadystatechange=write;
		req.send(null);
		break;
	case 'wod':
			req.open("GET","http://localhost:8080/OCM/submitwod.jsp");
			
			req.onreadystatechange=write;
			req.send(null);
			break;
			
			
			
			
		case 'complaints':
				req.open("GET","http://localhost:8080/OCM/ViewComplaint");
				
				req.onreadystatechange=write;
				req.send(null);
				break;
		case 'delusr':
					req.open("POST","http://localhost:8080/OCM/DeleteUser");
					
					req.onreadystatechange=write;
					req.send(null);
					break;
		case 'postnews':
						req.open("GET","http://localhost:8080/OCM/postnews.jsp");
						
						req.onreadystatechange=write;
						req.send(null);
						break;
		case 'ep':
							req.open("POST","http://localhost:8080/OCM/EditorPick");
							
							req.onreadystatechange=write;
							req.send(null);
							break;
	}
}
function info()
{
	if(req.readyState==4&&req.status==200)
	{
			var response=req.responseXML.getElementsByTagName("root")[0].childNodes;
			var divcontent="<div id='info'><center><h2>User Information</h2><table width='100%' border='1px dashed #666'>";
			for(var i=0;i<response.length;i++)
				{
					if(response[i].nodeType==1)
						if(response[i].textContent)
							if(response[i].textContent!='empty')
								divcontent=divcontent+"<tr><td>"+response[i].nodeName.replace("_"," ").replace("_"," ").toUpperCase()+" </td><td> "+response[i].textContent+"</td></tr>";
				}
			divcontent=divcontent+"</table></center></div>";
			document.getElementById("left_area").innerHTML=divcontent;
	}
}
function write()
{
	if(req.readyState==4&&req.status==200)
	{
		var response=req.responseText;
		document.getElementById("left_area").innerHTML=response;
	}
}
function Like(post,id)
{
	createXmlHttpRequest();
	req.open("GET","http://localhost:8080/OCM/Like?post='"+post+"'&id='"+id+"'");
	alert("http://localhost:8080/OCM/Like?post="+post+"&id="+id+"");
	req.onreadystatechange=aler;
	req.send(null);
	
}
function aler()
{
	if(req.readyState==4&&req.status==200)
	{
		/*var response=req.responseText;
		alert(response);*/
		window.location.reload();
	}
}
function promote()
{
	if(req.readyState==4&&req.status==200)
	{
		var response=req.responseText;
		document.getElementById("pr").innerHTML=response;
	}
}
function Comment(obj) {

	var el = document.getElementById(obj);

	el.style.display = (el.style.display != 'none' ? 'none' : '' );

	}
function comm(post,id)
{
	var cont=document.getElementById("cont").value
	createXmlHttpRequest();
	req.open("GET","http://localhost:8080/OCM/Comment?post='"+post+"'&id='"+id+"'&cont="+cont);
	
	req.onreadystatechange=aler;
	req.send(null);
}