<script>
    function goLogin() {
        var email = document.getElementById("email");
        var password = document.getElementById("password");
        if(email.value == ""){
            alert('Please input E-mail');
            email.focus();
            return;
        }
        if(password.value == ""){
            alert('Please input Password');
            password.focus();
            return;
        }
        document.login.action = "/login";
        document.login.method = "post";
        document.login.submit();
    }
</script>
<table border="0" width="100%"><tr><td>
<h1><img src="/images/main_sample.jpg" width="72" height="72" align="middle" onclick="location.href='/'" /> <label id="title">Blood Torrent</label></h1></td><td align="right">
<#if user?exists>
    <#assign userId = user.id>
Hello! ${userId?substring(0, userId?index_of("@")) }
<a href="/logoff">Sign off</a>
</td></tr></table>
<#else>
<form name="login">
    <#if message?exists>
        <span style="color:red">${message}</span>
        <br/>
    </#if>
    E-mail : <input type="text" name="email" id="email" value="" />&nbsp;&nbsp;&nbsp;&nbsp;
    Password : <input type="password" id="password" name="password" onkeydown="if (event.keyCode == 13) goLogin();" /> &nbsp;&nbsp;
    <input type="button" value="Log in" name="login" onclick="goLogin();">&nbsp;&nbsp;
    <a href="/user">Register donor</a> &nbsp;|
    <a href="/requestForBlood">Request blood</a>
</form>
</td></tr></table>
</#if>
<hr noshade />
