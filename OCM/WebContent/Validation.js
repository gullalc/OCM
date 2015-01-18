var req=null;
var cnt=0;

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
						
						
					
					if(req.responseXML!=null)
						{
							var message =  req.responseXML.getElementsByTagName("error");
							var formname=req.responseXML.getElementsByTagName("servlet_name");
							
							var length=message.length;
							
							if(length>0)
								{
											for(i=0;i<length;i++)
											{
											document.getElementById(req.responseXML.getElementsByTagName("error")[i].childNodes[0].nodeValue).innerHTML="This field is mandatory";
											}
								}
							else
								{
								var _xml=req.responseXML;
								var xml=""
								var doc=_xml.documentElement;
								var names=doc.getElementsByTagName('*');
								var len=names.length;
								if(len>0)
									{
										for(var i=0;i<len;i++)
										{	
												{
													if(names[i].tagName)
													{
													xml=xml+"<"+names[i].tagName+">"+"     "+names[i].childNodes[0].nodeValue+"</"+names[i].tagName+">";
													}
												}
										}
									}
								call_servlet(formname[0].childNodes[0].nodeValue,xml);
								}
						}
						
					
					
					}			
						
}


function tab(a)
{

  createXmlHttpRequest();
  var params="";
  var day=document.getElementById("day").value;
  var month=document.getElementById("month").value;
  var year=document.getElementById("year").value;
  var date=day+"/"+month+"/"+year;
  var elem = document.getElementById(a).elements;
  
  for(var i = 0; i < elem.length-2; i++)
	  
  {
	  if(elem[i].name=="day"||elem[i].name=="month"||elem[i].name=="year")
	  {
		  
	  }
	  else
	  {
		  params=params+elem[i].name+'='+elem[i].value+'&';
	  }
  } 
 
  params=params+"servlet_name="+a;

  req.open("GET","http://localhost:8080/OCM/Validate?"+params);

  req.onreadystatechange=processChange;

  req.send(null);

}


function expr()
{	
	if(document.getElementById("occ").value=="Alumni")
	{	
		document.getElementById("Exp").style.display="table-row";
	}
	else if(document.getElementById("occ").value=="Faculty")
	{	
		document.getElementById("Exp").style.display="table-row";
	}
	else
	{
		document.getElementById("Exp").style.display="none";
	}
}

function passChar()
{
	document.getElementById("pass_char").style.display="table-row";
}

function dateClick()
{
	
	for(var i=1;i<=31;i++)
	{
		var e1=document.getElementById("day");
        var o1=document.createElement('option');
		o1.value=i;
		o1.text=i;
		e1.options.add(o1);
	}
	
	for(var j=0;j<=11;j++)
	{
		var month = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		var e2=document.getElementById("month");
        var o2=document.createElement('option');
		o2.value=j;
		o2.text=month[j];
		e2.options.add(o2);
	}
	
	for(var k=1920;k<=2000;k++)
	{
		var e3=document.getElementById("year");
        var o3=document.createElement('option');
		o3.value=k;
		o3.text=k;
		e3.options.add(o3);
	}
	
}

  
function countContent()
{
	 var cnt = "";
     var string = document.getElementById("art").value;
     var len = string.length;
     if(len >= cnt) 
     {
          document.getElementById("txtlen").value=cnt+len;
          document.getElementById("art").value=string.substr(0, 140);
     } 

}	

