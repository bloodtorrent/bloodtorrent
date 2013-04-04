<!doctype html>
<html>
<title>Blood Torrent - Home</title>
<#include "header.ftl"/>


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
                                                <img src="../images/2034022.png?526" alt="Picture" style="width:auto;max-width:100%" />
                                            </a>
                                            <div style="display:block;font-size:90%"></div>
                                        </div></div>

                                    </td>
                                    <td class='wsite-multicol-col' style='width:32.88490284006%;padding:0 15px'>

                                        <h2 style="text-align:center;"><br />Join for blood donation!!</h2>

                                        <div class="paragraph" style="text-align:center;">Save Life, Save Community,&nbsp;</div>

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