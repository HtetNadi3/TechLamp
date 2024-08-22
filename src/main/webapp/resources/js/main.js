function addLink(id) {
	document.getElementById("deleteLink").setAttribute("href", href = "delete?id=" + id);
}

$(document).ready(function() {
	var form = document.getElementById('postForm');
	form.onsubmit = function() {
		var content = document.getElementById('content');
		content.value = quill.root.innerHTML;
	};

	var quill = new Quill('#editor-container', {
		theme: 'snow',
		modules: {
			toolbar: [['bold', 'italic', 'underline'],
			['link', 'image']]
		}
	});

	var initialContent = document.getElementById('content').value;
	quill.root.innerHTML = initialContent;
});
