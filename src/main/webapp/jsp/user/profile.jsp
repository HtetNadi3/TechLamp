<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>

<style>


.user-info {
	background: rgba(255, 255, 255, 0.15);
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.user-info p {
	font-size: 1.1rem;
	margin: 10px 0;
}

.user-info p strong {
	color: #007bff;
}

.user-profile button {
	width: 100%;
	margin-top: 20px;
}

.form-group {
	margin-bottom: 15px;
}

.edit-profile-btn {
	margin-top: 10px;
}
</style>

<script>
	function toggleEditProfile() {
		var profileView = document.getElementById('profile-view');
		var editProfileForm = document.getElementById('edit-profile-form');
		if (editProfileForm.style.display === "none") {
			profileView.style.display = "none";
			editProfileForm.style.display = "block";
		} else {
			editProfileForm.style.display = "none";
			profileView.style.display = "block";
		}
	}
	function validateProfileUpdate() {
		var password = document.getElementById('password').value;
		var confirmPassword = document.getElementById('confirmPassword').value;

		if (password !== confirmPassword) {
			alert('Passwords do not match!');
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div class="container mt-2 mb-5">
	<%@ include file="/jsp/common/navbar.jsp"%>
		<div class="row">
			<!-- Left Column: User's Posts -->
			<div class="col-8">
				<c:forEach var="post" items="${postList}" varStatus="loop">
					<div class="row mb-5">
						<!-- content and image -->
						<div class="col-8">
							<div class="w-100 p-3">
								<div class="d-flex align-items-start mb-2">
									<i class="fas fa-user-circle fa-2x pe-2" style="color: #4b6b98"></i>
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
									</span>
									<!-- <span class="text-muted me-3"><i class="fas fa-eye"></i>
											501</span>  -->
									<a
										href="${pageContext.request.contextPath}/comment/list?postId=${post.id}">
										<span class="text-muted me-3"><i class="fas fa-comment"></i>
											${commentCounts[post.id]}</span>
									</a> 
									    	
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

			<!-- Right Column: User Profile -->
			<div class="col-md-4">
				<!-- User Profile Section -->
				<div id="profile-view" class="user-profile">
					<h1>User Profile</h1>

					<!-- User Information Display -->
					<div class="user-info">
						<p>
							<strong>Name:</strong> ${user.username}
						</p>
						<p>
							<strong>Email:</strong> ${user.email}
						</p>
						<p>
							<strong>Phone:</strong> ${user.phone_number}
						</p>
						<p>
							<strong>Occupation:</strong> ${user.occupation}
						</p>
						<p>
							<strong>Bio:</strong> ${user.bio}
						</p>
					</div>

					<button type="button" class="btn btn-primary edit-profile-btn"
						onclick="toggleEditProfile()">Edit Profile</button>
				</div>

				<!-- User Update Form (Hidden by default) -->
				<div id="edit-profile-form" class="user-profile"
					style="display: none;">
					<h1>Edit Profile</h1>
					<c:url var="profileUpdate" value="/profile/update"></c:url>
					<form action="${profileUpdate}" method="post"
						enctype="multipart/form-data"
						onsubmit="return validateProfileUpdate()">
						<input type="hidden" name="userId" value="${user.id}" />

						<div class="form-group">
							<label for="name">Name:</label> <input type="text" id="name"
								name="username" value="${user.username}" class="form-control"
								required />
						</div>

						<div class="form-group">
							<label for="email">Email:</label> <input type="email" id="email"
								name="email" value="${user.email}" class="form-control" required />
						</div>

						<div class="form-group">
							<label for="phone">Phone Number:</label> <input type="tel"
								id="phone" name="phone_number" value="${user.phone_number}"
								class="form-control" />
						</div>

						<div class="form-group">
							<label for="occupation">Occupation:</label> <select
								id="occupation" name="occupation" class="form-control">
								<option value="Undergraduate"
									${user.occupation == 'Undergraduate' ? 'selected' : ''}>Undergraduate</option>
								<option value="Graduate"
									${user.occupation == 'Graduate' ? 'selected' : ''}>Graduate</option>
								<option value="Graphic Designer"
									${user.occupation == 'Graphic Designer' ? 'selected' : ''}>Graphic
									Designer</option>
								<option value="Programmer"
									${user.occupation == 'Programmer' ? 'selected' : ''}>Programmer</option>
								<option value="Software Engineer"
									${user.occupation == 'Software Engineer' ? 'selected' : ''}>Software
									Engineer</option>
							</select>
						</div>

						<div class="form-group">
							<label for="bio">Bio:</label>
							<textarea id="bio" name="bio" class="form-control">${user.bio}</textarea>
						</div>



						<div class="form-group">
							<label for="currentPassword">Current Password:</label> <input
								type="password" id="currentPassword" name="currentPassword"
								class="form-control" placeholder="Enter current password" />
						</div>

						<div class="form-group">
							<label for="password">New Password:</label> <input
								type="password" id="password" name="password"
								class="form-control" placeholder="Enter new password" />
						</div>

						<div class="form-group">
							<label for="confirmPassword">Confirm Password:</label> <input
								type="password" id="confirmPassword" name="confirmPassword"
								class="form-control" placeholder="Confirm new password" />
						</div>

						<button type="submit" class="btn btn-success">Update
							Profile</button>
						<button type="button" class="btn btn-secondary"
							onclick="toggleEditProfile()">Cancel</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
