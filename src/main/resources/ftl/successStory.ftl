<!doctype html>
<html>
<head>
</head>
<body>
<#if (user?exists && user.isAdmin = "Y")>
<#include "/ftl/adminHeader.ftl" >
<#else>
<#include "/ftl/header.ftl" >
</#if>
<!-- Success Story -->

<div style="width:800px; height:auto; margin: 0 auto; ">
    <header>
    <center><h1>Success Story</h1></center>
    </header>
    <h1>${successStory.title}</h1>
    <hr>
    <#if successStory.visualResourcePath?exists>
    <img class="visualResource" src="/successStory/image/${successStory.visualResourcePath}" width="800" height="600"/><br>
    </#if>
    ${successStory.description}<br>
    <p align="right"><a href='/'>Home</a></p>
    <hr>
    </div>
</body>
</html>

