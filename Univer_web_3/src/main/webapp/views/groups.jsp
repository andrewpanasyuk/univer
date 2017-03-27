<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Groups</title>
</head>
<body>
	All groups:	<br>
	<a href="${pageContext.servletContext.contextPath}/views/groups/GroupCreate.jsp"> Create new Group</a><br>
	<table border="1">
		<tr>
			<th>Group ID</th>
			<th>Group name</th>
			<th>Schedule</th>
			<th>Update</th>
			<th>Remove</th>
		</tr>
		<c:forEach items="${groups}" var="group">
			<tr>
				<td>${group.id}</td>
				<td><a href="${pageContext.servletContext.contextPath}/GroupSelectStudents?id=${group.id}">${group.name}</a></td>
				<td><a href="${pageContext.servletContext.contextPath}/Lessons/group?id=${group.id}">Schedule</a></td>
				
				<td><a href="${pageContext.servletContext.contextPath}/Group/update?id=${group.id}">Update</a></td>
				<td><a href="${pageContext.servletContext.contextPath}/GroupRemove?id=${group.id}">Remove</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="${pageContext.servletContext.contextPath}/index.jsp"> <b>Home page</b></a><br>
	
</body>


</html>