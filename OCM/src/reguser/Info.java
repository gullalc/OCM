package reguser;

import java.io.FileWriter;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class Info
 */
public class Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	   ResultSet rs;
	   Statement st;
	   String profile;
	   FileWriter file;
	   DocumentBuilderFactory dFactory;
	   Document doc;
	   Element root;
	   DocumentBuilder docBuilder;
	   XPathFactory xPathFactory;
	   XPath xPath;
	   String expression;
	   XPathExpression xPathExpression;
	   Object result; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out=response.getWriter();
		try
		{
			
			

			dFactory = DocumentBuilderFactory.newInstance();
			dFactory.setNamespaceAware(true);
			dFactory.setValidating( true );
    		docBuilder = dFactory.newDocumentBuilder();
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			rs=st.executeQuery("Select profile from REGUSER where id="+request.getParameter("id"));
			out.println("<root>");
			if(rs.next())
			{
				
				profile=rs.getString(1);
				doc = docBuilder.parse(new InputSource(new StringReader(profile)));
				root = doc.getDocumentElement();
				XPathFactory xPathFactory = XPathFactory.newInstance();
				// To get an instance of the XPathFactory object itself.
				XPath xPath = xPathFactory.newXPath();
				// Create an instance of XPath from the factory class.
				String expression = "/profile/view/Info/*";
				XPathExpression xPathExpression = xPath.compile(expression);
				// Compile the expression to get a XPathExpression object.
				Object result = xPathExpression.evaluate(doc,XPathConstants.NODESET);
				// Evaluate the expression against the XML Document to get the result.
				NodeList nodes = (NodeList) result;
				for (int i = 0; i < nodes.getLength(); i++) {
				    out.println("<"+nodes.item(i).getNodeName()+">"+nodes.item(i).getTextContent()+"</"+nodes.item(i).getNodeName()+">"); 
				}
				
			}
			else
			{
				
			}
			out.println("</root>");
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
