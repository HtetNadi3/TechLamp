<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ include file="/jsp/common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Comment Section</title>
    <link
      href="${pageContext.request.contextPath}/resources/css/Comment.css"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body class="d-flex flex-column vh-100 bg-light text-dark">
    <%@ include file="/jsp/common/navbar.jsp" %>

    <div class="container my-5">
      <!-- Post Title -->
      <h3 class="text-center text-primary">${post.title}</h3>
      <hr class="my-4" />

      <!-- Back to Post Button -->
      <div class="text-center mb-4">
        <a
          href="${pageContext.request.contextPath}/post/list"
          class="btn btn-outline-primary"
          >Back To Posts</a
        >
      </div>

      <!-- Comment Section -->
      <c:choose>
        <c:when test="${empty comments}">
          <!-- No Comments Message -->
          <div class="alert alert-warning text-center">
            <h4>No Comments Yet</h4>
            <a
              href="${pageContext.request.contextPath}/comment/new?postId=${post.id}"
              class="btn btn-primary mt-3"
              >Add New Comment</a
            >
          </div>
        </c:when>

        <c:when test="${not empty comments}">
          <!-- Comment Actions -->
          <div class="d-flex justify-content-between align-items-center mb-3">
            <h5>Comments</h5>
            <a
              href="${pageContext.request.contextPath}/comment/new?postId=${post.id}"
              class="btn btn-primary"
              >Add New Comment</a
            >
          </div>

          <!-- Comment List -->
          <div class="list-group">
            <c:forEach var="comment" items="${comments}">
              <div
                class="list-group-item d-flex justify-content-between align-items-start"
              >
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
                    <a
                      href="edit?id=${comment.id}"
                      class="btn btn-sm btn-outline-secondary me-2"
                      >Edit</a
                    >
                    <button
                      type="button"
                      class="btn btn-sm btn-danger"
                      data-bs-toggle="modal"
                      data-bs-target="#deleteModal"
                      onclick="addLink(${comment.id})"
                    >
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
    <div
      class="modal fade"
      id="deleteModal"
      tabindex="-1"
      aria-labelledby="deleteModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title" id="deleteModalLabel">Delete Comment</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete this comment?
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Cancel
            </button>
            <a id="deleteLink" class="btn btn-danger">Delete</a>
          </div>
        </div>
      </div>
    </div>

    <script>
      // This function will set the delete link dynamically in the modal
      function addLink(commentId) {
        document.getElementById("deleteLink").href =
          "${pageContext.request.contextPath}/comment/delete?id=" + commentId;
      }
    </script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <%@ include file="/jsp/common/footer.jsp" %>
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Comment</title>
    <link
      href="${pageContext.request.contextPath}/resources/css/Comment.css"
      rel="stylesheet"
    />
    <style>
      /* Glassmorphism styling for comment section */
      .comment-section {
        ackground-color: #ffffff;
        border-radius: 16px;
        padding: 20px;
        backdrop-filter: blur(10px);
        box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
        border: 1px solid rgba(255, 255, 255, 0.3);
      }

      .comment {
        background: rgba(255, 255, 255, 0.15);
        border-radius: 12px;
        padding: 15px;
        backdrop-filter: blur(8px);
        /*box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
		/*border: 1px solid rgba(255, 255, 255, 0.3);*/
      }

      .comment .comment-content {
        color: #fff;
      }

      .comment .fw-bold {
        color: #fff;
      }

      .comment .text-muted {
        color: rgba(255, 255, 255, 0.7);
      }

      /* Modal glassmorphism */
      .modal-content {
        background: rgba(255, 255, 255, 0.2);
        backdrop-filter: blur(10px);
        border-radius: 16px;
        box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
        border: 1px solid rgba(255, 255, 255, 0.3);
      }

      .modal-title {
        color: #fff;
      }

      .modal-body span {
        color: #fff;
      }

      /* General styles */
      body {
        background: linear-gradient(
          135deg,
          rgba(58, 58, 158, 0.9),
          rgba(136, 136, 206, 0.5)
        );
        color: white;
      }
    </style>
  </head>
  <body class="d-flex flex-column vh-100">
    <%@ include file="/jsp/common/navbar.jsp"%>

    <div
      class="container-fluid flex-grow-1 d-flex justify-content-center align-items-center py-3"
    >
      <div class="col-md-8">
        <h3 class="text-center">${post.title}</h3>

        <hr style="border-color: #555" />
        <div class="row mt-3">
          <div class="col-md-4 d-flex justify-content-between">
            <a
              href="${pageContext.request.contextPath}/post/list"
              class="btn btn-outline-primary mx-3 text-white"
              >Back To Post</a
            >
          </div>
        </div>
        <br />
        <c:choose>
          <c:when test="${empty comments}">
            <div class="row">
              <div class="col-md-3"></div>
              <div class="col-md-6 text-center">
                <h3>No New Comment.</h3>
                <a
                  href="${pageContext.request.contextPath}/comment/new?postId=${post.id}"
                  class="btn btn-outline-primary mx-3 text-white"
                  >Add New Comment</a
                >
              </div>
              <div class="col-md-3"></div>
            </div>
          </c:when>
          <c:when test="${not empty comments}">
            <div class="comment-section p-3">
              <div class="row">
                <div class="col-md-6">
                  <a
                    href="${pageContext.request.contextPath}/comment/new?postId=${post.id}"
                    class="btn btn-outline-primary mb-3 text-white"
                    >Add New Comment</a
                  >
                </div>
              </div>

              <c:forEach var="comment" items="${comments}">
                <div class="comment d-flex align-items-start mb-3">
                  <i
                    class="fas fa-user-circle fa-2x pe-2"
                    style="color: #fff"
                  ></i>
                  <div class="comment-content ms-3 flex-grow-1">
                    <div class="d-flex justify-content-between">
                      <div>
                        <span class="fw-bold"
                          >${comment.commentCreatedUserName}</span
                        >
                        <span class="text-muted ms-2"
                          >${comment.createdAt}</span
                        >
                      </div>
                      <div class="comment-actions">
                        <c:if test="${comment.createdUserId == loggedInUserId}">
                          <a
                            href="edit?id=${comment.id}"
                            class="text-muted me-2"
                            >Edit</a
                          >
                          <button
                            type="button"
                            data-bs-toggle="modal"
                            data-bs-target="#deleteModal"
                            onclick="addLink(${comment.id})"
                            class="btn mx-3 btn-danger"
                          >
                            <i class="fa-solid fa-trash"></i>
                          </button>
                        </c:if>
                      </div>
                    </div>
                    <p class="text-muted mt-2">${comment.description}</p>
                  </div>
                </div>
              </c:forEach>
            </div>
          </c:when>
        </c:choose>
      </div>
    </div>
    <div
      class="modal fade"
      id="deleteModal"
      tabindex="-1"
      aria-labelledby="deleteModal"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-sm">
        <div class="modal-content text-center text-danger">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="deleteModal">
              <i class="fa-solid fa-triangle-exclamation"></i>&nbsp;&nbsp;Delete
              Comment?
            </h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <span>Are You Sure To Delete?</span>
          </div>
          <div class="modal-footer justify-content-center">
            <button type="button" class="btn btn-dark" data-bs-dismiss="modal">
              Close
            </button>
            <a id="deleteLink" class="btn btn-danger">Delete</a>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
