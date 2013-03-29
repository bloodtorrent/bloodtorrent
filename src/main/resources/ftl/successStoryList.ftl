<html>
    <#if savedSuccessFlag>
        <script language="javascript">
            alert("This story is saved successfully.");
        </script>
    </#if>
    <body>
    <#include "/ftl/header.ftl" >
       <h1 id="title" align="center">How we helped</h1>
       <table border="1" cellspacing="0">
           <tr bgcolor="#cccccc">
              <th> </th>
              <th>Title</th>
              <th>Short Description</th>
              <th>Create Date</th>
              <th>Display on main page</th>
              <th>Image Preview</th>
           </tr>

           <#list successStoryList as story>
           <tr>
              <td><input type="checkbox" id="${story.id}"></td>
              <td>${story.title}</td>
              <td>${story.summary}</td>
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

       <input type="button" value="Create" onclick="location.href='/successStory/createView';"/>
    </body>
</html>