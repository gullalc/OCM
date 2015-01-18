package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class storenews
 */
public class storenews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public storenews() {
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
		String title = request.getParameter("news_title");
		String content = request.getParameter("news_content");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
			
			String query = "Insert into news(title,news) values('"+title+"','"+content+"')";
			int i= stmt.executeUpdate(query);
			if(i>0)
			{
				out.println("News submitted successfully.");
			}
			else
			{
				out.println("There seems to be a problem, Please try again later");
			}
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
