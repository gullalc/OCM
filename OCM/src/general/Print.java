package general;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.avalon.framework.logger.ConsoleLogger;
import org.apache.avalon.framework.logger.Logger;
import org.apache.fop.apps.Driver;
import org.apache.fop.messaging.MessageHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class Print
 */
public class Print extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Print() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   static String article,articles,id,title;
   DocumentBuilderFactory dFactory;
   Document doc;
   Element root;
   DocumentBuilder docBuilder;
   XPathFactory xPathFactory;
   XPath xPath;
   String expression;
   XPathExpression xPathExpression;
   Object result;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			
				
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
				Statement st1=con.createStatement();
				ResultSet rs1 = st1.executeQuery("select article,title,id from article where id="+request.getParameter("id"));
				while(rs1.next())
				{
					article=rs1.getString(1);
					title=rs1.getString(2);
					id=rs1.getString(3);
				}
				dFactory = DocumentBuilderFactory.newInstance();
				dFactory.setNamespaceAware(true);
				dFactory.setValidating( true );
	    		docBuilder = dFactory.newDocumentBuilder();
	    		XPathFactory xPathFactory = XPathFactory.newInstance();
				articles="<root><title>"+title+"</title>"+"<id>"+id+"</id><content>";
				
				XPath xPath = xPathFactory.newXPath();
				doc = docBuilder.parse(new InputSource(new StringReader(article)));
				root = doc.getDocumentElement();
				String expression = "//content/*/text()";
				XPathExpression xPathExpression = xPath.compile(expression);
				
				Object result = xPathExpression.evaluate(doc,XPathConstants.NODESET);
				
				NodeList nodes = (NodeList) result;
				for (int i = 0; i < nodes.getLength(); i++) {
				    articles=articles+nodes.item(i).getNodeValue(); 
				}
				articles=articles+"</content></root>";
				 File xslt=new File(getServletContext().getRealPath("xslt/Print.xsl"));
		Driver driver=new Driver();
		Logger logger= new ConsoleLogger(ConsoleLogger.LEVEL_WARN);
		driver.setLogger(logger);
		MessageHandler.setScreenLogger(logger);
		
		driver.setRenderer(Driver.RENDER_PDF);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        driver.setOutputStream(out);
           TransformerFactory factory=TransformerFactory.newInstance();
        Transformer transformer=factory.newTransformer(new StreamSource(xslt));
         Source src=new StreamSource(new StringReader(articles));
         Result res=new SAXResult(driver.getContentHandler());
        
          transformer.transform(src,res);
        response.setContentType("application/pdf");
        byte[] content = out.toByteArray();
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
        response.getOutputStream().flush();
        out.close();
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
