<html>
<#include "header.ftl"/>
<title>${printStrings.windowTitle}-${printStrings.menuBarRequest}</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="/css/message.css" />
<style type="text/css">
    .wsite-form-label {
        width: 120px;   /* for equal width of all labels. the longest label is 'Confirm Password'. */
    }
</style>
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
                    <div id="content">
                        <div id='wsite-content' class='wsite-elements wsite-not-footer'>
                            <form id="bloodRequestForm" method="post" action="/requestForBlood">
                                <div id="title">
                                    <h2>${printStrings.requestTitle}</h2>
                                </div>
                                <div class="message" style="display: none"></div>
                                <div id="bloodRequestInfo">
                                    <label class="wsite-form-label notLast">${printStrings.labelName}</label>
                                    <div class="form-container notLast">
                                        <input class="wsite-form-input wsite-input" type="text" name="firstName" maxLength="35"/>
                                        <label class="wsite-form-sublabel form-container">${printStrings.labelFirstName}</label>
                                    </div>
                                    <div class="form-container">
                                        <input class="wsite-form-input wsite-input" type="text" name="lastName" maxLength="35"/>
                                        <label class="wsite-form-sublabel form-container">${printStrings.labelLastName}</label>
                                    </div>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-sublabel">${printStrings.labelValidationHospital}</label>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label">${printStrings.labelHospitalAddress}</label>
                                    <textarea class="wsite-form-input wsite-input" name="hospitalAddress" id="hospitalAddress" rows="3" cols="50"></textarea>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label" for="city">${printStrings.labelCity}</label>
                                    <input class="wsite-form-input wsite-input" type="text" name="city" id="city"/>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label" for="state">${printStrings.labelState}</label>
                                    <select name="state" id="state" class="form-select" style="width: 200px;">
                                        <option value="Andhra Pradesh" selected="true">Andhra Pradesh</option>
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
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label">${printStrings.labelCellphone}</label>
                                    <input class="wsite-form-input wsite-input" type="text" name="phone" maxlength="10"/>
                                    <label class="wsite-form-sublabel">${printStrings.labelValidationCellphone}</label>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label">${printStrings.labelEmail}</label>
                                    <input class="wsite-form-input wsite-input" type="text" name="email" style="width: 250px;"/>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label">${printStrings.labelGender}</label>
                                    <select name="gender" class="form-select" style="width: 100px;">
                                        <option value="F" selected>Female</option>
                                        <option value="M">Male</option>
                                    </select>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label" for="birthday">${printStrings.labelBirth}</label>
                                    <input class="wsite-form-input wsite-input" type="text" name="birthday" id="birthday"/>
                                    <label class="wsite-form-sublabel">(optional, DD-MM-YYYY)</label>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label" for="bloodGroup">${printStrings.labelBloodGroup}</label>
                                    <select name="bloodGroup" id="bloodGroup" class="form-select" style="width: 100px;">
                                        <option value="A+" selected="true">A+</option>
                                        <option value="A-">A-</option>
                                        <option value="B+">B+</option>
                                        <option value="B-">B-</option>
                                        <option value="AB+">AB+</option>
                                        <option value="AB-">AB-</option>
                                        <option value="O+">O+</option>
                                        <option value="O-">O-</option>
                                        <option value="Unknown">Unknown</option>
                                    </select>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label" for="bloodVolume">${printStrings.labelBloodVolume}</label>
                                    <input class="wsite-form-input wsite-input" type="text" name="bloodVolume" id="bloodVolume" style="width: 100px;" maxlength="2"/>
                                    <label class="wsite-form-sublabel">${printStrings.labelValidationBloodVolume}</label>
                                    <div class="form-divider"></div>

                                    <label class="wsite-form-label" for="requesterType">${printStrings.labelRequester}</label>
                                    <input class="wsite-form-input wsite-input" type="radio" name="requesterType" id="requesterType" value="C" checked/>${printStrings.labelCareGiver}
                                    <input class="wsite-form-input wsite-input" type="radio" name="requesterType" id="requesterType" value="P"/>${printStrings.labelPatient}
                                    <div class="form-divider"></div>
                                </div>
                                <div>
                                    <a href="#" class="wsite-button wsite-button-large wsite-button-highlight" id="register"><span class="wsite-button-inner">${printStrings.labelRequestButton}</span></a>
                                    <a href="/" class="wsite-button wsite-button-large" id="cancel"><span class="wsite-button-inner">${printStrings.labelCancelButton}</span></a>
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
</body>
</html>