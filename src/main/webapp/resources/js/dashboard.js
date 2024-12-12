// You can add custom JavaScript or jQuery code here

$(document).ready(function() {
    // Example jQuery function
    $('.nav-link').on('click', function() {
        $('.nav-link').removeClass('active');
        $(this).addClass('active');
    });
});
<script>
    function addLink(postId) {
        document.getElementById('deleteLink').href = 
            '${pageContext.request.contextPath}/dashboard/delete?id=' + postId;
    }
</script>

