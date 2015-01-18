package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tod
 */
public class tod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tod() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String thot=request.getParameter("text_thought").toString();
		 response.setContentType("text/html");
	 	 	String author=request.getParameter("text_author").toString();
	 	 	PrintWriter out=response.getWriter();
	 	 	out.println("<html>");
			out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
			out.println("<script type='text/javascript' src='header.js'></script>");
			out.println("<body onload='header()'>");
			out.println("<div id='head'></div>");
			out.println("<div>");
	 	 	if(thot != null && author != null)
	 	 	{
	 	 	try
	 	 	{
	 	 		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
				
				Statement stmt =  con.createStatement();
				
				ResultSet rs=stmt.executeQuery("Select * from tod where thought like '"+thot+"'");
				if(rs.next())
				{
					out.println("The Thought is already in the database");
				}
				else
				{
				int i=stmt.executeUpdate("Insert into tod (thought,author) values('"+thot+"','"+author+"')");
				if(i>0)
				{
					out.println("Submitted");
				}
				else
				{
					out.println("Not Submitted");
				}
				}
	 	 	}
	 	 	catch(Exception e)
	 	 	{
	 	 		response.getWriter().println(e);
	 	 	}
	 	 	}
	}

}
