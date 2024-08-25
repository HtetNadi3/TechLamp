<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Post Detail</title>
</head>
<body class="d-flex flex-column vh-100">
  <input type="hidden" id="isFullPreview" value="true" />
  <div
    class="container-fluid flex-grow-1 d-flex align-items-center justify-content-center">
    <div class="card" style="width: 40%">
      <div class="card-header text-center">
        <h2 class="my-0">Post Detail</h2>
      </div>
      <div class="card-body">
        <div class="row">
          <div class="col-md-4">Title :</div>
          <div class="col-md-8">${post.title}</div>
        </div>
        <hr class="my-2">
        <div class="row">
          <div class="col-md-4">Content :</div>
          <div class="col-md-8">${post.content}</div>
        </div>
        <hr class="my-2">
        <div class="row">
          <div class="col-md-4">Author :</div>
          <div class="col-md-8">Leo</div>
        </div>
        <hr class="my-2">
        <div class="row">
          <div class="col-md-4">Posted Date :</div>
          <div class="col-md-8">${post.createdAt}</div>
        </div>
      </div>
      <div class="card-footer text-center">
        <a href="edit?id=${post.id}" class="btn btn-primary mx-3">Edit</a>
        <a href="${pageContext.request.contextPath}/post/list"
          class="btn btn-dark mx-3">Back</a>
      </div>
    </div>
  </div>
  <%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>
