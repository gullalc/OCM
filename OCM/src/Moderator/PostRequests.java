package Moderator;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PostRequests
 */
public class PostRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostRequests() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    Connection con;
    Statement st;
    static String cat;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession(true);
		if(session.getAttribute("usertype")==null)
		{
			response.sendRedirect("/OCM");
		}
		else if(session.getAttribute("usertype").equals("Admin"))
		{
			out.println("<html>");
			out.println("<head><title>Moderator requests</title><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
			out.println("<script type='text/javascript' src='header.js'></script>");
			out.println("<body onload='header()'>");
			out.println("<div id='head'></div>");
			out.println("<div>");
			out.println("<table>");
			out.println("<tr><td>Id</td><td>Title</td><td></td><td></td></tr>");
			try
			{
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
				st=con.createStatement();
				ResultSet rs=st.executeQuery("Select id, title from article where status=0 ");
				while(rs.next())
				{
					out.println("<tr>");
					out.println("<td>"+rs.getString(1)+"</td>");
					out.println("<td><a href='http://localhost:8080/OCM/ViewArticle?id="+rs.getString(1)+"'>"+rs.getString(2)+"</a></td>");
					out.println("<td><button type='button' onclick='accp("+rs.getString(1)+")'>Accept</button></td>");
					out.println("<td><button type='button' onclick='rejp("+rs.getString(1)+")'>Reject</button></td>");
				}
			}
			catch(Exception e)
			{
				
			}
		}
		else if(session.getAttribute("usertype").equals("Moderator"))
		{
			out.println("<html>");
			out.println("<head><title>Moderator requests</title><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
			out.println("<script type='text/javascript' src='header.js'></script>");
			out.println("<body onload='header()'>");
			out.println("<div id='head'></div>");
			out.println("<div>");
			out.println("<table>");
			out.println("<tr><td>Id</td><td>Title</td><td></td><td></td></tr>");
			try
			{
				 cat="";
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
				st=con.createStatement();
				ResultSet rs1=st.executeQuery("select category from moderator where username like '"+session.getAttribute("username")+"'");
				if(rs1.next())
				{
					cat=rs1.getString(1);
				}
				ResultSet rs=st.executeQuery("Select id, title from article where id in(select id from article_category where category like '"+cat+"') and status=0 ");
				while(rs.next())
				{
					out.println("<tr>");
					out.println("<td>"+rs.getString(1)+"</td>");
					out.println("<td><a href='http://localhost:8080/OCM/ViewArticle?id="+rs.getString(1)+"'>"+rs.getString(2)+"</a></td>");
					out.println("<td><button type='button' onclick='accp("+rs.getString(1)+")'>Accept</button></td>");
					out.println("<td><button type='button' onclick='rejp("+rs.getString(1)+")'>Reject</button></td>");
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
