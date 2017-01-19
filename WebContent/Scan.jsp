<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.util.* ,java.util.ArrayList, java.util.List"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无主之地</title>
</head>
<body>
	<%
		List<TopicBean> topics = (List<TopicBean>)request.getAttribute("topics");
		for(int i=0;i<topics.size();i++)
		{
			out.println("<h2><a href=\"read.do?id="+topics.get(i).getId()+"\"+>"+topics.get(i).getTitle()+"</a><h2>");
		}
		
		int currentPage=(Integer)request.getAttribute("page");
		int maxPages=(Integer)request.getAttribute("maxPages");
		
		out.println("<a href=\"scan.do?page="+(currentPage>1?(currentPage-1):1)+"\">上一页</a>");
		out.println("<a href=\"scan.do?page="+(currentPage<maxPages?(currentPage+1):maxPages)+"\">下一页</a>");
		out.println("<br><br>");
	%>

	<a href="Write.jsp">发帖</a>
</body>
</html>