function addcat()
{	
	var cat=document.getElementById("cat").value;
	if(cat.length<1)
		document.getElementById("empty").innerHTML="Category name cannot be empty";
	else
		{
		createXmlHttpRequest();
		req.open("GET","http://localhost:8080/OCM/AddCategory?cat="+cat);
		req.onreadystatechange=addcat_response;
		req.send(null);
		}
}
function addcat_response()
{
	if(req.readyState==4&&req.status==200)
	document.write("<h3>"+req.responseText+"</h3>");
}
function replace()
{
var iframe = document.getElementsByTagName('iframe')[1];

	var doc = iframe.contentWindow.document;
	var a=doc.body.innerHTML;
	a=a.replace(/&nbsp/gi," ");
	a=a.replace(/<br>/gi,"<br></br>");

	document.getElementById("hiddencont").value=a;
	if(a.length>100)
	{
		document.getElementById("sub").value="Save";
		document.getElementById("formsubmit").style.display="inline-block";
	}
	else
		{
		document.getElementById("art_error").innerHTML=("<h3>"+"Minimum 100 characters"+"</h3>");
		}
}
function Regex_response()
{
	if(req.readyState==4&&req.status==200)
	{	if(req.responseText)
			{
			document.getElementById("art_error").innerHTML=("<h3>"+req.responseText+"</h3>");
			}
		else
		{	
		
		}
	}
}
function call_servlet(a,b)
{
	createXmlHttpRequest();
	switch (a)
	{
	case "reg": 
	url="http://localhost:8080/OCM/Registeration";
	break;
	
	default : alert("Invalid choice");
	}
	
	req.open("POST",url+"?res="+b);
	
	 req.setRequestHeader("Content-Type", "text/xml");
	  req.onreadystatechange=callServlet;

	  req.send(null);
}
function callServlet()
{
	
	if(req.readyState==4&&req.status==200)
	{
		document.write(req.responseText);
	}
	else
		{
		
		}
}

function check_submit()
{
	if(document.getElementById("title").value.length<1)
		{
		document.getElementById("title_error").innerHTML="Mandatory form";
		}
	else if(document.getElementById("foto").value.length<1)
		{
		document.getElementById("foto_error").innerHTML="Mandatory form";
		document.getElementById("title_error").innerHTML="";
		}
	else if(document.getElementById("descrip").value.length<1)
		{
		document.getElementById("descrip_error").innerHTML="Mandatory form";
		document.getElementById("foto_error").innerHTML="";
		}
	else
		{
		document.getElementById("sub").value="Save";
		document.getElementById("formsubmit").style.display="inline-block";
		}
		
}
function countContent()
{
	 var cnt = "";
     var string = document.getElementById("art").value;
     var len = string.length;
     if(len >= cnt) 
     {
          document.getElementById("txtlen").value=cnt+len;
          document.getElementById("art").value=string.substr(0, 140);
     } 
}	

function passLen()
{
	var minlen = 6;
	var maxlen = 12;
	len = document.getElementById("pass").value.length;
	if(len<6 || len>12)
	{
		document.getElementById("pass_error").innerHTML="Password is not between 6 to 12 characters";		
	}
	else
	{
		document.getElementById("pass_error").innerHTML="";	
	}
}

function msgCount()
{
	 var cnt = 140;
     var string = document.getElementById("message").value;
     var len = string.length;
     if(len <= cnt) 
     {
          document.getElementById("msglen").value=cnt-len;
     }
     else
     {
     	  document.getElementById("message").value=string.substr(0, 140);
     }
}	

function subMessage()
{
		createXmlHttpRequest();
		var uname = document.getElementById("uname").value;
		var message = document.getElementById("message").value;
		
		req.open("GET","http://localhost:8080/OCM/Message?uname="+uname+"&message="+message);
		
		req.send(null);
		req.onreadystatechange=handleMessage;
}

function handleMessage()
{
	if(req.readyState==4&&req.status==200)
	{
			var resp=req.responseText;
			alert(resp);
			window.location.href="/OCM";
	}
}
function Forgot()
{
	createXmlHttpRequest();
	var uname = document.getElementById("email").value;
	var message = document.getElementById("code").value.toUpperCase();
	
	req.open("GET","http://localhost:8080/OCM/Forgot?email="+uname+"&code="+message);
	
	req.send(null);
	req.onreadystatechange=forgot;
}
function forgot()
{
	if(req.readyState==4&&req.status==200)
	{
		var captcha=req.responseXML.getElementsByTagName("captcha").length;
		var email=req.responseXML.getElementsByTagName("email").length;
		var emai=req.responseXML.getElementsByTagName("e").length;
		var password=req.responseXML.getElementsByTagName("password").length;
			if(captcha>0)
				{
				alert(req.responseXML.getElementsByTagName("captcha").item(0).childNodes[0].nodeValue);
				window.location.reload();
				}
			
			
			else if(email>0)
				{
				alert(req.responseXML.getElementsByTagName("email").item(0).childNodes[0].nodeValue);
				window.location.reload();
				}
			else if(emai>0&&password>0)
				{
				
				window.open("http://localhost:8080/OCM/SendMail.jsp?email='"+emai+"'&subj='"+"Your OCM password has been reset to"+password+"'",_blank);
				}
			
			
	}
}