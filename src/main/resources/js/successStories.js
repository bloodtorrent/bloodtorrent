var toggleCheckedSuccessStory = function() {
   $(this).toggleClass("ischecked");
}

var suppressSubmitAndNotifyIfInvalid = function() {
    var numChecked = $('.ischecked').size(),
        maxChecks = 3;
    if (numChecked > maxChecks) {
        alert('You can choose '+maxChecks+' success stories at most');
        return false;
    } else if (numChecked == 0){
        alert('Please, select 1 ~ 3 stories for displaying on the main page');
        return false;
    }
    return true;
}