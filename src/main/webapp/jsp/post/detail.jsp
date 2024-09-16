<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>Post Detail</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      color: #333;
      
    }
    
    .container-fluid {
      max-width: 85%;
      margin-top: 10px;
    }

    .card {
      border: none;
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .card-title {
      font-size: 2rem;
      font-weight: bold;
      color: #333;
      margin-bottom: 15px;
    }

    .post-metadata {
      color: #6c757d;
      font-size: 0.9rem;
      margin-bottom: 20px;
    }

    .post-metadata i {
      margin-right: 5px;
    }

    .post-content {
      font-size: 1.1rem;
      line-height: 1.6;
      margin-bottom: 20px;
    }

    .icon-bar {
      margin-top: 15px;
      display: flex;
      justify-content: space-between;
    }

    .icon-bar .btn {
      font-size: 1.2rem;
      color: #6c757d;
      border: none;
      background: none;
    }

    .icon-bar .btn:hover {
      color: #333;
    }

    .btn-dark {
      background-color: #333;
      color: white;
    }
  </style>
</head>
<body>
<%@ include file="/jsp/common/navbar.jsp"%>
<div class="container-fluid">
  <div class="card p-4">
    <h1 class="card-title">${post.title}</h1>
    <div class="post-metadata">
      <i class="fas fa-user"></i>${post.author} &middot; 
      <i class="fas fa-calendar-alt"></i>${post.createdAt}
    </div>

    <div class="post-content">${post.content}</div>

    <div class="icon-bar">
      <button class="btn">
        <i class="fas fa-thumbs-up"></i> 8.4K
      </button>
      <button class="btn">
        <i class="fas fa-comment"></i> 
      </button>
      <button class="btn">
        <i class="fas fa-bookmark"></i>
      </button>
      <button class="btn">
        <i class="fas fa-share"></i>
      </button>
    </div>

    <div class="text-center mt-4">
      <a href="${pageContext.request.contextPath}/post/list" class="btn btn-dark">Back</a>
    </div>
  </div>
</div>

</body>
</html>
