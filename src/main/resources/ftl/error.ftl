<html>
    <body>
       <h1>Errr!</h1>
       <hr noshade>
       <#if isNullPointerException>
            Please fill out all the mandatory fields.  <br>
            ${field} is empty.
       <#else>
            Please check ${field}
       </#if>
       <br>
       <a href="javascript:history.go(-1);">Back</a>
    </body>
</html>