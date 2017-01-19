<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>看帖</title>
</head>
<body>
	<%
		TopicBean topic=(TopicBean)request.getAttribute("topic");
	%>

	<h1><%= topic.getTitle() %></h1>
	<h2><%= topic.getContent() %></h2>
</body>
</html>