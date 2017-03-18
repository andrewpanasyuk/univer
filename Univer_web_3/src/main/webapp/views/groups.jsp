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
	<table border="1">
		<tr>
			<th>Group ID</th>
			<th>Group name</th>
		</tr>
		<c:forEach items="${groups}" var="group">
			<tr>
				<td>${group.id}</td>
				<td>${group.name}</td>
			</tr>
		</c:forEach>
	</table>
</body>


</html>