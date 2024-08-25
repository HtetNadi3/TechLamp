function addLink(id) {
	document.getElementById("deleteLink").setAttribute("href", href = "delete?id=" + id);
}

$(document).ready(function() {
	var form = document.getElementById('postForm');
	if (form) {
		form.onsubmit = function() {
			var content = document.getElementById('content');
			content.value = quill.root.innerHTML;
		};

		var quill = new Quill('#editor-container', {
			theme: 'snow',
			modules: {
				toolbar: [
					['bold', 'italic', 'underline', 'strike'],
					[{ 'size': ['small', false, 'large', 'huge'] }],
					['code-block', 'link', 'image'],
					[{ 'color': [] }, { 'background': [] }],
					[{ 'align': [] }]
				]
			}
		});

		var initialContent = document.getElementById('content').value;
		quill.root.innerHTML = initialContent;

	}

	var isFullPreview = $('#isFullPreview').val();
	console.log(isFullPreview)
	if (isFullPreview) {
		$('img').addClass('pointer');
		$('img').on('click', function() {
			const imgSrc = $(this).attr('src');
			const modal = $('<div>').addClass('image-modal');

			const img = $('<img>').attr('src', imgSrc);
			modal.append(img);
			$('body').append(modal);
			modal.on('click', function() {
				$(this).remove();
			});
		});
	}

	document.addEventListener("DOMContentLoaded", function() {
		document.querySelectorAll('.post-item').forEach(function(postItem) {
			var content = postItem.querySelector('.post-content').innerHTML;
			var imgSrc = content.match(/<img[^>]+src="([^">]+)"/);
			if (imgSrc) {
				postItem.querySelector('.post-image').src = imgSrc[1];
			}
		});
	});
});
