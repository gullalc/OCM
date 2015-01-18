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
function re_enter(jsp_name)
{
	var win=window.open("http://localhost:8080/OCM/"+jsp_name+".jsp","_self");
}
function confirm(ur)
{
	
	var win=window.open("http://localhost:8080/OCM/"+ur,"_self");
}
function callServlet()
{
	
	if(req.readyState==4&&req.status==200)
	{
		document.write(req.responseText);
	}
}
