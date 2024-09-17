<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
<link href="${pageContext.request.contextPath}/resources/css/welcomeStyle.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div class="welcome">
		    <h1 class="typing-container" id="blog-title"></h1>

		
	</div>
	<div class="logo">
			<a href="${pageContext.request.contextPath}/register"> <span></span>
				<span></span> <span></span> <span></span> Register
			</a> <a href="${pageContext.request.contextPath}/login"> <span></span>
				<span></span> <span></span> <span></span> Login
			</a>
		</div>
	<div id="particles-js"></div>
	<script src="${pageContext.request.contextPath}/resources/js/particle.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</body>
</html>
<%@ include file="/jsp/common/footer.jsp"%>

