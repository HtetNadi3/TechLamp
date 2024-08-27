<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Category ${type == 'edit' ? 'Edit ' : 'Registration'}</title>
</head>
<body class="d-flex flex-column vh-100">
    <div class="container-fluid flex-grow-1 d-flex align-items-center justify-content-center">
        <div class="card">
            <div class="card-header text-center">
                <h2 class="my-0">${type == 'edit' ? 'Edit ' : 'Add New '}Category</h2>
            </div>
            <div class="card-body">
                <form id="categoryForm"
                    action="${pageContext.request.contextPath}/category/${type == 'edit' ? 'update' : 'insert'}"
                    method="post">

                    <c:if test="${type == 'edit'}">
                        <input type="hidden" name="id" value="${category.id}" />
                    </c:if>

                    <div class="mb-3 align-items-center">
                        <label class="fw-medium required" for="category">Name</label>
                        <input type="text" value="${category.name}"
                            class="form-control" name="name" id="category"
                            required><span class="text-danger">${err}</span>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary mx-2">${type == 'edit' ? 'Update' : 'Add'}</button>
                        <a href="${pageContext.request.contextPath}/category/list"
                            class="btn btn-dark mx-2">Back</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>
