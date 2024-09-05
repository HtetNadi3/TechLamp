<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comment</title>
</head>
<body class="d-flex flex-column vh-100">
  <div
    class="container-fluid flex-grow-1 d-flex justify-content-center align-items-center py-3">

    <div class="col-md-8">
      <h3 class="text-center">Comment of ${post.title}</h3>
      <hr>
      <div class="row mt-3">
        <div class="col-md-4 d-flex justify-content-between">
          <a href="${pageContext.request.contextPath}/post/list"
            class="btn btn-primary mx-3">Back To Post</a>
        </div>
      </div>
      <br>
      <c:choose>
        <c:when test="${empty comment}">
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6 text-center">
              <h3>No New Comment.</h3>
              <a
                href="${pageContext.request.contextPath}/comment/new?postId=${post.id}"
                class="btn btn-primary mx-3">Add New Comment</a>
            </div>
            <div class="col-md-3"></div>
          </div>
        </c:when>
        <c:when test="${not empty comment}">
          <table class="table table-bordered table-hover"
            style="table-layout: fixed;">
            <thead>
              <tr>
                <th class="col-2">Comment</th>
                <th class="col-1">Author</th>
                <th class="col-2">Posted Date</th>
                <th class="text-center col-2">Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr
                style="vertical-align: middle; max-width: 50px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                <td class="text-truncate">${comment.description}</td>
                <td class="text-truncate">Leo</td>
                <td>${post.createdAt}</td>
                <td class="d-flex justify-content-center"><a
                  href="edit?id=${comment.id}"
                  class="btn mx-3 btn-primary"><i
                    class="fa-solid fa-pen-to-square"></i></a>
                  <button type="button" data-bs-toggle="modal"
                    data-bs-target="#deleteModal"
                    onclick="addLink(${comment.id})"
                    class="btn mx-3 btn-danger">
                    <i class="fa-solid fa-trash"></i>
                  </button></td>
              </tr>
            </tbody>
          </table>
        </c:when>
      </c:choose>
    </div>
  </div>
  <%@ include file="/jsp/common/footer.jsp"%>
  <div class="modal fade" id="deleteModal" tabindex="-1"
    aria-labelledby="deleteModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-sm">
      <div class="modal-content text-center text-danger">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="deleteModal">
            <i class="fa-solid fa-triangle-exclamation"></i>&nbsp;&nbsp;Delete
            Comment?
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
