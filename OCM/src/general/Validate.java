package general;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Boolean valid=true;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			else if (value==null||value.equals(""))
			{
				out.println("<error>"+Element+"_error"+"</error>");
				
			}
			
			
		}
		
		if(request.getParameter("servlet_name").equals("reg"))
		{
			
			out.println("<servlet_name>"+request.getParameter("servlet_name").toString()+"</servlet_name>");
			String params[]={"occ","fname","lname","uname","pass","cls","batch","rno","email","adr","dob","gender"};
			
			for(int j=0;j<params.length;j++)
			{
				if(request.getParameter(params[j])==null||request.getParameter(params[j])=="")
				{
					out.println("<"+params[j]+">"+"empty"+"</"+params[j]+">");
				}
				else
				{
					out.println("<"+params[j]+">"+request.getParameter(params[j]).toString()+"</"+params[j]+">");
				}
			}
			
		}
		
		out.println("</root>");
		
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
