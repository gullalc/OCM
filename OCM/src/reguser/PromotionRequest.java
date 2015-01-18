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
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class PromotionRequest
 */
public class PromotionRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Connection con;
     Statement st;
     ResultSet rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromotionRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			response.setContentType("text/html");
			HttpSession session=request.getSession();
			PrintWriter out=response.getWriter();
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			String user=session.getAttribute("id").toString();
			ResultSet rs=st.executeQuery("Select id from promotion where id="+user);
			if(rs.next())
			{
				out.println("You are already on the application list");
			}
			else
			{
				st.executeUpdate("Insert into promotion (id) values('"+user+"')");
				out.println("Your application has now been received");
				
			}
			
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
