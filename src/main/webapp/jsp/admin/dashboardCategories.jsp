<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/header.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <title>All Categories</title>
    <link
      href="${pageContext.request.contextPath}/resources/css/dashboardStyle.css"
      rel="stylesheet"
    />
    <script
      type="text/javascript"
      src="${pageContext.request.contextPath}/resources/js/dashboard.js"
    ></script>
  </head>

  <body>
    <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar glass">
          <div class="sidebar-header">
            <h4>Blog Admin</h4>
          </div>
          <ul class="nav flex-column">
            <li class="nav-item">
              <a
                class="nav-link active"
                href="${pageContext.request.contextPath}/dashboard"
              >
                <i class="fas fa-tachometer-alt"></i> Dashboard
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="${pageContext.request.contextPath}/dashboard/allPosts"
              >
                <i class="fas fa-blog"></i> Posts
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="${pageContext.request.contextPath}/dashboard/allUsers"
              >
                <i class="fas fa-users"></i> Users
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="${pageContext.request.contextPath}/dashboard/category/list"
              >
                <i class="fas fa-layer-group"></i> Categories
              </a>
            </li>
          </ul>
        </nav>

        <!-- Main Content -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div
            class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
          >
            <h1 class="h2">Dashboard</h1>
          </div>

          <!-- Categories Table -->
          <div
            class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mt-4"
          >
            <h5>All Categories</h5>
            <div class="btn-toolbar mb-2 mb-md-0">
              <a
                href="${pageContext.request.contextPath}/dashboard/category/new"
                class="btn glass"
              >
                <i class="fas fa-plus"></i> Add Category
              </a>
            </div>
          </div>
          <div class="table-responsive">
            <table class="t table-hover table-sm glass">
              <thead class="thead-light">
                <tr>
                  <th scope="col">Category Name</th>
                  <th scope="col">Number of Posts</th>
                  <th scope="col">Created Date</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                <!-- Loop through categories -->
                <c:forEach var="category" items="${categoryList}">
                  <tr>
                    <td>${category.name}</td>
                    <td>${category.postCount}</td>
                    <td>${category.createdAt}</td>
                    <td class="d-flex">
                      <a
                        href="edit?id=${category.id}"
                        class="btn mx-3 btn-primary"
                        ><i class="fa-solid fa-pen-to-square"></i
                      ></a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </main>
      </div>
    </div>
  </body>
</html>
