var req=null;
var des=null;
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
document.getElementById("tab_content").innerHTML=req.responseText;

alert('hi');

}
}
function tab()
{

  createXmlHttpRequest();
	
  

  req.open("GET","http://localhost:8080/Sample/uploadPhoto.jsp");

  req.onreadystatechange=processChange;

  req.send(null);

}
function autoresize(txtbox)
{
    var cnt=txtbox.value;
    var text = txtbox.value.replace(/\s+$/g,"")
    var split = text.split("\n");
    var linecount= split.length;
    if(Number(cnt.length)-110>0 || linecount-txtbox.rows>0)
    	{
    	txtbox.rows=txtbox.rows+1;
    	alert(txtbox.rows);
    	}
    
}
