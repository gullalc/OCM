package general;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Regex
 */
public class Regex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regex() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    Statement st;
    ResultSet rs;
    LinkedList<String> l1;
    boolean found=false;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		 {
			
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
		 
		 st=con.createStatement();
		 rs=st.executeQuery("select word from bad_words");
		 l1=new LinkedList<String>();
		 while(rs.next())
		 {
			 l1.add(rs.getString("WORD"));
		 }
		 String str=request.getParameter("a");
		 if(str.length()<1)
		 response.getWriter().println("Article content cannot be empty");
		 for(int i=0;i<l1.size();i++)
		 {
			 Pattern pat=Pattern.compile("\\b"+l1.get(i)+"\\b");
			 
			 Matcher m=pat.matcher(request.getParameter("a"));
			 if (m.find()) {
					response.getWriter().println("Found unacceptable word/words please re-input");
					
				}
			 
		 }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
