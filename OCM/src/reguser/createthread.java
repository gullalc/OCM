package reguser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class createthread
 */
public class createthread extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createthread() {
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
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String title = request.getParameter("thread_title").toString();
		String description = request.getParameter("thread_desc").toString();
		PrintWriter out=response.getWriter();
		
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
			description=description.replaceAll("'", "");
			description="<root>"+description+"</root>";
			String query = "Insert into discussionforum(title,DESC,comments,creater) values ('"+title+"','"+description+"','<root></root>',"+"'"+session.getAttribute("username").toString()+"')";
			
			stmt.executeUpdate(query);
			out.println("<script type='text/javascript'>alert('Thread Created Successfully')</script>");
			//response.sendRedirect("");
		}
		catch(Exception e)
		{
			response.getWriter().println(e);
		}
	}

}
