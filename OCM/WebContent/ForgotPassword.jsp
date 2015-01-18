<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
	import="java.util.Properties, javax.mail.*, javax.mail.internet.*,javax.mail.Message.RecipientType.*,java.sql.*,java.util.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!
Connection con;
Statement st;
ResultSet rs;
static String password;
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



%>
<script type="text/javascript" src="Validation.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<center>
<form method="post">
<table cellspacing="15">
<tr>
<td>Email</td>
<td> <input type="text" name="email" id="email"/></td>
<td><span id="email_error"></span></td>
</tr>

<tr>
<td>Captcha</td>
<td><input type="text" name="code" id="code"></td>
<td><span id="code_error" id="code_error"></span></td>
</tr>

</table>

<br>
<img src="http://localhost:8080/OCM/CaptchaServlet" height=75 width=175> 

<br><br>
<input type="Submit" value="Submit" >

</form>
<br><br>
<%
  String captcha = (String) session.getAttribute("captcha");
  String code = (String) request.getParameter("code");

  if (captcha != null && code != null) {

    if (captcha.equals(code.toUpperCase())) {
        
	  out.print("<p class='alert'>Correct</p>");
	  String email=request.getParameter("email");
	  if(email==null||email.equals(""))
		{
			out.println("<email>Email address cannot be empty</email>");
		}
		else
		{
			try
			{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			rs=st.executeQuery("Select email from login where email='"+email+"'");
			if(rs.next())
			{
				Random r = new Random();
				password = Long.toString(Math.abs(r.nextLong()), 36);
				String a=sendmail(email,"Password Change","Your new password is : "+password);
				out.print(a);
			}
			else
			{
				out.println("<email>"+"Invalid email address"+"</email>");
			}
			}
			catch(Exception e)
			{
				out.println(e);
			}
			
		}
	  
    } else {
          out.print("<p class='alert'>Incorrect</p>");
    }
  }
 
%>
</center>
</body>
</html>