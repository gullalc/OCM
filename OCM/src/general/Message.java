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
 * Servlet implementation class Message
 */
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    Statement st,st2;
    ResultSet rs,rs2;
    String uname,message,sender,receiver;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		try{
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
		st=con.createStatement();
		uname=request.getParameter("uname");
		message=request.getParameter("message");
		sender=session.getAttribute("username").toString();
		rs=st.executeQuery("Select username from Login where username='"+uname+"'");
		if(rs.next())
		{
			receiver=rs.getString(1);
			int i=st.executeUpdate("Insert into messages (sender,RECEIPIENT,message) values ('"+sender+"','"+receiver+"','"+message+"')");
			
			if(i>0)
			{
				out.println("Message sent successfully");
			}
		}
		else
		{
			out.println("Invalid username");
		}
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
