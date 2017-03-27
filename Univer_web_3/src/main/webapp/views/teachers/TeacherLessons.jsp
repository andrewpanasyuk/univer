<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.servletContext.contextPath }/Lessons/Teacher/date" method="post">
		<input type="hidden" name="id" value="${teacher.id}"> Schedule
		for <b> ${teacher.firstName} ${teacher.lastName} </b>
		<p>
			Show lessons on <input type="date" name="date"> <input
				type="submit" value="select">
		</p>
	</form>
	<form
		action="${pageContext.servletContext.contextPath }/Lessons/Teacher/period" method="post">
		<input type="hidden" name="id" value="${teacher.id}"> 
		<p>
			Show schedule from <input type="date" name="dateFrom"> to <input
				type="date" name="dateTo"> <input type="submit"
				value="select">
		</p>
	</form>

	<table border="1">
		<tr>
			<th>Subject</th>
			<th>Date</th>
			<th>Time</th>
			<th>WeekDay</th>
			<th>Auditorium</th>
			<th>Teacher</th>
			<th>Group</th>
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

			</tr>
		</c:forEach>
	</table>
	<a href="${pageContext.servletContext.contextPath}/index.jsp"> <b>Home page</b></a><br>
</body>
</html>