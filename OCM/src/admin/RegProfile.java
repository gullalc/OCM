package admin;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.sql.*;

/**
 * Servlet implementation class for Servlet: RegProfile
 *
 */
 public class RegProfile extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   String profileid,xml;
   Connection con;
   Statement st;
   ResultSet rs;
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RegProfile() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		try
		{
			profileid=request.getParameter("rid").toString();
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			st=con.createStatement();
			rs=st.executeQuery("Select info from registeration where rid="+profileid);
			if(rs.next())
			{
				xml=rs.getString(1);
			}
			Source xmlSource=new StreamSource(new StringReader(xml));
			File xmls=new File(getServletContext().getRealPath("xslt/Register.xsl"));
			Source xsltSource=new StreamSource(xmls);
			TransformerFactory transFact=TransformerFactory.newInstance();
			Transformer trans =transFact.newTransformer(xsltSource);
			trans.setParameter("rid", profileid);
			trans.transform(xmlSource, new StreamResult(out));
			
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}