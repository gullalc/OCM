package reguser;

import java.io.File;
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
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class ViewThread
 */
public class ViewThread extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewThread() {
        super();
        // TODO Auto-generated constructor stub
    }
    static String title,desc,postdate,comments,liked_by,likes,creater;
    static String likes_enable="true"; 
    DocumentBuilderFactory dFactory;
    Document doc;
    Element root;
    DocumentBuilder docBuilder;
    XPathFactory xPathFactory;
    XPath xPath;
    String expression;
    XPathExpression xPathExpression;
    Object result;
    FileWriter file;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		try {
			PrintWriter out = response.getWriter();
			
			out.println("<head><link href='style/Profile.css' rel='stylesheet' type='text/css' /></head>");
			out.println("<script type='text/javascript' src='scripts/profile.js'></script>");
			out.println("<center>");
			//response.addHeader("Refresh", "15");
			//response.getWriter().println("select photo from "+request.getParameter("post").toString()+" where id='"+request.getParameter("id").toString()+"'");
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
			Statement st1=con.createStatement();
			ResultSet rs1 = st1.executeQuery("select title,desc,postdate,comments,liked_by,likes,creater from discussionforum where id="+request.getParameter("id"));
			while(rs1.next())
			{
				title=rs1.getString(1);
				
				desc=rs1.getString(2);
				postdate=rs1.getString(3);
				comments=rs1.getString(4);
				liked_by=rs1.getString(5);
				likes=rs1.getString(6);
				creater=rs1.getString(7);
			}
			out.println("<div>");
			out.println("<h4>"+title+"</h4>");
			out.println("<h5>Thread created on "+postdate+" by "+creater);
			out.println("<p>"+desc+"<p>");
			if(session.getAttribute("username")!=null)
			{
				dFactory = DocumentBuilderFactory.newInstance();
				dFactory.setNamespaceAware(true);
				dFactory.setValidating( true );
	    		docBuilder = dFactory.newDocumentBuilder();
			doc = docBuilder.parse(new InputSource(new StringReader(liked_by)));
			root = doc.getDocumentElement();
			XPathFactory xPathFactory = XPathFactory.newInstance();
			
			XPath xPath = xPathFactory.newXPath();
			
			String expression = "//root[text()='"+session.getAttribute("username").toString()+"']";
			XPathExpression xPathExpression = xPath.compile(expression);
			
			Object result = xPathExpression.evaluate(doc,XPathConstants.NODESET);
			
			NodeList nodes = (NodeList) result;
			if(nodes.getLength()>0)
			{
				likes_enable="false";
			}}
			
			try
			{
				
				String fileName=getServletContext().getRealPath("xslt/Comment.xsl");
				File a=new File(fileName);
				
				StreamSource xmlfile = new StreamSource(new StringReader(comments));
				Source xsltSource=new StreamSource(a);
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer(xsltSource);
			
				transformer.setParameter("likes", likes);
				transformer.setParameter("likes_enable", likes_enable);
				transformer.setParameter("post", "article");
				transformer.setParameter("id", request.getParameter("id"));
				

				transformer.transform(xmlfile, new StreamResult(out));
			out.println("</center>");
			}
			catch(Exception e)
			{
			out.println(e);
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
