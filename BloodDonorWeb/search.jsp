<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<body>
<jsp:include page="header.jspf"></jsp:include>
<h2>Search</h2>
<hr>
<form>
city<select name="t4">
<option>Jabalpur</option>
<option>Bhopal</option>
<option>Katni</option>
<option>Indore</option>
</select>

Blood Group <select name="t5">
<option>A+</option>
<option>B+</option>
<option>AB+</option>
<option>O+</option>
<option>A-</option>
<option>B-</option>
<option>AB-</option>
<option>O-</option>
</select>
 

<input type="submit" name="b2" value="Search"/>

</form>

<%
if(request.getParameter("b2")!=null)
{
	try
	{
		Class.forName("org.postgresql.Driver");
		Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
		Statement stmt=conn.createStatement();
		String sql="select * from reblood where city='"+request.getParameter("t4")+"'and bg='"+request.getParameter("t5")+"'";
		ResultSet rs=stmt.executeQuery(sql);
		out.println("Name  \t Mobile  \t City  \tBlood Group  \t");
		out.println("<br>");
		while(rs.next())
		{
			out.println(rs.getString(1)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+"\n");
			out.println("<br>");
		}
	}
	catch(Exception e)
	{
		out.println("Error:"+e.toString());
	}
}

%>
</body>
</html>