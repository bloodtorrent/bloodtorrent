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
        function goLogin() {
            var email = document.getElementById("email");
            var password = document.getElementById("password");
            if(email.value == ""){
                alert('Please input ID');
                email.focus();
                return;
            }
            if(password.value == ""){
                alert('Please input PASSWORD');
                password.focus();
                return;
            }
            document.login.action = "/login";
            document.login.method = "post";
            document.login.submit();
        }
    </script>
</head>
<body>
<h1><label id="title">Welcome to Blood Torrent</label></h1> <br/>
<#if user?exists>
Hello ${user.id}
<a href="/logoff">Sign off</a>
<br/>
<h1>status : login</h1>
<#else>
<form name="login">
<#if message?exists>
<span style="color:red">${message}</span>
<br/>
</#if>
E-mail : <input type="text" name="email" id="email" value="" />&nbsp;&nbsp;&nbsp;&nbsp;
Password : <input type="password" id="password" name="password" /> &nbsp;&nbsp;
<input type="button" value="Log in" name="login" onclick="goLogin();">&nbsp;&nbsp;
</form>
<a href="/user"><input type="button" value="Register donor"/></a> &nbsp;
<a href="/requestForBlood"><input type="button" value="Request blood"/></a>
<h1>status : logoff</h1>
</#if>
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
        <a href="#" onclick="alert('See you next iteration :)'); return false;">READ MORE</a>
    </article>
</#list>
</#if>
<!-- //Success Story -->
</section>
</body>
</html>