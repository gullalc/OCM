package general;

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
 * Servlet implementation class Comment
 */
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
		// TODO Auto-generated method stub
		Connection con;
	    Statement st;
	    ResultSet rs;
	    int likes;
	    static String comments="";
	    static String post;
	    static String child;
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession();
			response.setContentType("text/html");
			try
			{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			st=con.createStatement();
			String post=request.getParameter("post");
			post=post.replaceAll("'", "");
			
			rs=st.executeQuery("Select comments from "+post+" where id="+request.getParameter("id")+"");
			
			while(rs.next())
			{
				comments=(rs.getString(1));
				
			}
			likes=likes+1;
			DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(comments));
		    Document doc = db.parse(is);
		    Element droot=doc.getDocumentElement();
		    if(session.getAttribute("username")!=null)
		    {
		    Element root=doc.createElement("comment");
		    Element child = doc.createElement("username");
		    Text text = doc.createTextNode(session.getAttribute("username").toString());
		    child.appendChild(text);
		    Element child2 = doc.createElement("uid");
		    Text text2 = doc.createTextNode(request.getParameter("id").toString());
		    child2.appendChild(text2);
		    Element child3 = doc.createElement("cont");
		    Text text3 = doc.createTextNode(request.getParameter("cont").toString());
		    child3.appendChild(text3);
	        root.appendChild(child);
	        root.appendChild(child2);
	        root.appendChild(child3);
	        droot.appendChild(root);
		    }
		    else if(session.getAttribute("email")!=null)
		    {
		    	Element root=doc.createElement("comment");
		    	Element child = doc.createElement("email");
		        
		        Text text = doc.createTextNode(session.getAttribute("email").toString());
		        child.appendChild(text);
		        Element child3 = doc.createElement("cont");
			    Text text3 = doc.createTextNode(request.getParameter("cont").toString());
			    child3.appendChild(text3);
		        root.appendChild(child);
		        root.appendChild(child3);
		        droot.appendChild(root);
		    }
		    TransformerFactory transfac = TransformerFactory.newInstance();
		    Transformer trans = transfac.newTransformer();
		    
		    
		    StringWriter sw = new StringWriter();
		    StreamResult result = new StreamResult(sw);
		    DOMSource source = new DOMSource(doc);
		    trans.transform(source, result);
		    String xmlString = sw.toString();
		    out.print(xmlString);
			st.executeUpdate("Update "+post+" set comments='"+xmlString+"' where id="+request.getParameter("id")+"");
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
