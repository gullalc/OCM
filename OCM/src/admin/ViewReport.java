package admin;

import java.io.ByteArrayOutputStream;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.avalon.framework.logger.ConsoleLogger;
import org.apache.avalon.framework.logger.Logger;
import org.apache.fop.apps.Driver;
import org.apache.fop.messaging.MessageHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Servlet implementation class ViewReport
 */
public class ViewReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    DocumentBuilderFactory dFactory;
    Document doc;
    Element root;
    DocumentBuilder docBuilder;
    XPathFactory xPathFactory;
    XPath xPath;
    String expression;
    XPathExpression xPathExpression;
    Object result;
    static String report;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			
				
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
				Statement st1=con.createStatement();
				ResultSet rs1 = st1.executeQuery("select report from moderator where id="+request.getParameter("id"));
				while(rs1.next())
				{
					report=rs1.getString(1);
				}
				
				
				 File xslt=new File(getServletContext().getRealPath("xslt/Report.xsl"));
		Driver driver=new Driver();
		Logger logger= new ConsoleLogger(ConsoleLogger.LEVEL_WARN);
		driver.setLogger(logger);
		MessageHandler.setScreenLogger(logger);
		
		driver.setRenderer(Driver.RENDER_PDF);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        driver.setOutputStream(out);
           TransformerFactory factory=TransformerFactory.newInstance();
        Transformer transformer=factory.newTransformer(new StreamSource(xslt));
         Source src=new StreamSource(new StringReader(report));
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
		try
		{
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
				Statement st1=con.createStatement();
				out.println("Reports available");
				out.println("<table>");
				ResultSet rs1 = st1.executeQuery("select id, username from moderator where report is not null");
				while(rs1.next())
				{
					out.println("<tr><td>"+rs1.getString(1)+"</td><td><a href='http://localhost:8080/OCM/ViewReport?id="+rs1.getString(2)+"'>View report</a></tr>");
				}
				out.println("</table>");
				
				
		
		}
		catch(Exception e)
		{
			response.getWriter().println(e);
		}
	}

}
