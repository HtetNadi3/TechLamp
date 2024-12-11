<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="/jsp/common/header.jsp" %>
      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Comment Section</title>
        <link href="${pageContext.request.contextPath}/resources/css/Comment.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
      </head>

      <body class="d-flex flex-column vh-100 bg-light text-dark">
        <%@ include file="/jsp/common/navbar.jsp" %>

          <div class="container my-5">
            <!-- Post Title -->
            <h3 class="text-center text-primary">${post.title}</h3>
            <hr class="my-4" />

            <!-- Back to Post Button -->
            <div class="text-center mb-4">
              <a href="${pageContext.request.contextPath}/post/list" class="btn btn-outline-primary">Back To Posts</a>
            </div>

            <!-- Comment Section -->
            <c:choose>
              <c:when test="${empty comments}">
                <!-- No Comments Message -->
                <div class="alert alert-warning text-center">
                  <h4>No Comments Yet</h4>
                  <a href="${pageContext.request.contextPath}/comment/new?postId=${post.id}"
                    class="btn btn-primary mt-3">Add New Comment</a>
                </div>
              </c:when>

              <c:when test="${not empty comments}">
                <!-- Comment Actions -->
                <div class="d-flex justify-content-between align-items-center mb-3">
                  <h5>Comments</h5>
                  <a href="${pageContext.request.contextPath}/comment/new?postId=${post.id}" class="btn btn-primary">Add
                    New Comment</a>
                </div>

                <!-- Comment List -->
                <div class="list-group">
                  <c:forEach var="comment" items="${comments}">
                    <div class="list-group-item d-flex justify-content-between align-items-start">
                      <div class="ms-2 me-auto">
                        <!-- Comment Author and Date -->
                        <div class="fw-bold">${comment.commentCreatedUserName}</div>
                        <small class="text-muted">${comment.createdAt}</small>
                        <!-- Comment Description -->
                        <p class="mt-2 mb-1">${comment.description}</p>
                      </div>

                      <!-- Edit and Delete Buttons -->
                      <div class="d-flex align-items-center">
                        <c:if test="${comment.createdUserId == loggedInUserId}">
                          <a href="edit?id=${comment.id}" class="btn btn-sm btn-outline-secondary me-2">Edit</a>
                          <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal"
                            data-bs-target="#deleteModal" onclick="addLink(${comment.id})">
                            <i class="fas fa-trash-alt"></i> Delete
                          </button>
                        </c:if>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </c:when>
            </c:choose>
          </div>

          <!-- Delete Modal -->
          <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                  <h5 class="modal-title" id="deleteModalLabel">Delete Comment</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  Are you sure you want to delete this comment?
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                  <a id="deleteLink" class="btn btn-danger">Delete</a>
                </div>
              </div>
            </div>
          </div>

          <script>
            // This function will set the delete link dynamically in the modal
            function addLink(commentId) {
              document.getElementById('deleteLink').href = '${pageContext.request.contextPath}/comment/delete?id=' + commentId;
            }
          </script>

          <!-- Bootstrap JS -->
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

          <%@ include file="/jsp/common/footer.jsp" %>
      </body>

      </html>