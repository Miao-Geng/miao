<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 上传文件，提交方式一定要是post，并且指定编码格式为文件上传 enctype="multipart/form-data" -->
<form action="upload" method="post" enctype="multipart/form-data">
	请选择上传头像<input type="file" name="file"/>
			<input type="submit" value="提交"/>
</form>
</body>
</html>