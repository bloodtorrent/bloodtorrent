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
<#if successStories?size = 0>
    <h2>Save the people.</h2>
<#else>
<#list successStories as story>
    <article>
        <h1>${story.getTitle()}</h1>
        <img class="thumbnail" src="${story.getThumbnailPath()}"/>
        <p>${story.getSummary()}</p>
        <a class="successStoryDetailLink" href="#" onclick="alert('See you next iteration :)'); return false;">READ MORE</a>
    </article>
</#list>
</#if>
<!-- //Success Story -->
</section>
</body>
</html>