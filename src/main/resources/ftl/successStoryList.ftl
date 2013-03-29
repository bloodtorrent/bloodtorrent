<html>
    <body>
    <#include "/ftl/header.ftl" >
       <h1 id="title" align="center">How we helped</h1>

       <#list successStoryList as story>
           <article>
               <h1>${story.title}</h1>
               <img class="thumbnail" src="/successStory/image/${story.thumbnailPath}"/>
               <p>${story.summary}</p>
               <p>${story.createDate?string("dd-MM-yyyy")}</p>
           </article>
       </#list>

       <input type="button" value="Create" onclick="location.href='/successStory/createView';"/>
    </body>
</html>