<html>
    <body>
       <h1 id="title">How we helped</h1>

       <#list successStories as story>
           <article>
               <h1>${story.getTitle()}</h1>
               <img class="thumbnail" src="${story.getThumbnailPath()}"/>
               <p>${story.getSummary()}</p>
               <a class="successStoryDetailLink" href="#" onclick="alert('See you next iteration :)'); return false;">READ MORE</a>
           </article>
       </#list>

       <input type="button" value="Create" onclick="location.href='/createStory';"/>
    </body>
</html>