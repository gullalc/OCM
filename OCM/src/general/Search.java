package general;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String qu[];
	static String l;
	static  FileWriter file;
	static   DocumentBuilderFactory dFactory;
	static Document doc;
	static   Element root;
	static   DocumentBuilder docBuilder;
	static   XPathFactory xPathFactory;
	static   XPath xPath;
	static   String expression;
	static  XPathExpression xPathExpression;
	static Object result;
	static String h;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head><title>Serach Results</title><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		out.println("<center>");
		try
		{
			String q=request.getParameter("q");
			String cat=request.getParameter("cat");
			//out.print(q+cat);
			h="";
			 Set<String> wa = new HashSet<String>();
			Connection con;
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement st=con.createStatement();
			qu=q.split(" ");
			System.out.print(qu.length);
			for(int i=0;i<qu.length;i++)
			{
				h=h+", '"+qu[i]+"'";
			}
			//out.println(h);
			h=h.substring(1, h.length());
			h=h.toUpperCase();
			System.out.println(h);
			ResultSet rs=st.executeQuery("Select indexes from keyword  where keyword in("+h+")");
			while(rs.next())
			{
				l=rs.getString(1);
				//System.out.println(l);
				dFactory = DocumentBuilderFactory.newInstance();
				dFactory.setNamespaceAware(true);
				dFactory.setValidating( true );
	    		docBuilder = dFactory.newDocumentBuilder();
				dFactory = DocumentBuilderFactory.newInstance();
				//dFactory.setNamespaceAware(true);
				//dFactory.setValidating( true );
				doc = docBuilder.parse(new InputSource(new StringReader(l)));
				root = doc.getDocumentElement();
				XPathFactory xPathFactory = XPathFactory.newInstance();
				// To get an instance of the XPathFactory object itself.
				XPath xPath = xPathFactory.newXPath();
				// Create an instance of XPath from the factory class.
				String expression = "//id/text()";
				XPathExpression xPathExpression = xPath.compile(expression);
				// Compile the expression to get a XPathExpression object.
				Object result = xPathExpression.evaluate(doc,XPathConstants.NODESET);
				// Evaluate the expression against the XML Document to get the result.
				NodeList nodes = (NodeList) result;
				for (int j = 0; j < nodes.getLength(); j++) {
				    wa.add(nodes.item(j).getNodeValue().toString()); 
				}
			}
			Object[] arrObj = wa.toArray();
			String u="";
		    for (int i = 0; i < arrObj.length; i++) {
		    	u=u+", "+arrObj[i]+"";
		    }
		    u=u.substring(1, u.length());
		    System.out.println(u);
		    System.out.println("Select articleid from article_category  where articleid in("+u+") and category like '"+cat+"'");
		    ResultSet rs1=st.executeQuery("Select articleid from article_category  where articleid in("+u+") and category like '"+cat+"'");
		    
		    while(rs1.next())
		    {
		    	System.out.println(rs1.getString(1));
		    }
		    rs1=st.executeQuery("Select id,title from article where id in("+"Select articleid from article_category  where articleid in("+u+") and category like '"+cat+"')");
		    while(rs1.next())
		    {
		    	out.println("<li>"+"<a href='http://localhost:8080/OCM/ViewArticle?id="+rs1.getString(1)+"'>"+rs1.getString(2)+"</a></li>");
		    }
		}
		catch(Exception e)
		{
			
			out.print(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
