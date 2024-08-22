<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<div class="container mt-5">
	<div class="row justify-content-center">
		<div class="col-md-4">
			<h2>Login</h2>
			<form action="${pageContext.request.contextPath}/signin" method="post">
				<div class="mb-3">
					<label for="username" class="form-label">User Name</label> <input
						type="text" class="form-control" id="username" name="username"
						required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" id="password" name="password"
						required>
				</div>
				<c:if test="${not empty error}">
					<div class="alert alert-danger">${error}</div>
				</c:if>
				<button type="submit" class="btn btn-primary">Login</button>
				<a href="${pageContext.request.contextPath}/register" class="btn btn-link">Register</a>
			</form>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
