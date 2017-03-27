<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Lesson</title>

</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath}/Lesson/update"
		method="post">
		<h2>Update information about Lesson</h2>
		<p>
			<input type="hidden" name="id" value="${lesson.id}">
		</p>
		<p>
			Lesson: <input type="text" name="subject" value="${lesson.name}">
		</p>
		<p>
			Auditorium: <input type="number" size="3" name="auditorium" min="1"
				max="10" value="1">
		</p>
		<p>
			Teacher: <select name="teacher">
				<option value="${lesson.teacher.id}">${lesson.teacher.firstName}
					${lesson.teacher.lastName}</option>
				<c:forEach items="${teachers}" var="teacher">
					<option value="${teacher.id}">${teacher.firstName}
						${teacher.lastName}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			Group: <select name="group">
				<option value="${lesson.group.id}">${lesson.group.name}</option>
				<c:forEach items="${groups}" var="group">
					<option value="${group.id}">${group.name}</option>
				</c:forEach>
			</select>
		</p>
		<input type="submit" value="update lesson">
	</form>
	<form
		action="${pageContext.servletContext.contextPath}/Lesson/updateTime"
		method="post">
		<p>
			<input type="hidden" name="id" value="${lesson.id}">
		</p>
		<h2>Update Data & Time</h2>
		<c:set var="dateLesson" value="${lesson.date}" />
		<p>
			Current DATA:
			<fmt:formatDate type="date" value="${dateLesson}" />
			New Data: <input type="date" name="date">
		</p>
		<p>
			Current TIME:
			<fmt:formatDate pattern="hh:mm" value="${dateLesson}" />
			New TIME: <input type="time" name="time">
		</p>
		<input type="submit" value="update time and data">
	</form>

</body>
</html>