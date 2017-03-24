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
All teachers:<br>
<a href="${pageContext.servletContext.contextPath}/views/teachers/TeacherCreate.jsp">Create new Teacher</a>
	<br>
	<table border="1">
		<tr>
			<th>Teacher ID</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Schedule</th>
			<th>Update</th>
			<th>Remove</th>
		</tr>
		<c:forEach items="${teachers}" var="teacher">
			<tr>
				<td>${teacher.id}</td>
				<td>${teacher.firstName}</td>
				<td>${teacher.lastName}</td>
				<td><a href="${pageContext.servletContext.contextPath}/ScheduleForTeacherServlet?id=${teacher.id}">Schedule</a></td>
				<td><a href="${pageContext.servletContext.contextPath}/TeacherUpdateServlet?id=${teacher.id}">Update</a></td>
				<td><a href="${pageContext.servletContext.contextPath}/TeacherRemoveServlet?id=${teacher.id}">Remove</a></td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>