package reguser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String profileid,xml;
	   Connection con;
	   Statement st;
	   ResultSet rs;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
			profileid=request.getParameter("id").toString();
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			st=con.createStatement();
			rs=st.executeQuery("Select profile from reguser where id="+profileid);
			if(rs.next())
			{
				xml=rs.getString(1);
			}
			String fileName=getServletContext().getRealPath("xslt/Profile.xsl");
			File a=new File(fileName);
			StreamSource xmlfile = new StreamSource(new StringReader(xml));
			Source xsltSource=new StreamSource(a);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(xsltSource);
			HttpSession session=request.getSession(true);
			if(session.getAttribute("id")==null)
			{
				transformer.setParameter("acces", "public");
			}
			else
			{
				if(session.getAttribute("id").equals(profileid))
				{
					transformer.setParameter("acces", "restricted");
				}
				else
				{
					transformer.setParameter("acces", "private");
				}
			}
			transformer.setParameter("userid", profileid);
			transformer.transform(xmlfile, new StreamResult(out));
		}
		catch(Exception e)
		{
		out.println("Sorry the profile no longer exists....Press on the back button on your browser to return");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
