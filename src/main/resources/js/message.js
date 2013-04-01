$(function() {
    $("#bloodRequestForm").ajaxForm(function(data) {
        if(data.result && data.result == "fail") {
            var msgText = data.messages[0];
            $(".message").text(msgText);
            $(".message").hide().slideDown();
        } else {
            $("input[type=submit]").attr("disabled", "disabled");
            $("#successForm input[name=requestId]").val(data.requestId);
            $("#successForm").submit();
        }
    });
});