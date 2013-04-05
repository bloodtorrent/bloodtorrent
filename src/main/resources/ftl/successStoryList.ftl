<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <#if savedSuccessFlag>
        <script language="javascript">
            alert("${printStrings.successStoryCreateAlert}");
        </script>
    </#if>
    <#if editSuccessFlag>
        <script language="javascript">
            alert("${printStrings.successStoryEditAlert}");
        </script>
    </#if>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="/js/successStories.js"></script>
        <script>
            $(document).ready(function() {
                var $form = $("#successStorySelectionForm");
                $('#submitButtonUp').click(function() {$form.submit()});
                $('#submitButtonDown').click(function() {$form.submit()});
                $form.find("input:checkbox").click(toggleCheckedSuccessStory);
                $form.on('submit', function(){
                    if(!suppressSubmitAndNotifyIfInvalid(
                            "${printStrings.successStoryIfMoreThreeFirst}",
                            "${printStrings.successStoryIfMoreThreeSecond}",
                            "${printStrings.successStoryIfZero}"))
                            return false;});
            });
        </script>
    </head>
    <body>
    <#include "/ftl/adminHeader.ftl" >
       <h1 id="title" align="center">${printStrings.successStoryListTitle}</h1>
       <input type="button" value="${printStrings.successStoryCreateButton}" onclick="location.href='/successStory/createView';"/>
       <input type="button" id="submitButtonUp" value="${printStrings.successStorySubmitButton}"/>
       <form id="successStorySelectionForm" method='POST' action="/successStory/selectForMain">
           <table border="1" cellspacing="0">
               <tr bgcolor="#cccccc">
                   <th> </th>
                   <th>${printStrings.tableTitle}</th>
                   <th>${printStrings.tableShortDescription}</th>
                   <th>${printStrings.tableCreateDate}</th>
                   <th>${printStrings.tableDisplayOnMainPage}</th>
                   <th>${printStrings.tableImagePreview}</th>
               </tr>

           <#list successStoryList as story>
           <tr>
              <td><input type="checkbox" name="checkStoryId" id="${story.id}" value="${story.id}"></td>
              <td>${story.title}</td>
              <td>${story.summary}&nbsp;</td>
              <td>${story.createDate?string("dd-MM-yyyy")}</td>
              <td>${story.showMainPage}</td>
              <td>
              <#if story.thumbnailPath?has_content>
                <img class="thumbnail" src="/successStory/image/${story.thumbnailPath}" width="72" height="72" />
              </#if>
              </td>
           </tr>
           </#list>
       </table>
       <input type="button" value="${printStrings.successStoryCreateButton}" onclick="location.href='/successStory/createView';"/>
       <input type="button" id="${printStrings.successStorySubmitButton}" value="Display on the main page"/>
       </form>
    </body>
</html>