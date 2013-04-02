<html>
    <body>
       <h1 id="title">Thank you for posting your request.<br>
       We will get in touch soon to validate the details.</h1>
       <br>
       <input type="button" value="Home" onclick="location.href='/';"/>

       <!--TODO Remove list after finishing emailing functionality-->
       <div id="donor_list">
         <#if donors?size = 0>
           <h2>There is no matching donor.</h2>
         <#else>
           <#list donors as user>
               <li> ${user.firstName}, ${user.lastName},${user.cellPhone}, ${user.id}, ${user.lastDonateDate}, ${user.address}, ${user.city}, ${user.state}, ${user.bloodGroup}<br/>
           </#list>
         </#if>
       </div>
       <!-- End of temporary list -->
    </body>
</html>