package reguser;

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
 * Servlet implementation class Recent_Activity
 */
public class Recent_Activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	   Statement st;
	   ResultSet rs; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recent_Activity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			out.println("<div id='info'><center><h2>Recent Activity</h2><ul>");
			String userid=request.getParameter("id").toString();
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			st=con.createStatement();
			rs=st.executeQuery("select id,post_type from posts where userid ="+userid+" and status =1 order by post_date");
			if(rs.next())
			{
				
			}
			else
			{
				out.println("The user did not upload any articles");
			}
			out.println("</ul></center></div>");
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
