<html>
    <body>
       <h1 id="title">Thank you for posting your request.<br>
       We will get in touch soon to validate the details.</h1>
       <br>
       <input type="button" value="Home" onclick="location.href='/';"/>

       <div id="donor list">
           <#list users as user>
               <li> ${user.firstName}, ${user.lastName},${user.cellPhone}, ${user.id}, ${user.lastDonateDate}, ${user.address}, ${user.city}, ${user.state}, ${user.bloodGroup}<br/>
           </#list>
       </div>
    </body>
</html>