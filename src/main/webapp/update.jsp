<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update">
姓名:<input name="name" value="${user.name}"/><br>
密码:<input name="password"  value="${user.password }"/><br>
金额:<input name="money" value="${user.money}"/><br>
<input type="hidden" name="id" value="${user.id}">
<input type="submit" value="提交">
<a href="show">返回</a>
</form>
</body>
</html>