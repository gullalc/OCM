<%@ page
	import="javax.xml.transform.*, javax.xml.transform.stream.*, java.io.*;"%>
<%
try
{
	
	String fileName=getServletContext().getRealPath("xslt/Header.xsl");
	File a=new File(fileName);
	
	StreamSource xmlfile = new StreamSource(new File(getServletContext().getRealPath("xml/mainpage.xml")));
	Source xsltSource=new StreamSource(a);
TransformerFactory tFactory = TransformerFactory.newInstance();
Transformer transformer = tFactory.newTransformer(xsltSource);
transformer.setParameter("user", session.getAttribute("username"));

transformer.transform(xmlfile, new StreamResult(out));
}
catch(Exception e)
{
out.println(e);
}
%>
