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
<h1><img src="/images/main_sample.jpg" width="72" height="72" valign="middle" /> <label id="title">Blood Torrent</label></h1> <br/>
<#if user?exists>
    <#assign userId = user.id>
Hello ${userId?substring(0, userId?index_of("@")) }
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