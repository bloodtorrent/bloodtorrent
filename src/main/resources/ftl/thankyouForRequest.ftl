<html>
<#include "header.ftl"/>
<div class="outer-main-wrap">
    <div id="main-wrap">
        <div class="page-shadow">
            <div class="page">
                <div id="main">
                    <div id="content">
                        <div id='wsite-content' class='wsite-elements wsite-not-footer'>
                            <h1 id="title">${printStrings.thankYouRequestTitle}</h1>
                            <br>

                            <!--TODO Remove list after finishing emailing functionality-->
                            <div id="donor_list">
                            <#if donors?size = 0>
                                <h2>${printStrings.thankYouRequestSubTitle}</h2>
                            <#else>
                                <#list donors as user>
                                <li> ${user.firstName}, ${user.lastName},${user.cellPhone}, ${user.id}, ${user.lastDonateDate}, ${user.address}, ${user.city}, ${user.state}, ${user.bloodGroup}<br/>
                                </#list>
                            </#if>
                            </div>
                            <!-- End of temporary list -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>