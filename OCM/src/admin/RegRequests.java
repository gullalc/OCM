package admin;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.xml.xpath.*;
import org.xml.sax.*;
/**
 * A servlet where admin will see the registeration requests
 *
 */
 public class RegRequests extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   Connection con;
   ResultSet rs;
   Statement st;
   String profile;
   FileWriter file;
   DocumentBuilderFactory dFactory;
   Document doc;
   Element root;
   DocumentBuilder docBuilder;
   XPathFactory xPathFactory;
   XPath xPath;
   String expression;
   XPathExpression xPathExpression;
   Object result;
	public RegRequests() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		try
		{
			
			response.setContentType("text/html");
			out.println("<center>");
			out.println("<div id='regtab'>");
			out.println("<table>");
			out.println("<br/><br/>");
			out.println("<tr><td>Registeration number</td><td>Name</td><td>Profile</td></tr>");
			dFactory = DocumentBuilderFactory.newInstance();
			dFactory.setNamespaceAware(true);
			dFactory.setValidating( true );
    		docBuilder = dFactory.newDocumentBuilder();
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			rs=st.executeQuery("Select rid,info from REGISTERATION where status=0");
			while(rs.next())
			{
				out.println("<tr><td>"+rs.getString(1)+"</td><td>");
				profile=rs.getString(2);
				doc = docBuilder.parse(new InputSource(new StringReader(profile)));
				root = doc.getDocumentElement();
				XPathFactory xPathFactory = XPathFactory.newInstance();
				
				XPath xPath = xPathFactory.newXPath();
				
				String expression = "//fname/text()";
				XPathExpression xPathExpression = xPath.compile(expression);
				
				Object result = xPathExpression.evaluate(doc,XPathConstants.NODESET);
				
				NodeList nodes = (NodeList) result;
				for (int i = 0; i < nodes.getLength(); i++) {
				    out.println(nodes.item(i).getNodeValue()+"</td><td><form action='http://localhost:8080/OCM/RegProfile'><input type='hidden' name='rid' value="+rs.getString(1)+" /><input type='submit' value='View Profile'/></form></td></tr>"); 
				}
				
			}
			
			out.println("</table>");
			out.println("</div>");
			out.print("</center>");
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}   	  	    
}