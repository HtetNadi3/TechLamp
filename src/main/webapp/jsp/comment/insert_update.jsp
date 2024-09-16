<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Commment ${type == 'edit' ? 'Edit ' : 'Add'}</title>
</head>
<body class="d-flex flex-column vh-100">
  <div
    class="container-fluid flex-grow-1 d-flex align-items-center justify-content-center">
    <div class="card">
      <div class="card-header text-center">
        <h2 class="my-0">${type == 'edit' ? 'Edit' : 'Add New'}
          Comment to ${post.title}</h2>
      </div>
      <div class="card-body">
        <form id="postForm"
          action="${pageContext.request.contextPath}/comment/${type == 'edit' ? 'update' : 'add'}"
          method="post">

            <input type="hidden" name="postId" value="${post.id}" />
          <c:if test="${type == 'edit'}">
            <input type="hidden" name="id" value="${comment.id}" />
          </c:if>

          <div class="mb-3 align-items-center">
            <label class="fw-medium required" for="description">Comment</label>
            <input type="text" value="${comment.description}"
              class="form-control" name="description" id="description"
              required>
          </div>

          <div class="text-center">
            <button type="submit" class="btn btn-primary mx-2">${type == 'edit' ? 'Update' : 'Add'}</button>
            <a
              href="${pageContext.request.contextPath}/comment/list?postId=${post.id}"
              class="btn btn-dark mx-2">Back</a>
          </div>

        </form>
      </div>
    </div>
  </div>
  <%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>