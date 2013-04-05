var toggleCheckedSuccessStory = function() {
   $(this).toggleClass("ischecked");
}

var suppressSubmitAndNotifyIfInvalid = function(moreMessage1, moreMessage2, zeroMessage) {
    var numChecked = $('.ischecked').size(),
        maxChecks = 3;
    if (numChecked > maxChecks) {
        alert(moreMessage1+' '+maxChecks+' '+moreMessage2);
        return false;
    } else if (numChecked == 0){
        alert(zeroMessage);
        return false;
    }
    return true;
}