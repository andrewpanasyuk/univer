<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lessons</title>

</head>
<body>
	All lessons:
	<br>
	<a href="${pageContext.servletContext.contextPath}/Lesson/add">add
		new Lesson</a>
	<br>
	<form
		action="${pageContext.servletContext.contextPath }/Lesson/date"
		method="post">
		<p>
			Show lessons on <input type="date" name="date"> <input
				type="submit" value="select">
		</p>
	</form>
	<form action="${pageContext.servletContext.contextPath }/Lessons/period" method="post">
		<p>
			Show schedule from <input type="date" name="dateFrom"> to <input
				type="date" name="dateTo"> <input type="submit"
				value="select">
		</p>
	</form>
	<table border="1">
		<tr>
			<th>Lesson name</th>
			<th>Date</th>
			<th>Time</th>
			<th>WeekDay</th>
			<th>Auditorium</th>
			<th>Teacher</th>
			<th>Group</th>
			<th>Update</th>
			<th>Remove</th>
		</tr>
		<c:forEach items="${lessons}" var="lesson">
			<tr>
				<td>${lesson.name}</td>
				<c:set var="dateLesson" value="${lesson.date}" />
				<td width="10%"><fmt:formatDate pattern="dd-MM-yyyy"
						value="${dateLesson}" /></td>
				<td width="10%"><fmt:formatDate pattern="hh:mm"
						value="${dateLesson}" /></td>
				<td>${lesson.weekDay}</td>
				<td>${lesson.auditorium}</td>
				<td>${lesson.teacher.lastName}</td>
				<td>${lesson.group.name}</td>
				<td><a
					href="${pageContext.servletContext.contextPath}/Lesson/update?id=${lesson.id}">Update</a></td>
				<td><a
					href="${pageContext.servletContext.contextPath}/RemoveLesson?id=${lesson.id}">Remove</a></td>

			</tr>
		</c:forEach>
	</table>
	<a href="${pageContext.servletContext.contextPath}/index.jsp"> <b>Home page</b></a><br>
</body>
</html>