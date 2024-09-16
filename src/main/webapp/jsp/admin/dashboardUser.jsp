<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/header.jsp"%>


<!DOCTYPE html>
<html>

<head>
<title>Admin Dashboard</title>
<link
	href="${pageContext.request.contextPath}/resources/css/dashboardStyle.css"
	rel="stylesheet">
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
					<li class="nav-item"><a class="nav-link active"
						href="${pageContext.request.contextPath}/dashboard"> <i
							class="fas fa-tachometer-alt"></i> Dashboard
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/dashboard/allPosts">
							<i class="fas fa-blog"></i> Posts
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/dashboard/allUsers">
							<i class="fas fa-users"></i> Users
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/dashboard/category/list">
							<i class="fas fa-layer-group"></i> Categories
					</a></li>
					
				</ul>

			</nav>

			<!-- Main Content -->
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Dashboard</h1>
				</div>

				<!-- Users Table -->
				<h2 class="h5 mt-4">All Users</h2>
				<div class="table-responsive">
					<table class="t table-hover glass">
						<thead class="thead-light">
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Email</th>
								<th scope="col">No. of Posts</th>
								<th scope="col">Created Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users}">
								<tr>
									<td>${user.username}</td>
									<td>${user.email}</td>
									<td>${user.postCount}</td>
									<td>${user.created_date}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</main>
		</div>
	</div>