<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>

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
</script>
</head>
<body>
	<div class="container">
		<!-- User Profile Section -->
		<div id="profile-view" class="user-profile">
			<h1>User Profile</h1>

			<!-- Profile Image -->
			<div class="profile-image">
				<img
					src="${user.profile_img != null ? user.profile_img : '/img/profile.png'}"
					alt="Profile Image" />
			</div>

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

			<button type="button" onclick="toggleEditProfile()">Edit
				Profile</button>
		</div>

		<!-- User Update Form (Hidden by default) -->
		<div id="edit-profile-form" class="user-profile"
			style="display: none;">
			<h1>Edit Profile</h1>
			<form action="${pageContext.request.contextPath}/profile/update"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="userId" value="${user.id}" />

				<div class="form-group">
					<label for="name">Name:</label> <input type="text" id="name"
						name="username" value="${user.username}" required />
				</div>

				<div class="form-group">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" value="${user.email}" required />
				</div>

				<div class="form-group">
					<label for="phone">Phone Number:</label> <input type="tel"
						id="phone" name="phone_number" value="${user.phone_number}" />
				</div>

				<div class="form-group">
					<label for="occupation">Occupation:</label> <select id="occupation"
						name="occupation">
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
					<textarea id="bio" name="bio">${user.bio}</textarea>
				</div>

				<div class="form-group">
					<label for="profileImage">Profile Image:</label> <input type="file"
						id="profileImage" name="profile_img" />
				</div>

				<div class="form-group">
					<label for="currentPassword">Current Password:</label> <input
						type="password" id="currentPassword" name="currentPassword"
						placeholder="Enter current password" />
				</div>
				
				<div class="form-group">
					<label for="password">New Password:</label> <input type="password"
						id="password" name="password" placeholder="Enter new password" />
				</div>

				<div class="form-group">
					<label for="confirmPassword">Confirm Password:</label> <input
						type="password" id="confirmPassword" name="confirmPassword"
						placeholder="Confirm new password" />
				</div>

				<button type="submit">Update Profile</button>
				<button type="button" onclick="toggleEditProfile()">Cancel</button>
			</form>
		</div>

		<!-- User's Posts Section -->
		<div class="user-posts">
			<h2>Your Posts</h2>

			<c:if test="${!empty posts}">
				<ul>
					<c:forEach var="post" items="${postList}">
						<li>
							<h3>
								<a href="postDetails?id=${post.id}">${post.title}</a>
							</h3>
							<p>${post.description}</p> <a href="editPost?id=${post.id}">Edit</a>
							| <a href="deletePost?id=${post.id}">Delete</a>
						</li>
					</c:forEach>
				</ul>
			</c:if>

			<c:if test="${empty posts}">
				<p>No posts available.</p>
			</c:if>
		</div>
	</div>
</body>
</html>
