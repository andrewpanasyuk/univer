<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Student</title>
</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath}/StudentUpdateServlet"
		method="post">
		<p>
			Student ID: <input type="hidden" name="id" value="${student.id}">${student.id}</p>
		<p>
			First name: <input type="text" name="first name"
				value="${student.firstName}">
		</p>
		<p>
			Last name: <input type="text" name="last name"
				value="${student.lastName}">
		</p>
		<p>
			Select group: <select name="group">
				<option value="0"></option>
				<c:forEach items="${groups}" var="group">
					<option value="${group.id}">${group.name}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input type="submit" value="update">
		</p>
	</form>

</body>
</html>