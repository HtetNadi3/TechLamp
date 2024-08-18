<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post List</title>
</head>
<body class="d-flex flex-column vh-100">
  <div
    class="container-fluid flex-grow-1 d-flex justify-content-center align-items-center py-3">
    <c:choose>
      <c:when test="${empty postList}">
        <div class="col-md-4 text-center">
          <h3>No New Post.</h3>
          <a href="${pageContext.request.contextPath}/post/new"
            class="btn btn-primary mx-3">Add New Post</a>
        </div>
      </c:when>
      <c:when test="${not empty postList}">
        <div class="col-md-8">
          <h3 class="text-center">List of Posts</h3>
          <hr>
          <div class="row mt-3">
            <div class="col-md-4 d-flex justify-content-between">
              <a href="${pageContext.request.contextPath}/post/new"
                class="btn btn-primary">Add New Post</a>
            </div>
          </div>
          <br>
          <table class="table table-bordered table-hover"
            style="table-layout: fixed;">
            <thead>
              <tr>
                <th class="col-1">No.</th>
                <th class="col-2">Title</th>
                <th class="col-3">Content</th>
                <th class="col-1">Author</th>
                <th class="col-2">Posted Date</th>
                <th class="text-center col-2">Actions</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="post" items="${postList}" varStatus="loop">
                <tr
                  style="vertical-align: middle; max-width: 50px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                  <td>${loop.count}</td>
                  <td class="text-truncate"><a
                    href="${pageContext.request.contextPath}/post/detail?id=${post.id}">${post.title}</a></td>
                  <td class="text-truncate">${post.content}</td>
                  <td class="text-truncate">Leo</td>
                  <td>${post.createdAt}</td>
                  <td class="d-flex justify-content-center"><a
                    href="edit?id=${post.id}"
                    class="btn mx-3 btn-primary"><i
                      class="fa-solid fa-pen-to-square"></i></a>
                    <button type="button" data-bs-toggle="modal"
                      data-bs-target="#deleteModal"
                      onclick="addLink(${post.id})"
                      class="btn mx-3 btn-danger">
                      <i class="fa-solid fa-trash"></i>
                    </button></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </c:when>
    </c:choose>
  </div>
  <%@ include file="/jsp/common/footer.jsp"%>
  <div class="modal fade" id="deleteModal" tabindex="-1"
    aria-labelledby="deleteModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-sm">
      <div class="modal-content text-center text-danger">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="deleteModal">
            <i class="fa-solid fa-triangle-exclamation"></i>&nbsp;&nbsp;Delete
            Post?
          </h1>
          <button type="button" class="btn-close"
            data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <span>Are You Sure To Delete?</span>
        </div>
        <div class="modal-footer justify-content-center">
          <button type="button" class="btn btn-dark"
            data-bs-dismiss="modal">Close</button>
          <a id="deleteLink" class="btn btn-danger">Delete</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
