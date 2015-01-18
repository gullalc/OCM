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
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try
		{
			                 

		Connection con;
		
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
		Statement st=con.createStatement();
		String id=request.getParameter("id");
		st.executeUpdate("Delete from login where id="+id);
		st.executeUpdate("Delete from reguser where id="+id);
		ResultSet rs=st.executeQuery("select id from moderator where id="+id);
		while(rs.next())
		{
			st.executeUpdate("Delete from moderator where id="+id);
		}
		
		
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try
		{
			                 

		Connection con;
		
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
		Statement st=con.createStatement();
		out.println("<table>");
		ResultSet rs=st.executeQuery("select id, username from login");
		while(rs.next())
		{
			out.println("<tr><td>"+rs.getString(2)+"</td><td><a href='http://localhost:8080/OCM/DeleteUser?id="+rs.getString(1)+"'>Delete User</a>");
		}
		out.println("</table>");
		
		}
		catch(Exception e)
		{
			out.print(e);
		}
	}

}
