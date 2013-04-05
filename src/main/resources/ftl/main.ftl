<html>
<title>${printStrings.windowTitle} - ${printStrings.menuBarHome}</title>
<#include "header.ftl"/>
    <link rel='stylesheet' type='text/css' href='../css/main_style.css'/>
    <link rel='stylesheet' type='text/css' href='../css/bloodtorrent.css'/>
    <link href='../css/fonts_googleapis.css' rel='stylesheet' type='text/css' />
    <style type='text/css'>
        .wsite-elements div.paragraph, .wsite-elements p, .wsite-elements .product-block .product-title, .wsite-elements .product-description, .wsite-elements .wsite-form-field label, .wsite-elements .wsite-form-field label, #wsite-content div.paragraph, #wsite-content p, #wsite-content .product-block .product-title, #wsite-content .product-description, #wsite-content .wsite-form-field label, #wsite-content .wsite-form-field label, .blog-sidebar div.paragraph, .blog-sidebar p, .blog-sidebar .wsite-form-field label, .blog-sidebar .wsite-form-field label {}
        #wsite-content div.paragraph, #wsite-content p, #wsite-content .product-block .product-title, #wsite-content .product-description, #wsite-content .wsite-form-field label, #wsite-content .wsite-form-field label, .blog-sidebar div.paragraph, .blog-sidebar p, .blog-sidebar .wsite-form-field label, .blog-sidebar .wsite-form-field label {}
        .wsite-elements h2, .wsite-elements .product-long .product-title, .wsite-elements .product-large .product-title, .wsite-elements .product-small .product-title, #wsite-content h2, #wsite-content .product-long .product-title, #wsite-content .product-large .product-title, #wsite-content .product-small .product-title, .blog-sidebar h2 {}
        #wsite-content h2, #wsite-content .product-long .product-title, #wsite-content .product-large .product-title, #wsite-content .product-small .product-title, .blog-sidebar h2 {}
        #wsite-title {color:#990000 !important;}
    </style>
    <script type='text/javascript' src='../js/jquery.min.js'></script>
    <script type='text/javascript' src='../js/jquery_effects.js'></script>
    <script type='text/javascript' src='../js/jquery.animate.js'></script>
    <script type='text/javascript' src='../js/fancybox.min.js'></script>
    <script type='text/javascript' src='../js/utilities-jq.js'></script>
    <script type='text/javascript' src='../js/flyout_menus_jq.js'></script>


    <script>
        $(function() {
            $( "input[type=button]" ).button()
        });
    </script>
<body>
<div class="outer-main-wrap">
    <div id="main-wrap">
        <div class="page-shadow">
            <div class="page">
                <div id="main">
                    <div id="content"><div id='wsite-content' class='wsite-elements wsite-not-footer'>
                        <div><div class="wsite-multicol"><div class='wsite-multicol-table-wrap' style='margin:0 -15px'>
                            <table class='wsite-multicol-table'>
                                <tbody class='wsite-multicol-tbody'>
                                <tr class='wsite-multicol-tr'>
                                    <td class='wsite-multicol-col' style='width:69%;padding:0 15px'>

                                        <div><div class="wsite-image wsite-image-border-thin " style="padding-top:10px;padding-bottom:10px;margin-left:0;margin-right:10px;text-align:left">
                                            <a>
                                                <img src="${printUrls.mainPageImage}" alt="Picture" style="width:auto;max-width:100%" />
                                            </a>
                                            <div style="display:block;font-size:90%"></div>
                                        </div></div>

                                    </td>
                                    <td class='wsite-multicol-col' style='width:32.88490284006%;padding:0 15px'>

                                        <h2 style="text-align:center;"><br />${printStrings.homeDescriptionTitle}</h2>

                                        <div class="paragraph" style="text-align:center;">${printStrings.homeDescriptionSubTitle}&nbsp;</div>

                                        <div class="paragraph" style="text-align:left;">&nbsp;&nbsp;</div>

                                        <div style="text-align:center;"><div style="height: 10px; overflow: hidden;"></div>
                                            <a class="wsite-button wsite-button-large wsite-button-highlight" href="/user" >
                                                <span class="wsite-button-inner">Register as donor</span>
                                            </a>
                                            <div style="height: 10px; overflow: hidden;"></div></div>

                                        <div style="text-align:center;"><div style="height: 10px; overflow: hidden;"></div>
                                            <a class="wsite-button wsite-button-large wsite-button-highlight" href="/requestForBlood" >
                                                <span class="wsite-button-inner">Request blood</span>
                                            </a>
                                            <div style="height: 10px; overflow: hidden;"></div></div>

                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div></div></div>

                        <div><div style="height: 20px; overflow: hidden; width: 100%;"></div>
                            <hr class="styled-hr" style="width:100%;">
                            <div style="height: 20px; overflow: hidden; width: 100%;"></div></div>

                        <h2 style="text-align:left;" id="successStoryTitle">How we have transformed some lives</h2>

                        <div><div class="wsite-multicol"><div class='wsite-multicol-table-wrap' style='margin:0 -15px'>
                            <table class='wsite-multicol-table'>
                                <tbody class='wsite-multicol-tbody'>
                                <tr class='wsite-multicol-tr'>
                                <#if successStories??>
                                    <#list successStories as story>
                                        <td class='wsite-multicol-col' style='width:23%;padding:0 15px'>
                                            <h2><a href="/successStory/${story.id}" class="successStoryDetailLink">${story.title}</a></h2>
                                            <#if story.thumbnailPath?exists><span class='imgPusher'></span><span><a href="/successStory/${story.id}"><img class="thumbnail" src="/successStory/image/${story.thumbnailPath}"/></a></span></#if>
                                            <div class="paragraph">${story.summary}</div>
                                        </td>
                                    </#list>
                                </#if>
                                </tr>
                                </tbody>
                            </table>
                        </div></div></div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</html>