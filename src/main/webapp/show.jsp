<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Insert title here</title>
</head>
<body>
<a href="add.jsp">添加</a>   <a href="exit">退出</a>
<table id="tab">
<tr>
<td>ID</td>
<td>姓名</td>
<td>密码</td>
<td>余额</td>
<td>操作</td>
</tr>
<c:forEach items="${list}" var="i">
<tr>
	<td>${i.id}</td>
	<td>${i.name}</td>
	<td>${i.password}</td>
	<td>${i.money}</td>
	<td>
	<a href="byId?id=${i.id}">✎</a>
	<a href="delete?id=${i.id}">X</a>
	</td>
</tr>
</c:forEach>
</table>
</body>
</html>