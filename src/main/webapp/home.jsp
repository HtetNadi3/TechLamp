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
<body class="bg-light text-dark">
	<div class="homePage container-fluid mt-2">
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
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle fs-5" href="#"
							id="categoriesDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false">Categories</a>
							<ul class="dropdown-menu" aria-labelledby="categoriesDropdown">
								<li><a class="dropdown-item" href="#">Category 1</a></li>
								<li><a class="dropdown-item" href="#">Category 2</a></li>
								<li><a class="dropdown-item" href="#">Category 3</a></li>
							</ul></li>

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
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-user me-2"></i>Profile</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-bookmark me-2"></i>Library</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-book me-2"></i>Stories</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-chart-line me-2"></i>Stats</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-cog me-2"></i>Settings</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-sliders-h me-2"></i>Refine Recommendations</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-file-alt me-2"></i>Manage Publications</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-question-circle me-2"></i>Help</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-star me-2"></i>Become a Medium Member</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-at me-2"></i>Create a Mastodon Account</a></li>
								<li><a class="dropdown-item" href="#"><i
										class="fas fa-check-circle me-2"></i>Apply for Author
										Verification</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>

		<!-- posts -->
		<div class="container-fluid mt-4 ms-5 pe-5 ">
			<!-- full -->
			<div class="row">
				<div class="col-8">
					<c:forEach var="post" items="${postList}" varStatus="loop">
						<div class="row mb-5">
							<!-- content and image -->
							<div class="col-8">
								<div class="w-100 p-3">
									<div class="d-flex align-items-start mb-2">
										<img src="${user.profile_img != null ? user.profile_img : '/img/profile.png'}" class="rounded-circle me-3"
											alt="Author" width="50" height="50">
										<div class="d-flex align-items-center mb-1">
											<h6 class="fw-bold mb-0 fs-5">${post.author}</h6>
											<span class="badge bg-primary ms-2">${post.categoryName}</span>
										</div>
									</div>

									<!-- Article Title -->
									<a class="fw-bold mb-2"
										href="${pageContext.request.contextPath}/post/detail?id=${post.id}">
										${post.title != null ? post.title : 'Untitled'} </a>

									<!-- Article Description -->
									<c:set var="content" value="${post.content}" />
									<c:set var="truncatedContent" value="" />

									<%
									String postContent = (String) pageContext.getAttribute("content");
									StringBuilder plainText = new StringBuilder();
									int wordCount = 0;
									int maxWords = 20; // Set the desired number of words

									if (postContent != null) {
										// Remove all HTML tags
										String textOnly = postContent.replaceAll("<[^>]+>", " ");

										// Split the text into words
										String[] words = textOnly.split("\\s+");

										for (String word : words) {
											if (wordCount >= maxWords) {
										// Stop adding text once the word count limit is reached
										break;
											}
											plainText.append(word).append(" ");
											wordCount++;
										}

										// Append ellipsis if the content is longer than maxWords
										if (wordCount >= maxWords) {
											plainText.append("...");
										}
									}

									pageContext.setAttribute("truncatedContent", plainText.toString().trim());
									%>

									<!-- Show truncated content -->
									<p class="mb-3">${truncatedContent}</p>


									<!-- Meta Info -->
									<div class="meta-info d-flex align-items-center">
										<span class="text-muted me-3"> <i
											class="fas fa-calendar-alt"></i> ${post.createdAt != null ? post.createdAt : 'N/A'}
										</span> <span class="text-muted me-3"><i class="fas fa-eye"></i>
											501</span> <span class="text-muted me-3"><i
											class="fas fa-comment"></i> 6</span> <span class="text-muted me-3"><i
											class="fa-solid fa-heart"></i> 23</span>
									</div>
								</div>
							</div>
							<div class="col-4">
								<c:set var="content" value="${post.content}" />
								<c:set var="imgSrc" value="" />

								<!-- Server-side scriptlet to extract the first image from the content -->
								<%
								String contentHtml = (String) pageContext.getAttribute("content");
								String imgSrc = "";
								if (contentHtml != null) {
									java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<img[^>]+src=\"([^\"]+)\"");
									java.util.regex.Matcher matcher = pattern.matcher(contentHtml);
									if (matcher.find()) {
										imgSrc = matcher.group(1); // Extract the first img src
									}
								}
								pageContext.setAttribute("imgSrc", imgSrc);
								%>

								<!-- Display the extracted image in the img tag -->
								<div class="d-flex justify-content-end">
									<img
										src="${imgSrc != null && !imgSrc.isEmpty() ? imgSrc : 'default-image-path.jpg'}"
										class="rounded" alt="Article Image"
										style="width: 200px; height: 200px; object-fit: cover;">
								</div>
							</div>

							<hr style="color: rgb(133, 134, 134);" class="mt-2">
						</div>
					</c:forEach>
				</div>

				<div class="col-4">
					<!-- Third Flex Item: Writer List -->
					<div class=" writer-list ms-5">
						<h6 class="fw-bold h3">Who to follow</h6>
						<ul class="list-unstyled ">
							<c:forEach var="user" items="${users}">
								<li class="d-flex align-items-center mb-3"><img src=""
									class="rounded-circle me-2" alt="${user.username}" width="40"
									height="40"> <span class="fw-bold me-auto">${user.username}</span>
									<button class="btn btn-outline-primary btn-sm">Follow</button>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</div>
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
</body>
</html>