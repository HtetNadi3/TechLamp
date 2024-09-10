function addLink(id) {
	document.getElementById("deleteLink").setAttribute("href", href = "delete?id=" + id);
}

$(document).ready(function() {
	var form = document.getElementById('postForm');
	form.onsubmit = function() {
		var content = document.getElementById('content');
		content.value = quill.root.innerHTML;
		// Extract images from the content
		extractImagesFromContent(content.value);
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
	
	new TomSelect('#category', {
		placeholder: 'Select or type category...',
		plugins: ['remove_button'],
		onItemAdd: function() {
			this.control_input.value = '';
			this.control_input.blur();
			this.control_input.focus();
		}
	});
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

