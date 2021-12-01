<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<%@page import ="Model.Bean.user"%>
<%@page import="Model.Bean.File"%>
<%@page import ="Model.Bean.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>
		<%
			user user = (user)request.getSession().getAttribute("User");
		%>
	<h2>Chuyển đổi file Word thành PDF</h2>
    <form action="UpFileController" method="post" enctype="multipart/form-data">
        Chọn file Word:
        <br />
        <input type="file" name="file" size="50"/>
        <br />
        <input type="submit" value="Chuyển" />
        <br/>
		<%
			String mess = (String)request.getAttribute("message");
			if (mess != null) {
				switch (mess) {
					case "Upload File Success":
						mess = "Tải file thành công";
					break;
					case "Extension Error":
						mess = "Lỗi định dạng file";
						break;
					case "Upload Error":
						mess = "Lỗi tải file";
						break;
				}
		%>
			<% 
				if("Tải file thành công".equals(mess))
				{
					%>
					<p ><%=mess%></p>
					<a href="DownFileController">Download</a>
					<% 
				}
				else{
					%>
					<p ><%=mess%></p>
					<% 
				}
			}
		%>
    </form>
</body>
</html>