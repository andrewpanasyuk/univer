<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath}/GroupUpdateServlet"
		method="post">
		<p>
			Group ID: <input type="hidden" name="id" value="${group.id }">
			${group.id }
		</p>
		<p>
			Group name: <input type="text" name="name" value="${group.name}">
		</p>
		<p>
			<input type="submit" value="Update">
		</p>

	</form>

</body>
</html>