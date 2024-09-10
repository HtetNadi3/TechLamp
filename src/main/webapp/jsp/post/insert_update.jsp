<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Post ${type == 'edit' ? 'Edit ' : 'Registration'}</title>
<link
	href="https://cdn.jsdelivr.net/npm/quill@2.0.2/dist/quill.snow.css"
	rel="stylesheet">
<style>
.card {
	width: 100%;
	max-width: 800px;
	display: flex;
	flex-direction: column;
}

#editor-container {
	border: 1px solid #ccc;
	border-radius: 4px;
	min-height: 200px;
	resize: horizontal;
	overflow: auto;
	flex-grow: 1;
}
</style>
</head>
<body class="d-flex flex-column vh-100">
	<div
		class="container-fluid flex-grow-1 d-flex align-items-center justify-content-center">
		<div class="card">
			<div class="card-header text-center">
				<h2 class="my-0">${type == 'edit' ? 'Edit ' : 'Add New '}Post</h2>
			</div>
			<div class="card-body">
				<form id="postForm"
					action="${pageContext.request.contextPath}/post/${type == 'edit' ? 'update' : 'insert'}"
					method="post">

					<c:if test="${type == 'edit'}">
						<input type="hidden" name="id" value="${post.id}" />
					</c:if>

					<div class="mb-3 align-items-center">
						<label class="fw-medium required" for="title">Title</label> <input
							type="text" value="${post.title}" class="form-control"
							name="title" id="title" required><span
							class="text-danger">${err}</span>
					</div>

					<div class="mb-3 align-items-center">
						<label class="fw-medium required" for="categories">Categories</label>
						<select name="categoryId" id="category">
							<c:forEach var="category" items="${categories}">
								<option value="${category.id}"
									<c:if test="${post.categoryId == category.id}">selected</c:if>>
									${category.name}</option>
							</c:forEach>
						</select>
					</div>


					<div class="mb-3 align-items-center">
						<label class="fw-medium required" for="content">Content</label>
						<div id="editor-container"></div>
						<textarea id="content" class="formcontrol" name="content"
							style="display: none">${post.content}</textarea>
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary mx-2">${type == 'edit' ? 'Update' : 'Add'}</button>
						<a href="${pageContext.request.contextPath}/post/list"
							class="btn btn-dark mx-2">Back</a>
					</div>

				</form>
			</div>
		</div>
	</div>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>