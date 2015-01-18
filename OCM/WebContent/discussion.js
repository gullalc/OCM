function discussion()
{
		var title = document.getElementById("thread_title").value;
		var desc = document.getElementById("thread_desc").value;
	
		if(title=="")
		{
			document.getElementById("title_error").innerHTML="Title is mandatory";
		}
		if(desc=="")
		{
			document.getElementById("desc_error").innerHTML="Description is mandatory";
		}
	
}