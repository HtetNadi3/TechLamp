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

        if (passwordValue.length < 6) {
            passwordError.textContent = 'Password must be at least 6 characters long';
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
