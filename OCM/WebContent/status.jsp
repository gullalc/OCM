<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.sql.*, javax.xml.parsers.*,javax.xml.transform.*,javax.xml.transform.dom.DOMSource, javax.xml.transform.stream.StreamResult,org.w3c.dom.*,org.xml.sax.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

</script>
<title>Update Status</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<center>
	<div id="upstatus">
		<form method="post">
			<table id="statustab">
				<tr>
					<th colspan=3>Update Status<br>
					<br></th>
				</tr>
				<tr>
					<td class="col1">
						<%out.println("Status"); %>
					</td>
					<td class="col2"><textarea name="status1" id="status1" rows=5	cols=100></textarea></td>
					<td class="col3"><span id="stat"></span></td>
				</tr>
			</table>
			<div id="but5">
				<input type="Submit" id="post" value="Post" onclick="" /> <input
					type="reset" value="reset" />
			</div>
		</form>
		<%
		String id = (String) session.getAttribute("id");
		
		 final String status = (String) request.getParameter("status1");
		  if (id != null && status != null) {
			try
			{
			Connection con;
			String profile="";
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			Statement st=con.createStatement();
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
		    NodeList lis=doc.getElementsByTagName("status");
		    
		    for(int i=0;i<lis.getLength();i++)
		    {
		    	if(lis.item(i).getNodeType()==1)
		    	{
		    		
		    		lis.item(i).setTextContent(status);
		    		
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
		    out.println("Status updated successfully");
		    st.executeUpdate("UPDATE reguser SET profile='"+xmlString+"' where id="+id);
		    //out.println("<script type='type/javascript'>window.location.href='http://localhost:8080/OCM/Profile?id="+id+"'</script>");
			}
			catch(Exception e)
			{
				out.println(e);
			}
			
		}
		
		
		%>
	</div>
</center>
</body>
</html>