<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
</head>
<body>
	<div class="container mt-5">
		<h1 class="text-center">Welcome to Our Blog System</h1>
		<div class="text-center mt-4">
			<a href="${pageContext.request.contextPath}/register"
				class="btn btn-primary">Register</a> <a
				href="${pageContext.request.contextPath}/login"
				class="btn btn-secondary">Login</a>
		</div>
	</div>
</body>
</html>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
