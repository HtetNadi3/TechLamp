<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<div class="container">
    <h2>Register</h2>
    <form id="registerForm" action="${pageContext.request.contextPath}/signup" method="post" onsubmit="return validatePasswords();">
        <div class="form-group">
            <label for="username">User Name:</label>
            <input type="text" id="username" name="username" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
        </div>
        <p id="errorMessage" style="color: red; display: none;">Passwords do not match!</p>
        <button type="submit" class="btn btn-primary">Register</button>
        <div>
            <c:if test="${requestScope.error != null}">
                <p style="color: red;">${requestScope.error}</p>
            </c:if>
        </div>
    </form>
    <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
</div>
<script>
    function validatePasswords() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const errorMessage = document.getElementById('errorMessage');
        if (password !== confirmPassword) {
            errorMessage.style.display = 'block';
            return false;
        }
        errorMessage.style.display = 'none';
        return true;
    }
    document.getElementById('confirmPassword').addEventListener('input', function() {
        validatePasswords();
    });
</script>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
