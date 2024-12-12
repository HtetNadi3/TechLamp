<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
prefix="fn"%> <%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>${type == 'edit' ? 'Edit Comment' : 'Add New Comment'}</title>
  </head>
  <body class="d-flex flex-column min-vh-100 bg-light">
    <div
      class="container flex-grow-1 d-flex align-items-center justify-content-center"
    >
      <div class="card shadow-lg w-100" style="max-width: 600px">
        <div class="card-header text-center bg-primary text-white">
          <h2 class="my-0">
            ${type == 'edit' ? 'Edit Comment' : 'Add New Comment'} for
            "${post.title}"
          </h2>
        </div>
        <div class="card-body">
          <form
            id="postForm"
            action="${pageContext.request.contextPath}/comment/${type == 'edit' ? 'update' : 'add'}"
            method="post"
          >
            <input type="hidden" name="postId" value="${post.id}" />
            <c:if test="${type == 'edit'}">
              <input type="hidden" name="id" value="${comment.id}" />
            </c:if>

            <div class="mb-3 align-items-center">
              <label class="fw-medium required" for="description"
                >Comment</label
              >
              <input
                type="text"
                value="${comment.description}"
                class="form-control"
                name="description"
                id="description"
                required
              />
            </div>

            <div class="text-center">
              <button type="submit" class="btn btn-primary mx-2">
                ${type == 'edit' ? 'Update' : 'Add'}
              </button>
              <a
                href="${pageContext.request.contextPath}/comment/list?postId=${post.id}"
                class="btn btn-dark mx-2"
                >Back</a
              >
            </div>
          </form>
        </div>
      </div>
    </div>
    <%@ include file="/jsp/common/footer.jsp"%>
  </body>
</html>
