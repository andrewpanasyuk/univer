<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lessons</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

</head>
<body>
	<p>hi!!</p>
	<form
		action="${pageContext.servletContext.contextPath }/ScheduleAddLesson"
		method="post">
		<p>
			Subject: <input type="text" name="subject">
		</p>
		<p>
			Date: <input type="text" id="datepicker" name="date">
		</p>
		<p>
			Time: <input type="time" name="time" value="09:00" min="08:00"
				max="16:00">
		</p>
		<p>
			Auditorium: <input type="number" size="3" name="auditorium" min="1"
				max="10" value="1">
		</p>
		<p>
			Teacher: <select name="teacher">
				<option value="0"></option>
				<c:forEach items="${teachers}" var="teacher">
					<option value="${teacher.id}">${teacher.firstName}
						${teacher.lastName}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			Group: <select name="group">
				<option value="0"></option>
				<c:forEach items="${groups}" var="group">
					<option value="${group.id}">${group.name}</option>
				</c:forEach>
			</select>
		</p>

		<input type="submit" value="add lesson">

	</form>

</body>
</html>