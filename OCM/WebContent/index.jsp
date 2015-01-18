<%@ page
	import="javax.xml.transform.*, javax.xml.transform.stream.*, java.io.*;"%>
<% 
if(session.getAttribute("username")!=null||session.getAttribute("email")!=null)
{
response.setIntHeader("Refresh", 60);
try
{
	
	String fileName=getServletContext().getRealPath("xslt/main.xsl");
	File a=new File(fileName);
	
	StreamSource xmlfile = new StreamSource(new File(getServletContext().getRealPath("xml/mainpage.xml")));
	Source xsltSource=new StreamSource(a);
TransformerFactory tFactory = TransformerFactory.newInstance();
Transformer transformer = tFactory.newTransformer(xsltSource);
if(session.getAttribute("username")!=null)
	{
	transformer.setParameter("user", session.getAttribute("username"));
	transformer.setParameter("userid", session.getAttribute("id"));
	}
	
else
	transformer.setParameter("user", "Guest");

transformer.transform(xmlfile, new StreamResult(out));
}
catch(Exception e)
{
out.println(e);
}
}
else
{
	response.sendRedirect("http://localhost:8080/OCM/LandingPage.jsp");
}
%>
