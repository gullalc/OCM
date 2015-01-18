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
 * Servlet implementation class ModRequests
 */
public class ModRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModRequests() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    Statement st,st1;
    ResultSet rs,rs1;
    static String id;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><title>Moderator requests</title><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
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
			out.println("<tr><td>Request number</td><td>Name</td><td></td><td></td></tr>");
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			st1=con.createStatement();
			rs=st.executeQuery("Select id from promotion");
			while(rs.next())
			{
				id=rs.getString(1);
				rs1=st1.executeQuery("Select username from login where id="+id);
				if(rs1.next())
				{
				out.println("<tr>");
				out.println("<td>"+id+"</td>");
				out.println("<td>");
				out.println("<a href='http://localhost:8080/OCM/Profile?id="+id+"' target='_blank'>"+rs1.getString(1)+"</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("<input type='button' value='Accept' onclick='Accept("+id+")'/>");
				out.println("</td>");
				out.println("<td>");
				out.println("<input type='button' value='Reject' onclick='Reject("+id+")'/>");
				out.println("</td>");
				out.println("</tr>");
				}
				
			}
			
			out.println("</table>");
			out.println("</div>");
			out.println("<span id='msg'></span>");
			out.print("</center>");
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
