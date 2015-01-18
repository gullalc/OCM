/**
 * 
 */
function a(date)
{
	var year=Number(document.getElementById("year").innerHTML);
  	var month=Number(document.getElementById("mv").innerHTML);
  	
  	alert("hi");
document.getElementById("dob").value=year+"-"+month+"-"+date;
}
function prey()
{
var year=document.getElementById("year").innerHTML;
year=Number(year)-1;
document.getElementById("year").innerHTML=year;
update();
}

function nexty()
{
var year=document.getElementById("year").innerHTML;
year=Number(year)+1;
document.getElementById("year").innerHTML=year;
update();
}
var months=new Array("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
function nextm()
{
	var month=Number(document.getElementById("mv").innerHTML);
	if(month<12)
	{
			document.getElementById("month").innerHTML=months[month];
			month=month+1;
			document.getElementById("mv").innerHTML=month;
			update();
	}
	else
	{
		document.getElementById("month").innerHTML=months[0];
		month=1;
		document.getElementById("mv").innerHTML=month;
		update();
	}
	
}
function prem()
{
	var month=Number(document.getElementById("mv").innerHTML);
	if(month>1)
	{
			month=month-1;
			document.getElementById("month").innerHTML=months[month-1];
			
			document.getElementById("mv").innerHTML=month;
			update();
	}
	else
	{
		document.getElementById("month").innerHTML=months[11];
		month=12;
		document.getElementById("mv").innerHTML=month;
		update();
	}
	
}
var xmlHttp;
function createXmlHttpRequest()
{
       if(window.ActiveXObject)
       {
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      }

    else if(window.XMLHttpRequest)
    {
        xmlHttp=new XMLHttpRequest();
        
     }

}
function update()
{
	
	createXmlHttpRequest();
  var year=Number(document.getElementById("year").innerHTML);
  var month=Number(document.getElementById("mv").innerHTML);
 
  xmlHttp.open("GET","http://localhost:8080/buzzz/Calender?year="+year +"&month="+month,true);
  
  xmlHttp.onreadystatechange=change;
  
  xmlHttp.send(null);
}
function change()
{

   try{
       
            document.getElementById("addr").innerHTML=xmlHttp.responseText;
      }
      catch(err)
        {
       
        }
}
