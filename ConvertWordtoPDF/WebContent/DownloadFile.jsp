<%@page import="Model.Bean.File"%>
<%@page import ="java.util.*"%>
<%@page import ="Model.Bean.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Download</title>
</head>
<body>
	 <%
		   		ArrayList<File> l = (ArrayList<File>)request.getAttribute("UserFiles");
		   		if (l == null) {		
		   %>
		   		<h2>File đang xử lí</h2>
		   		<a href="DownFileController">Xem các file của bạn</a>
		   <%
		   		} else {
		   			%>
		   			<a href="DownFileController">Xem các file của bạn</a>
		   			<% 
		   			%>
		   			<table border="1" align="center" width=100%>   
		   <tr>
		      <th>Tên Tệp</th>
		      <th>Ngày </th>
		      <th>Trạng Thái</th>
		   </tr>
		   <tr>
		   <%
		   			for(File f: l) {	   			
		   %>
		   			<tr>
			   			<td><%=f.GetFileName()%></td>
			   			<td><%=f.GetDateTime()%></td>
		   				<td><a href="DownloadFileController?id=<%=f.Getid()%>">Download</a></td><br>
		   			</tr>
		   <%
		   			}
		   		}
		   %>
		   </table>
</body>
</html>