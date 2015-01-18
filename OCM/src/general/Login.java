package general;

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
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    Statement st;
    ResultSet rs,rs2;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
				response.setContentType("text/xml");
				PrintWriter out=response.getWriter();
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
				st=con.createStatement();
				String username=request.getParameter("username");
				String password=request.getParameter("password");
				rs=st.executeQuery("Select * from Login where username='"+username+"' and password='"+password+"'");
				out.println("<root>");    		
				if(rs.next())
				{
					HttpSession session=request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("id", rs.getString(1));
					session.setAttribute("usertype", rs.getString(4));
				}
				else
				{
					out.println("<error>false</error>");
				}
				out.println("</root>");
				
				
		}
		catch(Exception e)
		{
			PrintWriter out=response.getWriter();
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
