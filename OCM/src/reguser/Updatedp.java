package reguser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
/**
 * Servlet implementation class Updatedp
 */
public class Updatedp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updatedp() {
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
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		  response.setContentType("text/html");
		  boolean isMultipart = ServletFileUpload.isMultipartContent(
		  request);
		  
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
		  Iterator <?>itr = items.iterator();
		  while (itr.hasNext()) {
		  FileItem item = (FileItem) itr.next();
		  if (item.isFormField()){

		  
		  
		  
		  } else {
		  try {
		  String itemName = item.getName();
		  String finalimage = itemName;
		  ServletConfig config=getServletConfig();
		  Random generator = new Random();
		  int r = Math.abs(generator.nextInt());
		  int IndexOf = itemName.indexOf("."); 
		  String domainName = itemName.substring(IndexOf);
		  finalimage=r+domainName;
		  File savedFile = new File(config.getServletContext().getRealPath("/")+finalimage);
		  item.write(savedFile);
		  out.println("<html>");
		  out.println("<body>");
		  out.println("<table><tr><td>");
		  out.println("<img src="+finalimage+">");
		  out.println("</td></tr></table>");
		  PreparedStatement psmnt;
		  Connection conn = null;
		  String url = "jdbc:db2://localhost:50000/PRAC";
		  
		  Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();

		conn = DriverManager.getConnection(	url, "db2inst1", "1491");

		File image = new File(config.getServletContext().getRealPath("/")	+ finalimage);

		psmnt = conn.prepareStatement("insert into img(img) "	+ "values(?)");

		FileInputStream fis = new FileInputStream(image);
		psmnt.setBinaryStream(1, (InputStream) fis,
			(int) (image.length()));

		int s = psmnt.executeUpdate();
		if (s > 0) {
		out.println("<h1>Uploaded successfully !</h1>");
		String id = (String) session.getAttribute("id");
		String photo="http://localhost:8080/OCM/Img?img=";
		 
		  if (id != null ) {
			try
			{
			Connection con;
			String profile="";
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement st=con.createStatement();
			ResultSet rs1=st.executeQuery("select max(id) from img");
			while(rs1.next())
			{
				photo=photo+rs1.getString(1);
			}
			ResultSet rs=st.executeQuery("select profile from reguser where id="+id);
			while(rs.next())
			{
				profile=rs.getString(1);
			}
				DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(profile));
		    Document doc = db.parse(is);
		    NodeList lis=doc.getElementsByTagName("image");
		    
		    for(int i=0;i<lis.getLength();i++)
		    {
		    	if(lis.item(i).getNodeType()==1)
		    	{
		    		
		    		lis.item(i).setTextContent(photo);
		    		
		    	}
		    }
		  
		  doc.normalize();
		    
		    TransformerFactory transfac = TransformerFactory.newInstance();
		    Transformer trans = transfac.newTransformer();
		    
		    
		    StringWriter sw = new StringWriter();
		    StreamResult result = new StreamResult(sw);
		    DOMSource source = new DOMSource(doc);
		    trans.transform(source, result);
		    String xmlString = sw.toString();
		    
		    st.executeUpdate("UPDATE reguser SET profile='"+xmlString+"' where id="+id);
		    response.sendRedirect("http://localhost:8080/OCM/Profile?id="+id);
		    //out.println("<script type='type/javascript'>window.location.href='http://localhost:8080/OCM/Profile?id="+id+"'</script>");
			}
			catch(Exception e)
			{
				out.println(e);
			}
		} else {
		
		}

		} }catch (Exception e) {
		e.printStackTrace();
		out.println(e);
		} 
		  }
		  }
		  }
	}

}
