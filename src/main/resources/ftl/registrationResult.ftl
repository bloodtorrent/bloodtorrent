<html>
    <body>
        <div id="title">
            <center><label><h2><u>Registration ${result}</u></h2><label></center>
        </div>
        <div id="detail">
            <#list messages as message>
               <li> ${message} <br/>
            </#list>
        </div>
        <#if result == "success">
            <a href ="/"><input type="button" name="home" value="Home"/></a>
        <#else>
            <input type="button" value="back" onclick="history.back()">
        </#if>
    </body>
</html>