<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<!-- Logo -->
				<a class="navbar-brand" href="#"><span class="h1 fw-bold">Tech</span><span
					class="fw-bold h3">Lamp</span></a>

				<!-- Toggler button for mobile view -->
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
					aria-controls="navbarResponsive" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<!-- Navbar Content -->
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<form class="d-flex ms-5 my-2 my-lg-0 me-2"
							action="${pageContext.request.contextPath}/post/list"
							method="post">
							<button class="btn me-2 fs-5" type="submit">
								<i class="fas fa-search fa-lg"></i>
							</button>
							<input class="form-control fs-5 border-0" type="search"
								name="searchTitle" placeholder="Search" aria-label="Search">
						</form>

					</ul>

					<ul
						class="navbar-nav ms-auto mb-2 mb-lg-0 d-flex align-items-center">
						<!-- Categories Dropdown -->
						<form action="${pageContext.request.contextPath}/home" method="get" class="mb-4">
    <div class="input-group">
        <select name="categoryId" class="form-select">
            <option value="">All Categories</option>
            <c:forEach var="category" items="${categoryList}">
                <option value="${category.id}" ${param.categoryId == category.id ? 'selected' : ''}>${category.name}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary">Filter</button>
    </div>
</form>


						<!-- Write Button -->
						<li class="nav-item"><a class="nav-link btn  px-4 ms-2 fs-5"
							href="${pageContext.request.contextPath}/post/new"><i
								class="fa-solid fa-pen-to-square"></i> Write</a></li>

						<!-- Dark/Light Mode Toggle Button -->
						<li class="nav-item">
							<button class="btn toggle-theme-btn ms-3" id="theme-toggle">
								<i class="fas fa-moon fa-lg"></i>
							</button>
						</li>

						<!-- Profile Icon with Dropdown -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle ms-3" href="#"
							id="profileDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> <i class="fas fa-user-circle fa-2x"></i>
						</a>
							<ul class="dropdown-menu dropdown-menu-end"
								aria-labelledby="profileDropdown">
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><i
										class="fas fa-user me-2"></i>Profile</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-bookmark me-2"></i>Library</a></li>
								
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>