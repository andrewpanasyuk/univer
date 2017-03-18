<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lessons</title>
</head>
<body>
All lessons:
	<br>
	<table border="1">
		<tr>
			<th>Lesson name</th>
			<th>Date</th>
			<th>WeekDay</th>
			<th>Auditorium</th>
			<th>Teacher</th>
			<th>Group</th>
		</tr>
		<c:forEach items="${lessons}" var="lesson">
			<tr>
				<td>${lesson.name}</td>
				<c:set var="dateLesson" value="${lesson.date}" />
				<td><fmt:formatDate type="date" value="${dateLesson}" /></td>
				
				<td>${lesson.weekDay}</td>
				<td>${lesson.auditorium}</td>
				<td>${lesson.teacher.lastName}</td>
				<td>${lesson.group.name}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>