<html>
<#include "/ftl/header.ftl" >
<!-- Success Story -->
<div class="outer-main-wrap">
    <div id="main-wrap">
        <div class="page-shadow">
            <div class="page">
                <div id="main">
                    <div id="content">
                        <div id='wsite-content' class='wsite-elements wsite-not-footer'>
                            <div id="title">
                               <h2 style="text-align:center">${successStory.title}</h2>
                            </div>
                            <div id="contentAll">
                                <div id="contentImg">
                                <#if successStory.visualResourcePath?exists>
                                    <img class="visualResource wsite-image galleryImageBorder" src="/successStory/image/${successStory.visualResourcePath}"/><br>
                                </#if>
                                </div>
                                <div id="contentText">
                                ${successStory.description}<br>
                                </div>
                            </div>
                            <hr style="clear:both;visibility:hidden;width:100%;"><hr/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>

