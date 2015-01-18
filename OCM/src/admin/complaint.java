package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class complaint
 */
public class complaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public complaint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><title>Moderator requests</title><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		String subject = request.getParameter("subject");
		String comp_sugg = request.getParameter("comp_sugg");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		
		response.setContentType("text/html");
		
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
			
			if(username==null)
			{	
				String query = "Insert into complaint(subject,comp_sugg,email_username) values('"+subject+"','"+comp_sugg+"','"+email+"')";
				int i= stmt.executeUpdate(query);
				if(i>0)
				{
					out.println("Suggestion/complaint submitted successfully." +
							"An auto generated complaint/suggestion ID has been sent to your email" +
							"Administrator will definitely take action/respond to your complaint/suggestion if possible");
					String a=sendmail(email,"Complaint received","Suggestion/complaint submitted successfully");
					out.println(a);
				}
				else
				{
					out.println("There seems to be a problem. Please try again later.");
				}
			}
			else
			{
				String query = "Insert into complaint(subject,comp_sugg,email_username) values('"+subject+"','"+comp_sugg+"','"+username+"')";
				int i= stmt.executeUpdate(query);
				if(i>0)
				{
					out.println("Suggestion/complaint submitted successfully." +
							"An auto generated complaint/suggestion ID has been sent to your email" +
							"Administrator will definitely take action/respond to your complaint/suggestion if possible");
				}
				else
				{
					out.println("There seems to be a problem. Please try again later.");
				}
			}
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
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
		   return new PasswordAuthentication("jas1291@gmail.com","Nokia5233");//change accordingly
		   }
		  });
		 
		//compose message
		  try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress("jas1291@gmail.com"));//change accordingly
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		   message.setSubject(Subject);
		   message.setText(emailmessage);
		   
		   //send message
		   Transport.send(message);
			String a="Message Sent Successfully";
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
