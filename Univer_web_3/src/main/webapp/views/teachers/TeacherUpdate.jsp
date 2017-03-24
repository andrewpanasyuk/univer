<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher Update</title>
</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath}/TeacherUpdateServlet"
		method="post">
		<p>
			Teacher ID: <input type="hidden" name="id" value="${teacher.id}"> ${teacher.id}</p>
		<p>
			First name: <input type="text" name="first name"
				value="${teacher.firstName}">
		</p>
		<p>
			Last name: <input type="text" name="last name"
				value="${teacher.lastName}">
		</p>
		<p> <input type="submit" value="update" > </p>
	</form>

</body>
</html>