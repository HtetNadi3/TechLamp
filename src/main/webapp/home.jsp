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
	<%@ include file="/jsp/common/navbar.jsp"%>
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
										<!-- <img src="https://www.freepik.com/free-vector/user-circles-set_145856997.htm#fromView=search&page=1&position=36&uuid=16ba5158-0da4-4802-8beb-f1f9bcb63b11"
											class="rounded-circle me-3" width="50" height="50"> -->
										<i class="fas fa-user-circle fa-2x pe-2" style="color: #4b6b98"></i>
										<div class="d-flex align-items-center mb-1">
											<h6 class="fw-bold mb-0 fs-5 ms-2"> ${post.author}</h6>
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
										</span>
										<!-- <span class="text-muted me-3"><i class="fas fa-eye"></i>
											501</span>  -->
										<a
											href="${pageContext.request.contextPath}/comment/list?postId=${post.id}">
											<span class="text-muted me-3"><i
												class="fas fa-comment"></i> ${commentCounts[post.id]}</span>
										</a> <span class="text-muted me-3"><i
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
										class="rounded" alt="not include img"
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
								<li class="d-flex align-items-center mb-3">
								<i class="fas fa-user-circle fa-2x pe-2" style="color: #583cf5"></i>
								    <a href="${pageContext.request.contextPath}/user?id=${user.id}" class="fw-bold me-auto" style="text-decoration: none">${user.username}</a>
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