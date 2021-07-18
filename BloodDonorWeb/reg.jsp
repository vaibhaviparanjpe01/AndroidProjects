<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jspf"></jsp:include>
<h1>New Registration</h1>
<hr>
<form>
Name <input type="text" name="t1"/>
<br>
Email <input type="email" name="t2" required/>
<br>
Mobile <input type="text" name="t3" required pattern="[0-9]{10}" title="not a valid number"/>
<br>
city<select name="t4">
<option>Jabalpur</option>
<option>Bhopal</option>
<option>Katni</option>
<option>Indore</option>
</select>

<br>
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

</body>
<input type="submit" name="b2" value="click here to submit data"/>
</form>           <!-- form used data is to be sent to the server -->
<%  //scriptlet //java code //execute on server
if(request.getParameter("b2")!=null)
{
	try
	{
		Class.forName("org.postgresql.Driver");
		Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","deepika");
		Statement stmt=conn.createStatement();
		String sql="insert into reblood values('"+request.getParameter("t1")+"','"+request.getParameter("t2")+"','"+request.getParameter("t3")+"','"+request.getParameter("t4")+"','"+request.getParameter("t5")+"')";
		stmt.execute(sql);
		stmt.close();
		conn.close();
		out.println("Record saved successfully");
	}
	catch(Exception e)
	{
		out.println("Error :"+e.toString());
	}
}
%>
</body>
</html>