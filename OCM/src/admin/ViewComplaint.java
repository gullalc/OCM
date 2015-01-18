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
 * Servlet implementation class ViewComplaint
 */
public class ViewComplaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewComplaint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			PrintWriter out=response.getWriter();
			out.println("<html>");
			out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
			out.println("<script type='text/javascript' src='header.js'></script>");
			out.println("<body onload='header()'>");
			out.println("<div id='head'></div>");
			out.println("<div>");
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
				Statement st1=con.createStatement();
				out.println("<center>");
				out.println("Complaints");
				out.println("<table border=1>");
				out.println("<tr><th>Id</th><th>Subject</th><th>Complaint/Suggestion</th><th>Email/Username</th><th>Date</th></tr>");
				ResultSet rs1 = st1.executeQuery("select * from complaint");
				while(rs1.next())
				{
					out.println("<tr><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td></tr>");
				}
				out.println("</table>");
				
				
		
		}
		catch(Exception e)
		{
			response.getWriter().println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
