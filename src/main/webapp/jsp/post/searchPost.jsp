<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="post" items="${posts}">
    <h2>${post.title}</h2>
    <!-- Display other post details -->
</c:forEach>
	
</body>
</html>
<%@ include file="/jsp/common/footer.jsp"%>