

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: Validate
 *
 */
 public class Validate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Validate() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml");
		PrintWriter out=response.getWriter();
	
		out.println("<root>");
		String value=null;
		String Element=null;
		Enumeration<?> list=request.getParameterNames();
		while(list.hasMoreElements())
		{
			Element=list.nextElement().toString();
			value=request.getParameter(Element);
			
			if(Element.equals("cls")||Element.equals("batch")||Element.equals("rno")||Element.equals("dob")||Element.equals("occ"))
			{
				if(request.getParameter("occ").toString().equals("Student"))
				{
					if (value==null||value.equals(""))
					{
						out.println("<error>"+Element+"_error"+"</error>");
					}
				}
			}
			
			else if ((value==null||value.equals(""))&!Element.equals("adr")&!Element.equals("valid")&!Element.equals("list"))
			{
				out.println("<error>"+Element+"_error"+"</error>");
			}
			else if(request.getParameter("cpass")!=null)
			{ 
				if(!request.getParameter("pass").equals(request.getParameter("cpass")))
				{
					out.println("<unequal>"+"cpass_error"+"</unequal>");
				}
			
			}
		}
		out.println("</root>");
	
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			}

}
   	  	    
