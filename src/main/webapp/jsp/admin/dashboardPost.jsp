<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/header.jsp"%>


<!DOCTYPE html>
<html>

<head>
<title>All Posts</title>
<link
	href="${pageContext.request.contextPath}/resources/css/dashboardStyle.css"
	rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
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

				<!-- Posts Table -->
				<h2 class="h5 mt-4">All Posts</h2>
				<div class="table-responsive">
					<table class="t table-hover glass">
						<thead>
							<tr>
								<th scope="col">Title</th>
								<th scope="col">Category</th>
								<th scope="col">Author</th>
								<th scope="col">Created Date</th>
								<th scope="col">Actions</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${posts}">
								<tr>
									<td>${post.title}</td>
									<td>${post.categoryName}</td>
									<td>${post.author}</td>
									<td>${post.createdAt}</td>
									<td class="d-flex">
										<button type="button" data-bs-toggle="modal"
											data-bs-target="#deleteModal"
											onclick="addLink(${post.id})" class="btn">
											<i class="fa-solid fa-trash fa-lg" style="color: red;"></i>
										</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</main>
		</div>
	</div>

	<div class="modal fade" id="deleteModal" tabindex="-1"
		aria-labelledby="deleteModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-sm">
			<div class="modal-content text-center text-danger">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="deleteModal">
						<i class="fa-solid fa-triangle-exclamation"></i>&nbsp;&nbsp;Delete
						Post?
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<span>Are You Sure To Delete?</span>
				</div>
				<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
					<a id="deleteLink" class="btn btn-danger">Delete</a>
				</div>
			</div>
		</div>
	</div>