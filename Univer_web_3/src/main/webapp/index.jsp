<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<a href="${pageContext.servletContext.contextPath}/Groups">Show
		all groups</a>
	<br>
	<a href="${pageContext.servletContext.contextPath}/Students">Show
		all students</a>
	<br>
	<a href="${pageContext.servletContext.contextPath}/Teachers">Show
		all teachers</a>
	<br>
	<a href="${pageContext.servletContext.contextPath}/Lessons">Show
		all lessons</a>
	<br>
	<hr>
 <a href="${pageContext.servletContext.contextPath}/views/groups/GroupCreate.jsp"> Create new Group</a><br>
	<a href="${pageContext.servletContext.contextPath}/Student/add">Create Student</a><br>
	<a href="${pageContext.servletContext.contextPath}/views/teachers/TeacherCreate.jsp">Create new Teacher</a><br>
	<a href="${pageContext.servletContext.contextPath}/Lesson/add">add
		new Lesson</a><br>
  <hr>
</body>
</html>