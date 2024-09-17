function addLink(id) {
	document.getElementById("deleteLink").setAttribute("href", href = "delete?id=" + id);
}

$(document).ready(function() {
	
	
	$('#bookmark').on('click', function(e) {
		e.preventDefault();
		let href = $(this).attr('href');
		let url = href.split(":")[0];
		let postId = href.split(":")[1];
		$.ajax({
			url: url,
			data: { postId: postId },
			type: 'POST',
			success: function(response) {
				$('#bookmark').removeClass('text-muted').addClass('text-primary');
				$('#bookmark-text').text('Remove Bookmark');
			},
			error: function(xhr, status, error) {
				console.error("An error occurred: " + error);
			}
		});
	});

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
	
	var form = document.getElementById('postForm');
	form.onsubmit = function() {
		var content = document.getElementById('content');
		content.value = quill.root.innerHTML;
		// Extract images from the content
		extractImagesFromContent(content.value);
	};
	
});

// Function to extract all image sources from the content
function extractImagesFromContent(content) {
	// Create a new DOMParser to parse the HTML
	let parser = new DOMParser();
	let doc = parser.parseFromString(content, 'text/html');

	// Select all img tags from the parsed HTML
	let imgTags = doc.querySelectorAll('img');

	// Loop through each img tag and extract its src attribute
	imgTags.forEach(function(img) {
		console.log('Image source:', img.src); // Log the src of each image
		// You can add further actions here, such as appending these images somewhere or saving them
	});
}

