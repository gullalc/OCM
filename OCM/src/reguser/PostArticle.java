package reguser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class PostArticle
 */
public class PostArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	static String title,keywords,categories,content,uploader,finalimage;
	int count1=0,count2=0,count3=0,count4=0;
	static String cats[];
	static String keys[];
	static String id,date;
	static String contents="";
	static Connection conn = null;
	static String url = "jdbc:db2://localhost:50000/PRAC";
	static ResultSet rs;
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
		  response.setContentType("text/html");
		  boolean isMultipart = ServletFileUpload.isMultipartContent(
		  request);
		  
		  if (!isMultipart)
		  {
			  out.println("File Not Uploaded");
		  } 
		  else 
		  {
		  FileItemFactory factory = new DiskFileItemFactory();
		  ServletFileUpload upload = new ServletFileUpload(factory);
		  List<?> items = null;

		  
		  items = upload.parseRequest(request);
		  Iterator<?> itr = items.iterator();
		  while (itr.hasNext())
		  {
			  FileItem item = (FileItem) itr.next();
		  if (!item.isFormField())
		  {
			  
			  String itemName = item.getName();
			  if(itemName.length()>0)
			  {
			  ServletConfig config=getServletConfig();
			  Random generator = new Random();
			  int r = Math.abs(generator.nextInt());
			  int IndexOf = itemName.indexOf("."); 
			  String domainName = itemName.substring(IndexOf);
			  
			  finalimage = r+domainName;
			  
		     
			  File savedFile = new File(config.getServletContext().getRealPath("/")+ finalimage);
			  savedFile.createNewFile();
			  item.write(savedFile);
			  }
		  
		  }
		  
		  
		  else 
		  				{
			 	
			  String name = item.getFieldName();
              String value = item.getString();
		              if(name.equals("title"))
		               {
		                       title=value;
		                       
		                       
                       }
                      if(name.equals("keyw"))
                      {  
                                     keywords=value;                         
                                    
                                     
                      }
                      if(name.equals("hiddencat"))
                      {  
                                     categories=value;                         
                                     
                                     
                                     
                      }
                      if(name.equals("hiddencont"))
                      {
                             content=value;
                             
                             
                      }
                 } 
		  
		  }
		  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   
		   Date dates = new Date();
		   date=dateFormat.format(dates);
		  
		  Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		  conn = DriverManager.getConnection(	url, "db2inst1", "1491");
		  
		  contents=contents+"<root><date>"+date+"</date>"+"<title>"+title+"</title>"+"<keywords>"+keywords+"</keywords>"+
				"<uploader>"+session.getAttribute("username")+"</uploader>"+"<content>"+content+"</content><categories>";
		  cats=categories.split("_");
		  for(int i=0;i<cats.length;i++)
		  {
			  contents=contents+"<category>"+cats[i]+"</category>";
		  }
		  contents=contents+"</categories></root>";
		  
		  
		  ServletConfig config=getServletConfig();
		  File savedFile = new File(config.getServletContext().getRealPath("/")+ finalimage);
		  if(savedFile.exists())
		  {
			  PreparedStatement preparedStatement =  conn.prepareStatement("INSERT INTO ARTICLE(PHOTO,COMMENTS,LIKED_BY,keywords,UPLOADER,TITLE,ARTICLE) VALUES(?,?,?,?,?,?,?)"); 
			  
			  InputStream inputStream = new FileInputStream(savedFile); 
			  
			  preparedStatement.setBinaryStream(1,inputStream,(int)(savedFile.length())); 
			  preparedStatement.setString(2, "<root></root>");
			  preparedStatement.setString(3, "<root></root>");
			  preparedStatement.setString(4, keywords);
			  preparedStatement.setString(5, session.getAttribute("username").toString());
			  preparedStatement.setString(6, title);
			  preparedStatement.setString(7, contents);
			  int rs=preparedStatement.executeUpdate(); 
			  if(rs>0)
			  out.println("<h1>Uploaded successfully !</h1>");
		  }
		  else
		  {
PreparedStatement preparedStatement =  conn.prepareStatement("INSERT INTO ARTICLE(COMMENTS,LIKED_BY,keywords,UPLOADER,TITLE,ARTICLE) VALUES(?,?,?,?,?,?,?)"); 
			  
			  preparedStatement.setString(1, "<root></root>");
			  preparedStatement.setString(2, "<root></root>");
			  preparedStatement.setString(3, keywords);
			  preparedStatement.setString(4, session.getAttribute("username").toString());
			  preparedStatement.setString(5, title);
			  preparedStatement.setString(6, contents);
			  int rs=preparedStatement.executeUpdate(); 
			  if(rs>0)
			  out.println("<h1>Uploaded successfully !</h1>");
		  }
		  
		  
		  }
		  }
	 catch (Exception e) 
	 {
		response.getWriter().println("<h1>There seems to be a problem please go back refresh the page and then re enter...Dont post without refreshing</h1>");
	} 
}
		else
		{
			response.getWriter().println("You must be logged in to post an article");
		}
}
}
