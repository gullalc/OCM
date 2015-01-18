package Moderator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reports
 */
public class Reports extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reports() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String id;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		id=request.getParameter("id");
		try
		{
			                 

		Connection con;
		String report="";
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("Select ID,USERNAME, CATEGORY, ARTICLE_ACCEPTED ,ARTICLE_REJECTED from moderator where id="+id);
		while(rs.next())
		{
			report="<root><id>"+rs.getString(1)+"</id><content> Username "+rs.getString(2)+"</content><content> Category "
					+rs.getString(3)+"</content><content> Articles Accepted "+rs.getString(4)+"</content><content> Articles Rejected "+
					rs.getString(5)+"</content></root>";
		}
		
		st.executeUpdate("Update moderator set report = '"+report+ "'where id="+id );
		out.print("Report Successfully generated");
		}
		catch(Exception e)
		{
			out.print(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
