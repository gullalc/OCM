package general;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.sql.*;
/**
 * Servlet implementation class Registeration
 */
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String param="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registeration() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection conn;
    Statement st;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>Registeration</title>");
		
		out.println("<link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
		out.println("<script type='text/javascript' src='header.js'></script>");
		out.println("<body onload='header()'>");
		out.println("<div id='head'></div>");
		out.println("<div>");
		try
		{
				 HttpSession session = request.getSession(true);
		         String xml=session.getAttribute("param").toString();
		         DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
		         DocumentBuilder db = dbf.newDocumentBuilder();
		         InputSource is = new InputSource();
		         is.setCharacterStream(new StringReader(xml));
		         String list[]={"fname","lname","uname","pass","email","occ","cls","batch","rno","gender","dob","adr"};
		         String username="";
		         String password="";
		         String info="<root>";
		         Document doc = db.parse(is);
		         for(int i=0;i<list.length;i++)
	                {
	                	NodeList node = doc.getElementsByTagName(list[i]);
	                    Element firstNameElement = (Element) node.item(0);
	                    NodeList firstName = firstNameElement.getChildNodes();
	                    if(firstName.item(0).getNodeValue().trim()=="empty")
	                    {    
	                    		
			                    
	                    }
	                    else
	                    {
	                    	switch(i)
		                    {
		                    	case 2:
		                    		username= firstName.item(0).getNodeValue().trim();
		                    		break;
		                    	case 3:
		                    		password=firstName.item(0).getNodeValue().trim();
		                    	default:
		                    		info=info+"<"+list[i]+">"+firstName.item(0).getNodeValue().trim()+"</"+list[i]+">";
		                    	
		                    		
		                    }
	                    }
	                    
	                }
		         info=info+"</root>";
		         
		         Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			        conn=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC", "db2inst1", "1491");
			        String query="Insert into REGISTERATION (username,password,info) values ('"+username+"','"+password+"','"+info+"')";
			        
			        st=conn.createStatement();
			        st.executeUpdate(query);
			        out.print("<h1>Your Information was saved Successfully</h1>");
			        out.print("</body></html>");
		                
		}
		catch(Exception e)
		{
			out.print("<h1>There seems to be a problem. Please Try later</h1>");
		}
        
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		String xml=request.getParameter("res");
		xml="<root>"+xml+"</root>";
		
        try
        {
        	PrintWriter out=response.getWriter();
        	out.println("<html><head><title>Registeration</title>");
    		
    		out.println("<link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
    		out.println("<script type='text/javascript' src='header.js'></script>");
    		out.println("<body onload='header()'>");
        	out.println("You have provided the following details for Registeration Process. Click on submit to continue or click on Reset to fill again</br>");
        	DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                String list[]={"fname","lname","uname","pass","email","occ","cls","batch","rno","gender","dob","adr"};
               
                Document doc = db.parse(is);
                
                out.println("<script type='text/javascript' src='http://localhost:8080/OCM/Servlet.js'></script>");
                
                out.println("<table>");
                
                for(int i=0;i<list.length;i++)
                {
                	NodeList node = doc.getElementsByTagName(list[i]);
                    Element firstNameElement = (Element) node.item(0);
                    NodeList firstName = firstNameElement.getChildNodes();
                    if(firstName.item(0).getNodeValue().trim()=="empty")
                    {    
                    		
                    }
                    else
                    {
                    	switch(i)
	                    {
	                    	case 0:
	                    		out.println("<tr><td>First Name:</td><td id='fname1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 1:
	                    		out.println("<tr><td>Last Name:</td><td id='lname1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 2:
	                    		out.println("<tr><td>Username:</td><td id='uname1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 3:
	                    		out.println("<tr><td>Password:</td><td id='pass1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 4:
	                    		out.println("<tr><td>Email:</td><td id='email1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 5:
	                    		out.println("<tr><td>Occupation:</td><td id='occ1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 6:
	                    		out.println("<tr><td>Class:</td><td id='cls1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 7:
	                    		out.println("<tr><td>Batch:</td><td id='batch1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 8:
	                    		out.println("<tr><td>Roll No.:</td><td id='rno1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 9:
	                    		out.println("<tr><td>Gender:</td><td id='gender1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 10:
	                    		out.println("<tr><td>Date of Birth:</td><td id='dob1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    	case 11:
	                    		out.println("<tr><td>Address:</td><td id='adr1'>"+ firstName.item(0).getNodeValue()+"</td><td></tr>");
	                    		break;
	                    		
	                    }
                    }
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("param", xml);
                out.println("<tr><td><button type='button' onclick=confirm('Registeration')>"+" Confirm </button></td><td><button onclick=re_enter('Register') type=button>Re-Enter</button></td><td></tr>");
                out.println("</table>");
                out.print("</body></html>");
                
               
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            response.getWriter().println(e);
        }
	}

}
