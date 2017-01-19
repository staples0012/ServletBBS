<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑帖子</title>
</head>
<body>
	<form action="write.do" method="post" >
		输入标题：
		<br><textarea rows="1" cols="80" name="title"></textarea><br>
		输入内容：
		<br><textarea rows="20" cols="80" name="content"></textarea><br>
		<br><input type="submit">
	</form>
</body>
</html>