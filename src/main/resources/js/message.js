function showMessage(message, type) {
    $(".message").slideUp(100, function() {
        $(this).removeClass("info error warning success")
            .addClass(type === undefined ? "error" : type)
            .text(message).slideDown()});
}