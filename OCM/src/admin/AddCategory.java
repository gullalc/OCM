package admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.sql.*;
/**
 * Servlet implementation class AddCategory
 */
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Connection con;
     Statement st;
     ResultSet rs;
     String maxid,category;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1" ,"1491" );
			st=con.createStatement();
			category=request.getParameter("cat");
			rs=st.executeQuery("select max(id) from category" );
			while(rs.next())
			{
				if(rs.getString(1)==null)
				{
					maxid="1";
					
				}
				else
				{
					maxid=Integer.toString(Integer.parseInt(rs.getString(1))+1);
					
				}
				
			}
			PrintWriter out=response.getWriter();
			
			st.executeUpdate("Insert into category (category) values ('"+category+"')");
			  
			  
			  
			  File main=new File(getServletContext().getRealPath("xml/mainpage.xml"));
			  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			  
				//Get the DocumentBuilder
				DocumentBuilder docBuilder = factory.newDocumentBuilder();
			 
				//Using existing XML Document
				Document doc = docBuilder.parse(main);
			  NodeList list = doc.getElementsByTagName("categories");
			  if(list.getLength() == 0){
			  out.println("Element doesn't exist in the " +  " Document.");
			  }
			  else
			  {
				  
				  Element cat=doc.createElement("category");
				  
				  cat.setAttribute("id", maxid);
				  
				  cat.appendChild(doc.createTextNode(category));
				  
				  list.item(0).appendChild(cat);
				  
				  TransformerFactory transfac = TransformerFactory.newInstance();
				    Transformer trans = transfac.newTransformer();
				    
				    
				    StringWriter sw = new StringWriter();
				    StreamResult result = new StreamResult(sw);
				    DOMSource source = new DOMSource(doc);
				    trans.transform(source, result);
				    String xmlString = sw.toString();
				 
				    //Saving the XML content to File
				    OutputStream f0,f1;
				    byte buf[] = xmlString.getBytes();
				    f0 = new FileOutputStream(getServletContext().getRealPath("xml/mainpage.xml"));
				    f1= new FileOutputStream("/home/db2inst1/Desktop/d/OCM/WebContent/xml/mainpage.xml");
				    for(int i=0;i<buf .length;i++) {
				 	f0.write(buf[i]);
				 	f1.write(buf[i]);
				    }
					f0.close();
					f1.close();
					buf = null;
			  }
			  out.println("<h3>Category "+category+" successfully added with id "+maxid); 
		}
		catch(Exception e)
		{
			response.getWriter().println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
