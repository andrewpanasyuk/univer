<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	All students from Group ${group.name}:
	<br>
	<table border="1">
		<tr>
			<th>Student ID</th>
			<th>First name</th>
			<th>Last name</th>
		</tr>
		<c:forEach items="${students}" var="student">
			<tr>
				<td>${student.id}</td>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>

			</tr>
		</c:forEach>
	</table>
<a href="${pageContext.servletContext.contextPath}/index.jsp"> <b>Home page</b></a><br>
</body>
</html>