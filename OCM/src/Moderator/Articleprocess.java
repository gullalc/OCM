package Moderator;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class Articleprocess
 */
public class Articleprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Articleprocess() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    Statement st;
    static String keywords,indexes;
    String cats[];
    int max;
    ResultSet r;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			ResultSet rs=st.executeQuery("Select keywords from article where id="+request.getParameter("id"));
			while(rs.next())
			{
				keywords=rs.getString(1);
			}
			cats=keywords.split(",");
			for(int i=0;i<cats.length;i++)
			{
				r=st.executeQuery("Select indexes from keyword where keyword like '"+cats[i]+"'");
				if(r.next())
				{
					indexes=r.getString(1);
					DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
				    DocumentBuilder db = dbf.newDocumentBuilder();
				    InputSource is = new InputSource();
				    is.setCharacterStream(new StringReader(indexes));
				    Document doc = db.parse(is);
				    Element droot=doc.getDocumentElement();
				    Element root=doc.createElement("id");
				    
				    Text text = doc.createTextNode(request.getParameter("id"));
				    root.appendChild(text);
				    droot.appendChild(root);
				    TransformerFactory transfac = TransformerFactory.newInstance();
				    Transformer trans = transfac.newTransformer();
				    
				    
				    StringWriter sw = new StringWriter();
				    StreamResult result = new StreamResult(sw);
				    DOMSource source = new DOMSource(doc);
				    trans.transform(source, result);
				    String xmlString = sw.toString();
				    st.executeUpdate("Insert into keyword values('"+cats[i]+"','"+xmlString+"')");
					st.executeUpdate("update article set status=1 where id="+request.getParameter("id"));
					if(session.getAttribute("usertype").equals("Moderator"))
					{
						r=st.executeQuery("Select max(article_accepted) from moderator");
						if(r.next())
						{
							max=Integer.parseInt(r.getString(1));
							max=max+1;
							st.executeUpdate("update moderator set article_accepted="+max+" where id="+request.getParameter("id"));
						}
					}	
				 
				}
				else
				{
				st.executeUpdate("Insert into keyword values('"+cats[i]+"','"+"<root><id>"+request.getParameter("id")+"</id></root>')");
					st.executeUpdate("update article set status=1 where id="+request.getParameter("id"));
					if(session.getAttribute("usertype").equals("Moderator"))
					{
						r=st.executeQuery("Select max(article_accepted) from moderator");
						if(r.next())
						{
							max=Integer.parseInt(r.getString(1));
							max=max+1;
							st.executeUpdate("update moderator set article_accepted="+max+" where id="+request.getParameter("id"));
						}
						
					}
					out.println("Article Successfully accepted");
				}
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
		PrintWriter out=response.getWriter();
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
