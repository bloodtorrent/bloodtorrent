<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
        <link rel="stylesheet" href="/css/bloodtorrent.css"/>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
        <script type="text/javascript">
        $(function() {
            $("#save").click(function(){

                $("#createSuccessStory").submit();
            });
            $("#cancel").click(function(){
                location.href="/successStory/list";
            });
        });
        </script>
        </head>
    <body>
    <#include "/ftl/adminHeader.ftl" >
        <section id = "successStoryEditor">
        <h1 align = "center">${printStrings.successStorySubTitle}</h1><br>
        <form id = "createSuccessStory" enctype="multipart/form-data" action="/successStory/create" method="post">
            <p>
               ${printStrings.labelTitle} :
               <input type="text" name="title" />
            </p>
            <p>
               ${printStrings.labelSelectImage} :
               <input type="file" name="visualResourcePath" id="visualResourcePath" style="visibility:show;"/>
            </p>
            <p>
               ${printStrings.labelShortDescription} :<br/>
               <input type="text" name="summary" />
            </p>
            <p>
               ${printStrings.labelDetailedDescription} : <br/>
               <textarea cols="80" rows="10" fixed="true" name="description"></textarea>
            </p>
            <p>
               <input type="button" id="save" value="${printStrings.labelSaveButton}" />
               <input type="button" id="cancel" value="${printStrings.labelCancelButton}"/>
            </p>
        </form>
       </section>
    </body>
</html>