document.addEventListener('DOMContentLoaded', function() {
	const form = document.getElementById('registrationForm');
	const password = document.getElementById('password');
	const confirmPassword = document.getElementById('confirmPassword');
	const passwordError = document.getElementById('passwordError');
	const confirmPasswordError = document.getElementById('confirmPasswordError');

	function validatePassword() {
		const passwordValue = password.value;
		const confirmPasswordValue = confirmPassword.value;

		// Clear previous errors
		passwordError.textContent = '';
		confirmPasswordError.textContent = '';

		if (password.length < 8 || !/[A-Z]/.test(passwordValue) || !/[0-9]/.test(passwordValue)) {
			passwordError.textContent = 'Password must be at least 8 characters long and contain an uppercase letter and a number.';
		}

		if (confirmPasswordValue && passwordValue !== confirmPasswordValue) {
			confirmPasswordError.textContent = 'Passwords do not match';
		}
	}

	password.addEventListener('input', validatePassword);
	confirmPassword.addEventListener('input', validatePassword);

	form.addEventListener('submit', function(event) {
		validatePassword();
		if (passwordError.textContent || confirmPasswordError.textContent) {
			event.preventDefault(); // Prevent form from submitting if validation fails
		}
	});
});
