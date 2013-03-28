<!doctype html>
<html>
<head>
</head>
<body>
<#include "/ftl/header.ftl" >
<!-- Success Story -->
<header>
<center><h1>Success Story</h1></center>
</header>
<h1>${successStory.title}</h1>
<hr>
<img class="visualResource" src="${successStory.visualResourcePath}" width="800" height="600"/><br>
${successStory.description}<br>
<input type="button" value="Home" onclick="location.href='/';"/><br>
<hr>
</body>
</html>

