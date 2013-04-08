<html>
<#include "header.ftl"/>
<div class="outer-main-wrap">
    <div id="main-wrap">
        <div class="page-shadow">
            <div class="page">
                <div id="main">
                    <div id="content">
                        <div id='wsite-content' class='wsite-elements wsite-not-footer'>
                            <div class="title">
                                <h2 id="title">${printStrings.thankYouRequestTitle}</h2>
                            </div>
                            <label class="wsitewsite-normal-label">${printStrings.thankYouRequestSubTitle}</label>

                            <!--TODO Remain this list because it might be needed for showcase-->
                            <#--<div id="donor_list">
                            <#if donors?size = 0>
                                <h2>${printStrings.requestDonorNotFound}</h2>
                            <#else>
                                <#list donors as user>
                                <li> ${user.firstName}, ${user.lastName},${user.cellPhone}, ${user.id}, ${user.lastDonateDate}, ${user.address}, ${user.city}, ${user.state}, ${user.bloodGroup}<br/>
                                </#list>
                            </#if>
                            </div>-->
                            <!-- End of temporary list -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>