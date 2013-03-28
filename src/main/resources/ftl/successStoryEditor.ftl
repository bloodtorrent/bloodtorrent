<html>
    <head>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
        <link rel="stylesheet" href="/css/bloodtorrent.css"/>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
        <script type="text/javascript">
        $(function() {
            $("#upload").click(function(){
                $("#visualResourcePath").click();
                $("#fileName").val($("#visualResourcePath").val().split('\\').pop());
            });
            $("#save").click(function(){
                $("#createSuccessStory").submit();
            });
        });
        </script>
        </head>
    <body>
    <#include "/ftl/header.ftl" >
        <section id = "successStoryEditor">
        <h1 align = "center">Success Stories</h1><br>
        <form id = "createSuccessStory" enctype="multipart/form-data" action="/successStory/create" method="post">
           Title :
           <input type="text" name="title" /> <p/>
           <input type="button" id="upload" value="Select Image or video" /><input type="text" id="fileName" />
           <input type="file" name="visualResourcePath" id="visualResourcePath" style="visibility:show;"/><p/>
           Short Description :<br/>
           <input type="textarea" name="summary" /> <p/>
           Detailed Description : <br/>
           <input type="textarea" name="description" /> <p/>

           <input type="button" id="save" value="Save" />
           <input type="button" id="cancel" value="Cancel"/>
        </form>
       </section>
    </body>
</html>