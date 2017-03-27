<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Teacher</title>
</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath }/Teacher/add"
		method="post">
		New Teacher <br>
		<p>
			First name: <input type="text" name="first name" size="40">
		</p>
		<p>
			Last name: <input type="text" name="last name" size="40">
		</p>
		<input type="submit" value="Create">
	</form>
</body>
</html>