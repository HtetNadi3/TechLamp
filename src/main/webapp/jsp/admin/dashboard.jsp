<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/header.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Admin Dashboard</title>
    <link
      href="${pageContext.request.contextPath}/resources/css/dashboardStyle.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
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
                href="${pageContext.request.contextPath}/dashboard/overview"
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

          <div class="row">
            <!-- Stats Card -->
            <div class="col-md-6 col-lg-4 mb-4">
              <div class="card glass">
                <div
                  class="card-body d-flex flex-column align-items-center text-center"
                >
                  <i
                    class="fas fa-blog fa-lg pt-2"
                    style="color: rgb(62, 246, 74)"
                  ></i>
                  <h5 class="card-title pt-2">Total Posts</h5>
                  <p class="card-text fs-2">${postCount}</p>
                </div>
              </div>
            </div>
            <!-- Users Card -->
            <div class="col-md-6 col-lg-4 mb-4">
              <div class="card glass">
                <div
                  class="card-body d-flex flex-column align-items-center text-center"
                >
                  <i
                    class="fas fa-users fa-lg pt-2"
                    style="color: rgb(68, 99, 241)"
                  ></i>
                  <h5 class="card-title pt-2">Users</h5>
                  <p class="card-text fs-2">${userCount}</p>
                </div>
              </div>
            </div>

            <!-- Categories Card -->
            <div class="col-md-6 col-lg-4 mb-4">
              <div class="card glass">
                <div
                  class="card-body d-flex flex-column align-items-center text-center"
                >
                  <i
                    class="fas fa-layer-group fa-lg pt-2"
                    style="color: rgb(237, 166, 79)"
                  ></i>
                  <h5 class="card-title pt-2">Categories</h5>
                  <p class="card-text fs-2">${categoryCount}</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Posts Table -->
          <h2 class="h5 mt-4">Recent Posts</h2>
          <div class="table-responsive">
            <table class="t table-hover glass">
              <thead>
                <tr>
                  <th scope="col">Title</th>
                  <th scope="col">Category</th>
                  <th scope="col">Author</th>
                  <th scope="col">Created Date</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="post" items="${recentPosts}">
                  <tr>
                    <td>${post.title}</td>
                    <td>${post.categoryName}</td>
                    <td>${post.author}</td>
                    <td>${post.createdAt}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>

          <!-- Users Table -->
          <h2 class="h5 mt-4">Recent Users</h2>
          <div class="table-responsive">
            <table class="t table-hover glass">
              <thead class="thead-light">
                <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Email</th>
                  <th scope="col">Created Date</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="user" items="${recentUsers}">
                  <tr>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.created_date}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>

          <!-- Categories Table -->
          <h2 class="h5 mt-4">Categories</h2>
          <div class="table-responsive">
            <table class="t table-hover table-sm glass">
              <thead class="thead-light">
                <tr>
                  <th scope="col">Category Name</th>
                  <th scope="col">Number of Posts</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="category" items="${categories}">
                  <tr>
                    <td>${category.name}</td>
                    <td>${category.postCount}</td>
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
