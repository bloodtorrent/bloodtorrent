<!doctype html>
<html>
<head>
    <title>Blood Torrent</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <link rel="stylesheet" href="/css/bloodtorrent.css"/>
    <!--[if lt IE 9]>
    <script src="https://raw.github.com/aFarkas/html5shiv/master/dist/html5shiv.js"></script>
    <![endif]-->
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "input[type=button]" ).button()
        });
    </script>
</head>
<body>
<#include "/ftl/header.ftl" >
<!-- Success Story -->
<section id="successStory">
    <header><h1>Success Story</h1></header>
<#if catchPhrase??>
    <img class="catchPhrase" src="${catchPhrase.imagePath}"/>
</#if>
<#if successStories??>
    <#list successStories as story>
        <article>
            <h1>${story.title}</h1>
            <img class="thumbnail" src="/successStory/image/${story.thumbnailPath}"/>
            <p>${story.summary}</p>
            <a class="successStoryDetailLink" href="/successStory/${story.id}">READ MORE</a>
        </article>
    </#list>
</#if>
<!-- //Success Story -->
</section>
</body>
</html>