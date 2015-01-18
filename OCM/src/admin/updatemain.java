package admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class updatemain
 */
public class updatemain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatemain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    static int value=0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			String query = " Select days(current date) - days(date(up_date)) from counter";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				value = rs.getInt(1);
			}
			if(value>0)
			{
				try
				{	
					String a="<?xml version='1.0' encoding='UTF-8' standalone='no'?><root>"+wod()+tod()+category()+mod()+cap()+ed()+ne()+arc()+mp()+archives()+"</root>";
					
					
					TransformerFactory transfac = TransformerFactory.newInstance();
				    Transformer trans = transfac.newTransformer();
				    
				    
				    StringWriter sw = new StringWriter();
				    StreamResult result = new StreamResult(sw);
				    Source source = new StreamSource(new StringReader(a));
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
					
					query = " Update counter set up_date = current_date";
					stmt.executeUpdate(query);
					response.sendRedirect("/OCM");
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			else
			{
				response.sendRedirect("/OCM");
			}
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

	public String wod()
	{
		Random r = new Random();
		ArrayList<String> WOD = new ArrayList<String>();
		ArrayList<String> meaning = new ArrayList<String>();
		ArrayList<Integer> ID = new ArrayList<Integer>();
		//ArrayList<String> TOD = new ArrayList<String>();	
		//String word, mean;
		
		
		try
		{
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			String query = " Select id,word,meaning from wrd";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				ID.add(rs.getInt(1));
				WOD.add(rs.getString(2));
				meaning.add(rs.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		String words[] = new String[WOD.size()];
		words = WOD.toArray(words);
		
		String meanings[] = new String[meaning.size()];
		meanings = meaning.toArray(meanings);
		int val = r.nextInt(ID.size());
		//System.out.println(words[val]+" "+meanings[val]);
		String a="<word_of_day><word>"+words[val]+"</word><meaning>"+meanings[val]+"</meaning></word_of_day>";
		return a;
	}
	public String tod()
	{
		Random r = new Random();
		ArrayList<String> WOD = new ArrayList<String>();
		ArrayList<String> meaning = new ArrayList<String>();
		ArrayList<Integer> ID = new ArrayList<Integer>();
		//ArrayList<String> TOD = new ArrayList<String>();	
		//String word, mean;
		
		
		try
		{
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			String query = " Select id,thought,author from tod";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				ID.add(rs.getInt(1));
				WOD.add(rs.getString(2));
				meaning.add(rs.getString(3));
			}
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
		
		String words[] = new String[WOD.size()];
		words = WOD.toArray(words);
		
		String meanings[] = new String[meaning.size()];
		meanings = meaning.toArray(meanings);
		int val = r.nextInt(ID.size());
		//System.out.println(words[val]+" "+meanings[val]);
		String a="<thought_of_day><thought>"+words[val]+"</thought><author>"+meanings[val]+"</author></thought_of_day>";
		return a;
	}
	public String category()
	{
		try
		{
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			String query = " Select category from category";
			String c="<categories>";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				c=c+"<category>"+rs.getString(1)+"</category>";
			}
			c=c+"</categories>";
			return c;
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
		
	}
	public String mod()
	{
		try
		{
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			String query = " Select id,username from moderator";
			String c="<moderators>";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				c=c+"<id><moderator>"+rs.getString(2)+"</moderator><link>"+rs.getString(1)+"</link></id>";
			}
			c=c+"</moderators>";
			return c;
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
		
	}
	public String cap()
	{
		String c="<capture><link>";
		
		try
		{
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			String query = " select id, likes from photos order by likes desc fetch first 1 rows only";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				c=c+rs.getString(2);
			}
			c=c+"</link></capture>";
			return c;
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
		
	}
	static String title,article,id;
	public String ed()
	{
		
		try
		{
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			
			
			ResultSet rs1 = stmt.executeQuery("select id,title,article,likes from article where id in(select ep from counter)");
			while(rs1.next())
			{
				title=rs1.getString(2);
				
				id=rs1.getString(1);
				article=rs1.getString(3);
				
				
			}
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = dbf.newDocumentBuilder();	
			InputSource is = new InputSource(); 
			is.setCharacterStream(new StringReader(article)); 
			Document doc = db.parse(is); 

				
			NodeList soaphead = doc.getElementsByTagName("content"); 
			StringWriter sw = new StringWriter(); 
			Transformer serializer = TransformerFactory.newInstance().newTransformer(); 
			serializer.transform(new DOMSource(soaphead.item(0)), new StreamResult(sw));	
			String result = sw.toString();
			result=result.substring(38,238);
			String c="<editor_pick><image>"+id+"</image>"+
					"<link>"+id+"</link> <title>"+title+"</title>"+"<text>"+result+"</div></content></text></editor_pick>";
			return c;
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
		
	}
	public String ne()
	{
		try
		{
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			String query = " Select title,news from news fetch first 5 rows only";
			String c="<news>";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				String d=rs.getString(1);
				Pattern p = Pattern.compile("&");
		        // Create a matcher with an input string
		        Matcher m = p.matcher(d);
		        StringBuffer sb = new StringBuffer();
		        boolean result = m.find();
		        // Loop through and create a new String 
		        // with the replacements
		        while(result) {
		            m.appendReplacement(sb, " ");
		            result = m.find();
		        }
		        // Add the last segment of input to 
		        // the new String
		        m.appendTail(sb);
		        d=sb.toString();
				c=c+"<id><title>"+d+"</title>"+"<text>"+rs.getString(2)+"</text></id>";
			}
			c=c+"</news>";
			return c;
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	public String arc()
	{
		try
		{
			String c="<articles>";
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			
			
			ResultSet rs1 = stmt.executeQuery("select id,title,article,likes from article order by likes desc fetch first 3 rows only");
			while(rs1.next())
			{
				title=rs1.getString(2);
				
				id=rs1.getString(1);
				article=rs1.getString(3);
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
				DocumentBuilder db = dbf.newDocumentBuilder();	
				InputSource is = new InputSource(); 
				is.setCharacterStream(new StringReader(article)); 
				Document doc = db.parse(is); 

					
				NodeList soaphead = doc.getElementsByTagName("content"); 
				StringWriter sw = new StringWriter(); 
				Transformer serializer = TransformerFactory.newInstance().newTransformer(); 
				serializer.transform(new DOMSource(soaphead.item(0)), new StreamResult(sw));	
				String result = sw.toString();
				result=result.substring(38,result.length());
				c=c+"<article><image>"+id+"</image>"+
						"<link>"+id+"</link> <title>"+title+"</title>"+"<text>"+result+"</text></article>";
				
			}
			
			c=c+"</articles>";
			return c;
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	public String mp()
	{
		try
		{
			String c="<most_popular>";
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement stmt =  con.createStatement();
				
			
			
			ResultSet rs1 = stmt.executeQuery("select id,title from article order by likes desc fetch first 5 rows only");
			while(rs1.next())
			{
				title=rs1.getString(2);
				
				id=rs1.getString(1);
				
				c=c+"<mp><link>"+id+"</link> <title>"+title+"</title></mp>";
				
			}
			
			c=c+"</most_popular>";
			return c;
			
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	
		static String archive = "";
		public String archives()
		{
			ArrayList<String> month = new ArrayList<String>();
			ArrayList<String> year = new ArrayList<String>();
			
			
			try
			{
				Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
				Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
				Statement stmt =  con.createStatement();
				
				archive ="<archives>";
				
				String query = " Select distinct (monthname(post_date)),year(post_date) from article where year(post_date) in (Select distinct (year(post_date)) from article)";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{	
					month.add(rs.getString(1));
					year.add(rs.getString(2));
					
				}
				String months[] = new String[month.size()];
				months = month.toArray(months);
				
				String years[] = new String[year.size()];
				years = year.toArray(years);
				
				for(int j=0;j<month.size();j++)
				{
					System.out.println(months[j]+"-"+years[j]);
					try
					{
						Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
						Connection con1 = DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
						Statement stmt1 =  con1.createStatement();
						
						String query1 = " Select count(*) from article where monthname(post_date) = '"+months[j]+"' and year(post_date) = '"+years[j]+"'"; 
						
						ResultSet rs1 = stmt1.executeQuery(query1);
						while(rs1.next())
						{
							System.out.println(rs1.getString(1));
							
							archive = archive +"<node><month>"+months[j]+"</month><year>"+years[j]+"</year><count>"+rs1.getString(1)+"</count></node>";
						}
					}
					catch(Exception e)
					{
						System.out.print(e);
					}
				}
				archive = archive + "</archives>";
				return archive;
			}
			
			catch(Exception e)
			{
				return e.toString();
			}
			
			
		}
	
}
