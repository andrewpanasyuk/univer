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
	<a href="${pageContext.servletContext.contextPath}/GroupShowServlet">Show
		all groups</a>
	<br>
	<a href="${pageContext.servletContext.contextPath}/StudentShowServlet">Show
		all students</a>
	<br>
	<a href="${pageContext.servletContext.contextPath}/TeacherShowController">Show
		all teachers</a>
	<br>
	<a href="${pageContext.servletContext.contextPath}/ScheduleShowServlet">Show
		all lessons</a>
	<br>
</body>
</html>