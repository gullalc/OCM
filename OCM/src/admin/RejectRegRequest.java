package admin;

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

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
/**
 * Servlet implementation class RejectRegRequest
 */
public class RejectRegRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    ResultSet rs;
    Statement st;
    String profile,email;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectRegRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			rs=st.executeQuery("Select info from REGISTERATION  where rid="+request.getParameter("rid"));
			if(rs.next())
			{
				
				profile=rs.getString(1);
				
				
				
				DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			    DocumentBuilder db = dbf.newDocumentBuilder();
			    InputSource is = new InputSource();
			    is.setCharacterStream(new StringReader(profile));

			    Document doc = db.parse(is);
				Element root=doc.getDocumentElement();
				
				email=root.getElementsByTagName("email").item(0).getFirstChild().getTextContent();
				String a=sendmail(email,"Registeration Successful","Your registeration request has been rejected by the OCM Administrator due to "+request.getParameter("reason"));
				out.print(a);
				
			}
			
			out.println("update registeration set status=3 ,reason='"+request.getParameter("reason")+"' where rid="+request.getParameter("rid"));
			st.executeUpdate("update registeration set status=3, reason='"+request.getParameter("reason")+"' where rid="+request.getParameter("rid"));
			response.sendRedirect("http://localhost:8080/OCM/RegRequests");
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
	public String sendmail(String receiver,String Subject, String emailmessage)
	{
		String to=receiver;//change accordingly

		//Get the session object
		  Properties props = new Properties();
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.socketFactory.port", "465");
		  props.put("mail.smtp.socketFactory.class",
		        	"javax.net.ssl.SSLSocketFactory");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.port", "465");
		 
		  Session session = Session.getInstance(props,
		   new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		   return new PasswordAuthentication("testerocm@gmail.com","testerocm123");//change accordingly
		   }
		  });
		 
		//compose message
		  try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress("testerocm@gmail.com"));//change accordingly
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		   message.setSubject(Subject);
		   message.setText(emailmessage);
		   
		   //send message
		   Transport.send(message);
			String a="Your password has been reset. Check your mail";
		  return a;
		 
		  } catch (AuthenticationFailedException ex) {
			  String a=ex.getMessage();
			  return a;
			  

	          
	      } catch (AddressException ex) {
	    	  String a=ex.getMessage();
			  return a;

	      } catch (MessagingException ex) {
	    	  String a=ex.getMessage();
			  return a;
	      }
		 }

}
