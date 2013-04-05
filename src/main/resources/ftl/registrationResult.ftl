<html>
    <body>
        <div id="title">
            <center><label><h2><u>${printStrings.registerResult} ${result}</u></h2><label></center>
        </div>
        <div id="detail">
            <#list messages as message>
               <li> ${message} <br/>
            </#list>
        </div>
        <#if result == "success">
            <a href ="/"><input type="button" name="home" value="${printStrings.menuBarHome}"/></a>
        <#else>
            <input type="button" value="${printSrings.labelBackButton}" onclick="history.back()">
        </#if>
    </body>
</html>