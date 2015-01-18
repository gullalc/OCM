package general;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: Img
 *
 */
 public class Img extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Img() {
		super();
	}   	
	Connection con;
	   ResultSet rs;
	   Statement st;
	   Blob blob=null;
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			
		if(request.getParameter("img")==null||request.getParameter("img")=="")
		{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<img src='style/images/Default.jpg'></img>");
		}
		
		else
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			rs = st.executeQuery("select img from img where id='"+request.getParameter("img").toString()+"'");
			if(rs.next())
			{
				blob=rs.getBlob(1);
				response.setContentType("image/jpg");
		        OutputStream out = response.getOutputStream();
		        byte bytes[]=blob.getBytes(1, (int)blob.length()+1);
			    out.write(bytes,0,(int)blob.length()+1);
			    out.flush();
			    out.close();
			}
		}
		}
		catch(Exception e)
		{
			
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}