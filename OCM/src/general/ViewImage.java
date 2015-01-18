package general;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewImage
 */
public class ViewImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection  con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");      
			Statement st1=con.createStatement();
			ResultSet rs1 = st1.executeQuery("select img from img where id='"+request.getParameter("id").toString()+"'");
			String imgLen="";
			if(rs1.next()){
			imgLen = rs1.getString(1);
			int len = imgLen.length();
			byte [] rb = new byte[len];
			InputStream readImg = rs1.getBinaryStream(1);
			int index=readImg.read(rb, 0, len); 
			System.out.println("index"+index);
			st1.close();
			response.reset();
			response.setContentType("image/jpg");
			response.getOutputStream().write(rb,0,len);
			response.getOutputStream().flush(); 
			} 
			
			} catch (Exception e){
			e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
