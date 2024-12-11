<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${type == 'edit' ? 'Edit Post' : 'Add New Post'}</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">
	<div class="container flex-grow-1 d-flex align-items-center justify-content-center">
		<div class="card shadow-lg w-100" style="max-width: 800px;">
			<div class="card-header text-center bg-primary text-white">
				<h2 class="my-0">${type == 'edit' ? 'Edit Post' : 'Add New Post'}</h2>
			</div>
			<div class="card-body">
				<form id="postForm" action="${pageContext.request.contextPath}/post/${type == 'edit' ? 'update' : 'insert'}" method="post">
					
					<c:if test="${type == 'edit'}">
						<input type="hidden" name="id" value="${post.id}" />
					</c:if>

					<div class="mb-3">
						<label for="title" class="form-label fw-bold">Title</label>
						<input type="text" value="${post.title}" class="form-control" name="title" id="title" placeholder="Enter post title" required>
						<c:if test="${err}">
							<small class="text-danger">${err}</small>
						</c:if>
					</div>

					<div class="mb-3">
						<label for="categories" class="form-label fw-bold">Category</label>
						<select name="categoryId" class="form-select" required>
							<option value="" disabled ${post.categoryId == null ? 'selected' : ''}>Select Category</option>
							<c:forEach var="category" items="${categories}">
								<option value="${category.id}" ${post.categoryId == category.id ? 'selected' : ''}>${category.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="mb-3">
						<label for="content" class="form-label fw-bold">Content</label>
						<div id="editor-container" class="border rounded"></div>
						<textarea id="content" name="content" class="form-control d-none">${post.content}</textarea>
					</div>

					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary px-4 me-2">${type == 'edit' ? 'Update' : 'Add'}</button>
						<a href="${pageContext.request.contextPath}/post/list" class="btn btn-outline-secondary px-4 ms-2">Back</a>
					</div>

				</form>
			</div>
		</div>
	</div>


</body>
</html>
