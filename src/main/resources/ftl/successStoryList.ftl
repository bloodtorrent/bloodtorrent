<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <#if savedSuccessFlag>
        <script language="javascript">
            alert("${properties.successStoryCreateAlert}");
        </script>
    </#if>
    <#if editSuccessFlag>
        <script language="javascript">
            alert("${properties.successStoryEditAlert}");
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
                $form.on('submit', suppressSubmitAndNotifyIfInvalid);
            });
        </script>
    </head>
    <body>
    <#include "/ftl/adminHeader.ftl" >
       <h1 id="title" align="center">${properties.successStoryListTitle}</h1>
       <input type="button" value="${properties.successStoryCreateButton}" onclick="location.href='/successStory/createView';"/>
       <input type="button" id="submitButtonUp" value="${properties.successStorySubmitButton}"/>
       <form id="successStorySelectionForm" method='POST' action="/successStory/selectForMain">
       <table border="1" cellspacing="0">
           <tr bgcolor="#cccccc">
              <th> </th>
              <th>${properties.tableTitle}</th>
              <th>${properties.tableShortDescription}</th>
              <th>${properties.tableCreateDate}</th>
              <th>${properties.tableDisplayOnMainPage}</th>
              <th>${properties.tableImagePreview}</th>
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
       <input type="button" value="${properties.successStoryCreateButton}" onclick="location.href='/successStory/createView';"/>
       <input type="button" id="${properties.successStorySubmitButton}" value="Display on the main page"/>
       </form>
    </body>
</html>