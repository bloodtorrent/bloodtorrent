<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<script>
       function fn_sendMail(){
           var flag = false;
           var userIdArr = document.getElementsByName('uId');
           var userFirstArr = document.getElementsByName('uFirstName');
           var userLastArr = document.getElementsByName('uLastName');

           var patientFirstName = document.getElementById('pFirstName');
           var patientLastName = document.getElementById('pLastName');
           var patientBlood = document.getElementById('pBlood');
           var patientAddress = document.getElementById('pAddress');
           var patientCellPhone = document.getElementById('pCellPhone');

           for(var i = 0 ; i < 3; i++){
               if(userIdArr[i].value == "" || userFirstArr[i].value == "" || userLastArr[i].value == ""){
                   alert('Please input all of data');
                   return;
               }
           }
           if(patientFirstName.value == "" || patientLastName.value == "" || patientBlood.value == ""
                   || patientAddress.value == "" || patientCellPhone.value == "") {
               alert('Please input all of data');
               return;
           }
           var form = document.sendEmailForm;
           form.action = "/sendMailTest";
           form.method = "POST";
           form.submit();
       }

</script>
<body>
<form name="sendEmailForm">
    <h1>USER1</h1><br/>
    <B>ID : </B><input type="text" name="uId"/>
    <B>FIRST NAME : </B><input type="text" name="uFirstName"/>
    <B>LAST NAME : </B><input type="text" name="uLastName"/>
    <br/><br/>
    <h1>USER2</h1><br/>
    <B>ID : </B><input type="text" name="uId"/>
    <B>FIRST NAME : </B><input type="text" name="uFirstName"/>
    <B>LAST NAME : </B><input type="text" name="uLastName"/>
    <br/><br/>
    <h1>USER3</h1><br/>
    <B>ID : </B><input type="text" name="uId"/>
    <B>FIRST NAME : </B><input type="text" name="uFirstName"/>
    <B>LAST NAME : </B><input type="text" name="uLastName"/>

    <br/><br/>
    <h1>PATIENT</h1><br/>
    <B>FIRST NAME : </B><input type="text" id="pFirstName" name="pFirstName"/>
    <B>LAST NAME : </B><input type="text" id="pLastName"  name="pLastName"/>
    <B>BLOOD : </B><input type="text" id="pBlood"  name="pBlood"/>
    <B>CELL PHONE : </B><input type="text" id="pCellPhone"  name="pCellPhone"/>
    <B>ADDRESS : </B><input type="text" id="pAddress"  name="pAddress"/>
    <br/><br/>
    <input type="button" onclick="javascript:fn_sendMail()" value="SEND EMAIL"/>
</form>
</body>
</html>