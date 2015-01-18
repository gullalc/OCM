function A()
{

var cn=document.getElementById('a').className;
if(cn=="act")
{
document.getElementById('a').className="inact";
document.getElementById('Ba').className="bi";
document.getElementById('b').className="act";
document.getElementById('Bb').className="ba";
}
else
{
document.getElementById('a').className="act";
document.getElementById('Ba').className="ba";
document.getElementById('b').className="inact";
document.getElementById('Bb').className="bi";
document.getElementById('c').className="inact";
document.getElementById('Bc').className="bi";
}
}
function B()
{

var cn=document.getElementById('b').className;
if(cn=="act")
{
document.getElementById('b').className="inact";
document.getElementById('Bb').className="bi";
document.getElementById('c').className="act";
document.getElementById('Bc').className="ba";
}
else
{
document.getElementById('b').className="act";
document.getElementById('Bb').className="ba";
document.getElementById('a').className="inact";
document.getElementById('Ba').className="bi";
document.getElementById('c').className="inact";
document.getElementById('Bc').className="bi";
}
}
function C()
{

var cn=document.getElementById('c').className;
if(cn=="act")
{
document.getElementById('c').className="inact";
document.getElementById('Bc').className="bi";
document.getElementById('a').className="act";
document.getElementById('Ba').className="ba";
}
else
{
document.getElementById('c').className="act";
document.getElementById('Bc').className="ba";
document.getElementById('a').className="inact";
document.getElementById('Ba').className="bi";
document.getElementById('b').className="inact";
document.getElementById('Bb').className="bi";
}
}
var i=0;
var j=0;
var cls=new Array("v","w","x","y","z");
function next()
{

	if(i==4)
	{
	i=0;
	}
	else
	{
	i=i+1;
	}
	var j;
	for(j=0;j<5;j++)
	{
		if(i==j)
		{
		document.getElementById(cls[i]).className="vis";
		}
		else
		{
		document.getElementById(cls[j]).className="hid";
		}
	}
}
function prev()
{

	if(i==0)
	{
	i=4;
	}
	else
	{
	i=i-1;
	}
	var j;
	for(j=0;j<5;j++)
	{
		if(i==j)
		{
		document.getElementById(cls[i]).className="vis";
		}
		else
		{
		document.getElementById(cls[j]).className="hid";
		}
	}
}
setInterval(next,5000);

function Login()
{
	createXmlHttpRequest();
	var username=document.getElementById("username").value;
	var password=document.getElementById("pass").value;
	var params="username="+username+"&password="+password;
	req.open("GET","http://localhost:8080/OCM/Login?"+params);
	req.onreadystatechange=processChange;
	req.send(null);
}
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
					
					var response=req.responseXML.getElementsByTagName("error");
					
					if(response.length>0)
					{
						document.getElementById("error").innerHTML="Invalid username on password";
					}
					else
						{
						window.location.href="http://localhost:8080/OCM/index.jsp";
						}
					}			
						
}
function logout()
{
	createXmlHttpRequest();
	var a=document.getElementById("uid").value;
	req.open("GET","http://localhost:8080/OCM/Info?id="+a);
	
	req.onreadystatechange=processInfo;
	req.send(null);
}
function processInfo()
{
	if(req.readyState==4&&req.status==200)
	{
			var response=req.responseXML.getElementsByTagName("root")[0].childNodes;
			var divcontent="<ol>";
			for(var i=0;i<8;i++)
				{
					if(response[i].nodeType==1&&response[i].textContent!='empty')
					divcontent=divcontent+"<li>"+response[i].nodeName.toUpperCase()+" : "+response[i].textContent+"</li>";
				}
			divcontent=divcontent+"</ol>";
			document.getElementById("logout").innerHTML=document.getElementById("logout").innerHTML+divcontent;
	}
}
function ViewProfile(a)
{
	window.location.href="http://localhost:8080/OCM/Profile?id="+a;
}
function Register()
{
	window.location.href="http://localhost:8080/OCM/Register.jsp";
}
function lg()
{
	window.location.href="http://localhost:8080/OCM/Logout";
}
function ForgetPassword()
{
	window.location.href="http://localhost:8080/OCM/ForgotPassword.jsp";
}