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
function processChange()
{

if(req.readyState==4&&req.status==200)
{
document.getElementById("head").innerHTML=req.responseText;



}
}
function header()
{

  createXmlHttpRequest();
	
  

  req.open("GET","http://localhost:8080/OCM/Header.jsp");

  req.onreadystatechange=processChange;

  req.send(null);

}
function Accept(a)
{
	alert("This may take time. Please do not reload");
	createXmlHttpRequest();
	req.open("GET","http://localhost:8080/OCM/ProcessModRequests?id="+a);
	req.onreadystatechange=processChange2;
	req.send(null);
}
function Reject(b)
{
	alert("This may take time. Please do not reload");
	createXmlHttpRequest();
	req.open("POST","http://localhost:8080/OCM/ProcessModRequests?id="+a);
	req.onreadystatechange=processChange2;
	req.send(null);
}
function processChange2()
{

if(req.readyState==4&&req.status==200)
{

	{
	
	//window.location.href="http://localhost:8080/OCM/ModRequests";
	document.getElementById("msg").innerHTML=req.responseText;
	}

}
}
function accp(a)
{
	alert("This may take time. Please do not reload");
	createXmlHttpRequest();
	req.open("GET","http://localhost:8080/OCM/Articleprocess?id="+a);
	req.onreadystatechange=processChange3;
	req.send(null);
}
function rejp(a)
{
	alert("This may take time. Please do not reload");
	createXmlHttpRequest();
	req.open("REJECT","http://localhost:8080/OCM/Articleprocess?id="+a);
	req.onreadystatechange=processChange3;
	req.send(null);
}
function processChange3()
{

if(req.readyState==4&&req.status==200)
{

	alert(req.responseText);

}
}