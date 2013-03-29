<!doctype html>
<html>
<head>
</head>
<body>
<#include "/ftl/header.ftl" >
<!-- Success Story -->

<div style="width:800px; height:auto; margin: 0 auto; ">
    <header>
    <center><h1>Success Story</h1></center>
    </header>
    <h1>${successStory.title}</h1>
    <hr>
    <img class="visualResource" src="/successStory/image/${successStory.visualResourcePath}" width="800" height="600"/><br>
    ${successStory.description}<br>
    <p align="right"><a href='/'>Home</a></p>
    <hr>
</div>
</body>
</html>

