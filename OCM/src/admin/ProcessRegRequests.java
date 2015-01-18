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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet implementation class ProcessRegRequests
 */
public class ProcessRegRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessRegRequests() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    ResultSet rs;
    Statement st;
    String profile, name,occ,cls,batch,email,dob;
    String username;
    String password;
    String rid,id;
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
			rs=st.executeQuery("Select rid,info,username,password from REGISTERATION  where rid="+request.getParameter("rid"));
			if(rs.next())
			{
				rid=rs.getString(1);
				profile=rs.getString(2);
				username=rs.getString(3);
				password=rs.getString(4);
				
				
				DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			    DocumentBuilder db = dbf.newDocumentBuilder();
			    InputSource is = new InputSource();
			    is.setCharacterStream(new StringReader(profile));

			    Document doc = db.parse(is);
				Element root=doc.getDocumentElement();
				name=root.getElementsByTagName("fname").item(0).getFirstChild().getTextContent()+" "+root.getElementsByTagName("lname").item(0).getFirstChild().getTextContent();
				occ=root.getElementsByTagName("occ").item(0).getFirstChild().getTextContent();
				cls=root.getElementsByTagName("cls").item(0).getFirstChild().getTextContent();
				batch=root.getElementsByTagName("batch").item(0).getFirstChild().getTextContent();
				email=root.getElementsByTagName("email").item(0).getFirstChild().getTextContent();
				dob=root.getElementsByTagName("dob").item(0).getFirstChild().getTextContent();
				
			}
			
			String xml=""+
						"<profile>"+
							"<username>"+username+"</username>"+
							"<usertype>Registered</usertype>"+
							"<image></image>"+
							"<status></status>"+
							"<view>"+
								"<Info>"+						
								"<name>"+name+"</name>"+
								"<occupation>"+occ+"</occupation>"+
								"<class>"+cls+"</class>"+
								"<batch>"+batch+"</batch>"+
								"<email>"+email+"</email>"+
								"<date_of_birth>"+dob+"</date_of_birth>"+
								"<school></school><graduation_college></graduation_college>"+
								"<post_graduation_college></post_graduation_college><hobbies></hobbies>"+
								"<favorite_quote />"+
								"<about />"+
								"</Info>"+
							"</view>"+
								"<progress>"+
										"<last_login></last_login><posts></posts><post_accepted></post_accepted>"+
										"<post_rejected></post_rejected><ep></ep><capture></capture><threads></threads>"+
								"</progress>"+							
						"</profile>";
			
			st.executeUpdate("Insert into login(username,password,usertype,email) values ('"+username+"','"+password+"','Registered','"+email+"')");
			rs=st.executeQuery("Select max(id) from login");
			int i=0;
			if(rs.next())
			{
				i=Integer.parseInt(rs.getString(1));
			}
			
			st.executeUpdate("Insert into reguser(id,profile,loginstatus) values ("+i+",'"+xml+"',0)");
			String a=sendmail(email,"Registeration Successful","Your registeration request has been accepted by the OCM Administrator");
			out.print(a);
			st.executeUpdate("update registeration set status=1 where rid="+rid);
			response.sendRedirect("http://localhost:8080/OCM/RegRequests");
			
			
		}
		catch(Exception e)
		{
			out.println(e+"There seems to be a problem. Please try again later");
		}
		out.println("<div>");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		out.println("<form action='http://localhost:8080/buzzz/RejectRegRequest'>");
		out.println("<input type='hidden' name='rid' value='"+request.getParameter("rid")+"'/>");
		out.println("Reason:<input type='text' name='reason'/></br>");
		out.println("<input type='submit'/>");
		out.println("</form>");
	}

}
