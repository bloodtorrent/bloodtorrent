<html>
<#include "header.ftl"/>
<title>${properties.windowTitle}-${properties.menuBarRequest}</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="/css/message.css" />
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=en"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript" src="/js/message.js"></script>
<script type="text/javascript" language="javascript">
    $(function() {
        /* out of scope #1
            $( "#birthday" ).datepicker({
              showOn: "button",
              buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
              buttonImageOnly: true,
              dateFormat: "dd-mm-yy"
            });
        */

        $("#bloodRequestForm").ajaxForm(function(data) {
            if(data.result && data.result == "fail") {
                showMessage(data.message);
            } else {
                hideMessage();
                $("#bloodRequestForm input[type=submit]").attr("disabled", "disabled");
                $("#bloodRequestForm input[type=button]").attr("disabled", "disabled");
                $("#successForm input[name=requestId]").val(data.requestId);
                $("#successForm").submit();
            }
        });

        $("#register").click(function(){
            if ($("#hospitalAddress").val() != "" && $("#city").val() != "" && $("#state").val() != "") {
                if ( ($("#lat").val() == "" || $("#lng").val() == "") || $("isMapExcuted").val() == "N") {
                    var fullAddress = $("#hospitalAddress").val() + "," + $("#city").val() + "," + $("#state").val() + "," +"India";
                    var geocoder = new google.maps.Geocoder();
                    geocoder.geocode(
                            {
                                'address': fullAddress
                            },
                            function(results, status) {
                                if (status == google.maps.GeocoderStatus.OK) {
                                    var loc = results[0].geometry.location;
                                    $("#lat").val(loc.lat());
                                    $("#lng").val(loc.lng());
                                    $("#bloodRequestForm").submit();
                                }else {
                                    alert("Not found: " + status);
                                }
                            }
                    );
                } else {
                    $("#bloodRequestForm").submit();
                }
            } else {
                $("#bloodRequestForm").submit();
            }
        });

    });

    function goHome(){
        location.href="/";
    }
</script>
<body>
<div class="outer-main-wrap">
    <div id="main-wrap">
        <div class="page-shadow">
            <div class="page">
                <div id="main">
                    <div id="content"><div id='wsite-content' class='wsite-elements wsite-not-footer'>
                        <form id="bloodRequestForm" method="post" action="/requestForBlood">
                            <div id="title">
                                <h2>${properties.requestTitle}</h2>
                            </div>
                            <div class="message" style="display: none"></div>
                            <div id="bloodRequestInfo">
                                <table cellspacing="3">
                                    <tr>
                                        <td><label>${properties.labelName} :</label></td>
                                        <td>
                                            <table><tr><td>${properties.labelFirstName}<br>
                                                <input type="text" name="firstName" width="30" value="" maxLength="35"/>
                                            </td>
                                                <td>${properties.labelLastName}<br>
                                                    <input type="text" name="lastName" width="30" value="" maxLength="35"/>
                                                </td></tr></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">${properties.labelValidationHospital}</td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelHospitalAddress}:</label></td>
                                        <td><textarea id="hospitalAddress" name="hospitalAddress" width="30" rows="4"></textarea></td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelCity} :</label></td>
                                        <td><input type="text" id = "city" name="city" width="30" value="" maxLength="30"/></td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelState} :</label></td>
                                        <td>
                                            <select id = "state" name="state">
                                                <option value="Andhra Pradesh">Andhra Pradesh</option>
                                                <option value="Arunachal Pradesh">Arunachal Pradesh</option>
                                                <option value="Asom (Assam)">Asom (Assam)</option>
                                                <option value="Bihar">Bihar</option>
                                                <option value="Karnataka">Karnataka</option>
                                                <option value="Kerala">Kerala</option>
                                                <option value="Chhattisgarh">Chhattisgarh</option>
                                                <option value="Goa">Goa</option>
                                                <option value="Gujarat">Gujarat</option>
                                                <option value="Haryana">Haryana</option>
                                                <option value="Himachal Pradesh">Himachal Pradesh</option>
                                                <option value="Jammu And Kashmir">Jammu And Kashmir</option>
                                                <option value="Jharkhand">Jharkhand</option>
                                                <option value="West Bengal">West Bengal</option>
                                                <option value="Madhya Pradesh">Madhya Pradesh</option>
                                                <option value="Maharashtra">Maharashtra</option>
                                                <option value="Manipur">Manipur</option>
                                                <option value="Meghalaya">Meghalaya</option>
                                                <option value="Mizoram">Mizoram</option>
                                                <option value="Nagaland">Nagaland</option>
                                                <option value="Orissa">Orissa</option>
                                                <option value="Punjab">Punjab</option>
                                                <option value="Rajasthan">Rajasthan</option>
                                                <option value="Sikkim">Sikkim</option>
                                                <option value="Tamilnadu">Tamilnadu</option>
                                                <option value="Tripura">Tripura</option>
                                                <option value="Uttarakhand (Uttaranchal)">Uttarakhand (Uttaranchal)</option>
                                                <option value="Uttar Pradesh">Uttar Pradesh</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelCellphone} :</label></td>
                                        <td><input type="text" name="phone" width="30" value="" maxLength="10"/> (10 digits)</td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelEmail} :</label></td>
                                        <td><input type="text" name="email" width="30" value=""/></td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelGender} :</label></td>
                                        <td>
                                            <select name="gender">
                                                <option value="F">Female</option>
                                                <option value="M" selected>Male</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelBirth} :</label></td>
                                        <td>
                                            <input type="text" name="birthday" id="birthday" width="30" value=""/>
                                        ${properties.labelValidationBirth}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelBloodGroup} :</label></td>
                                        <td>
                                            <select name="bloodGroup">
                                                <option value="A+">A+</option>
                                                <option value="A-">A-</option>
                                                <option value="B+">B+</option>
                                                <option value="B-">B-</option>
                                                <option value="AB+">AB+</option>
                                                <option value="AB-">AB-</option>
                                                <option value="O+">O+</option>
                                                <option value="O-">O-</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelBloodVolume} :</label></td>
                                        <td>
                                            <input type="text" name="bloodVolume" width="30" maxlength="2" value=""/>
                                        ${properties.labelValidationBloodVolume}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>${properties.labelRequester}</label></td>
                                        <td>
                                            <input type="radio" name="requesterType" value="C" checked/>${properties.labelCareGiver}
                                            <input type="radio" name="requesterType" value="P"/>${properties.labelPatient}
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div>
                                <input type="button" id="register" name="register" value="${properties.labelRegisterButton}"/>
                                <input type="button" name="reset" value="${properties.labelCancelButton}" onClick="goHome()"/>
                            </div>

                            <input type="hidden" name="lat" id="lat" value=""/>
                            <input type="hidden" name="lng" id="lng" value=""/>
                            <input type="hidden" name="isMapExcuted" id="isMapExcuted" value="N"/>
                        </form>

                        <form id="successForm" method="post" action="/requestForBlood/success">
                            <input type="hidden" name="requestId"/>
                        </form>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>