<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.Properties, javax.mail.*, javax.mail.internet.*,javax.mail.Message.RecipientType.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
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



%>
	<% String a=sendmail("javadev911@gmail.com","Hello","Message from jsp.......");
out.print(a);
%>
</body>
</html>