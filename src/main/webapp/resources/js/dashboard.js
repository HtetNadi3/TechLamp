// You can add custom JavaScript or jQuery code here

$(document).ready(function() {
    // Example jQuery function
    $('.nav-link').on('click', function() {
        $('.nav-link').removeClass('active');
        $(this).addClass('active');
    });
});
