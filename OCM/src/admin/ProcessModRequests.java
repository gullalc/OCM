package admin;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class ProcessModRequests
 */
public class ProcessModRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
      static String id,username,category; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessModRequests() {
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
		id=request.getParameter("id");
		try
		{
		Connection con;
		String profile="";
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select profile from reguser where id="+id);
		while(rs.next())
		{
			profile=rs.getString(1);
		}
		
		DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(profile));
	    Document doc = db.parse(is);
	    NodeList lis=doc.getElementsByTagName("usertype");
	    for(int i=0;i<lis.getLength();i++)
	    {
	    	if(lis.item(i).getNodeType()==1)
	    	{
	    		
	    		lis.item(i).getFirstChild().setNodeValue("Moderator");
	    		
	    	}
	    }
	    
	    doc.normalize();
	    ResultSet rs2=st.executeQuery("select username from login where id="+id);
		while(rs2.next())
		{
			username=rs2.getString(1);
		}
		ResultSet rs3=st.executeQuery("select category from category where category not in(Select category from moderator) FETCH FIRST 1 ROWS ONLY");
		while(rs3.next())
		{
			category=rs3.getString(1);
		}
	    st.executeUpdate("Update login set usertype= 'Moderator' where id="+id);
	    st.executeUpdate("Insert into moderator(Id,username,category) values ("+id+",'"+username+"','"+category+"')");
	    st.executeUpdate("Delete  from promotion where id="+id);
	    TransformerFactory transfac = TransformerFactory.newInstance();
	    Transformer trans = transfac.newTransformer();
	    
	    
	    StringWriter sw = new StringWriter();
	    StreamResult result = new StreamResult(sw);
	    DOMSource source = new DOMSource(doc);
	    trans.transform(source, result);
	    String xmlString = sw.toString();
	    
	    st.executeUpdate("UPDATE reguser SET profile='"+xmlString+"' where id="+id);
	    out.println(username+" is now moderator for category "+category);
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
		Connection con;
		
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
		Statement st=con.createStatement();
		st.executeUpdate("Delete  from promotion where id="+id);
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
