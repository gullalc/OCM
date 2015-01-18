package reguser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class PostPhoto
 */
public class PostPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostPhoto() {
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
	static String title,descrip,finalimage;
	PreparedStatement psmnt;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		if(session.getAttribute("username")!=null)
		{
			try
			{// TODO Auto-generated method stub
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><link href='style/homepage.css' rel='stylesheet' type='text/css' /></head>");
			out.println("<script type='text/javascript' src='header.js'></script>");
			out.println("<body onload='header()'>");
			out.println("<div id='head'></div>");
			out.println("<div>");
			out.println("<center>");
			  response.setContentType("text/html");
			  boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			  if (!isMultipart) {
				  out.println("File Not Uploaded");
				  } else {
				  FileItemFactory factory = new DiskFileItemFactory();
				  ServletFileUpload upload = new ServletFileUpload(factory);
				  List<?> items = null;

				  try {
				  items = upload.parseRequest(request);
				  
				  } catch (FileUploadException e) {
				  e.printStackTrace();
				  }
				  Iterator<?> itr = items.iterator();
				  while (itr.hasNext()) {
				  FileItem item = (FileItem) itr.next();
				  if (item.isFormField()){
					  String name = item.getFieldName();
		              String value = item.getString();
					  if(name.equals("title"))
		               {
		                       title=value;
		                       
		                       
                      }
                     if(name.equals("descrip"))
                     {  
                                    descrip=value;                         
                                   
                                    
                     }
                     
				  
				  
				  } else {
				  try {
					  String itemName = item.getName();
					  
					  
					  ServletConfig config=getServletConfig();
					  Random generator = new Random();
					  int r = Math.abs(generator.nextInt());
					  int IndexOf = itemName.indexOf("."); 
					  String domainName = itemName.substring(IndexOf);
					  
					  finalimage = r+domainName;
					  
				     
					  File savedFile = new File(config.getServletContext().getRealPath("/")+ finalimage);
					  savedFile.createNewFile();
					  item.write(savedFile);
					  out.println("<html>");
					  out.println("<body>");
					  out.println("<table><tr><td>");
					  out.println("<img src="+finalimage+">");
					  out.println("</td></tr></table>");
					 } 
				  catch (Exception e)
				  {
								
								out.println(e);
				  } 
				  
						
						
		 
		}
				  
				  }
				  ServletConfig config=getServletConfig();
				  File savedFile = new File(config.getServletContext().getRealPath("/")+ finalimage);
				  Connection conn = null;
				  String url = "jdbc:db2://localhost:50000/PRAC";
				 
				  
				  Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				  conn = DriverManager.getConnection(	url, "db2inst1", "1491");
				  PreparedStatement preparedStatement =  conn.prepareStatement("INSERT INTO PHOTOS(PHOTO,COMMENTS,LIKED_BY,CAPTION,UPLOADER,TITLE) VALUES(?,?,?,?,?,?)"); 
				  
				  InputStream inputStream = new FileInputStream(savedFile); 
				  
				  preparedStatement.setBinaryStream(1,inputStream,(int)(savedFile.length())); 
				  preparedStatement.setString(2, "<root></root>");
				  preparedStatement.setString(3, "<root></root>");
				  preparedStatement.setString(4, descrip);
				  preparedStatement.setString(5, session.getAttribute("username").toString());
				  preparedStatement.setString(6, title);
				  int rs=preparedStatement.executeUpdate(); 
				  if(rs>0)
				  out.println("<h1>Uploaded successfully !</h1>"+"<h2>"+title+"</h2>"+"<h2>"+descrip+"</h2>");
				  out.println("</div>");
				  }}  catch (Exception e2) 
					 {
						response.getWriter().println(e2);
					} 
		}
		else
		{
			response.getWriter().println("You must be logged in to post a photograph");
		}
}
}