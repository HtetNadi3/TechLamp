<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Post Detail</title>
</head>
<body>
    <%@ include file="/jsp/common/navbar.jsp"%>
    <div class="container my-5">
        <div class="card shadow-sm p-4">
            <!-- Post Title -->
            <h1 class="card-title display-5 mb-3">${post.title}</h1>

            <!-- Post Metadata -->
            <div class="text-muted mb-4">
                <i class="fas fa-user me-2"></i><span>${post.author}</span> 
                <span class="mx-2">&middot;</span> 
                <i class="fas fa-calendar-alt me-2"></i><span>${post.createdAt}</span>
            </div>

            <!-- Post Content -->
            <div class="post-content mb-4">${post.content}</div>

            <!-- Comments & Icon Bar -->
            <div class="d-flex align-items-center justify-content-between">
                <a href="${pageContext.request.contextPath}/comment/list?postId=${post.id}" class="text-muted">
                    <i class="fas fa-comment me-2"></i>${commentCounts[post.id]} Comments
                </a>
                <div class="text-center">
                    <!-- Show Edit/Delete Buttons for Post Owner -->
                    <c:if test="${post.createdUserId == loginUserId}">
                        <a href="edit?id=${post.id}" class="btn btn-outline-primary me-2">
                            <i class="fas fa-edit"></i> Edit
                        </a>
                        <button type="button" data-bs-toggle="modal" data-bs-target="#deleteModal" 
                            onclick="addLink(${post.id})" class="btn btn-danger">
                            <i class="fas fa-trash"></i> Delete
                        </button>
                    </c:if>
                </div>
            </div>

            <!-- Back Button -->
            <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/post/list" class="btn btn-dark">
                    <i class="fas fa-arrow-left me-2"></i> Back
                </a>
            </div>
        </div>
    </div>

    <!-- Delete Confirmation Modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteModal">
                        <i class="fas fa-exclamation-triangle me-2"></i> Delete Post?
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body text-center">
                    Are you sure you want to delete this post?
                </div>
                <div class="modal-footer justify-content-center">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <a id="deleteLink" class="btn btn-danger">Delete</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
<title>Post Detail</title>


</head>
<body>
	<%@ include file="/jsp/common/navbar.jsp"%>
	<div class="container-fluid">
		<div class="card ql-editor p-4">
			<h1 class="card-title">${post.title}</h1>
			<div class="post-metadata">
				<i class="fas fa-user"></i>${post.author} &middot; <i
					class="fas fa-calendar-alt"></i>${post.createdAt}
			</div>

			<div class="post-content">${post.content}</div>

			<div class="icon-bar">
				<span class="text-muted me-3"><i class="fa-solid fa-heart"></i>
					23</span> <a
					href="${pageContext.request.contextPath}/comment/list?postId=${post.id}">
					<span class="text-muted me-3"><i class="fas fa-comment"></i>
						${commentCounts[post.id]}</span>
				</a>


			</div>

			<div class="text-center mt-4">
				<c:if test="${post.createdUserId == loginUserId}">
					<a href="edit?id=${post.id}" class="text-muted me-2">Edit</a>
					<button type="button" data-bs-toggle="modal"
						data-bs-target="#deleteModal" onclick="addLink(${post.id})"
						class="btn mx-3 btn-danger">
						<i class="fa-solid fa-trash"></i>
					</button>
				</c:if>
				<a href="${pageContext.request.contextPath}/post/list"
					class="btn btn-dark">Back</a>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deleteModal" tabindex="-1"
		aria-labelledby="deleteModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-sm">
			<div class="modal-content text-center text-danger">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="deleteModal">
						<i class="fa-solid fa-triangle-exclamation"></i>&nbsp;&nbsp;Delete
						Post?
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<span>Are You Sure To Delete?</span>
				</div>
				<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
					<a id="deleteLink" class="btn btn-danger">Delete</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
