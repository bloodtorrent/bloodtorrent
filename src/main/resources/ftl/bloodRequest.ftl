<html>
    <head>
    <title>Request for Blood</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <link rel="stylesheet" href="/css/message.css" />
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=en"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
    <script src="/js/message.js"></script>
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
                $(".message").text(data.message);
                $(".message").hide().slideDown();
            } else {
                $("#bloodRequestForm input[type=submit]").attr("disabled", "disabled");
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
                                'address': fullAddress,
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
    </head>
    <body>
        <form id="bloodRequestForm" method="post" action="/requestForBlood">
        <div id="title">
            <h2>Request for Blood</h2>
        </div>
        <div class="message" style="display: none"></div>
        <div id="bloodRequestInfo">
            <table cellspacing="3">
                <tr>
                    <td><label>Name :</label></td>
                    <td>
                        <table><tr><td>First name<br>
                            <input type="text" name="firstName" width="30" value="" maxLength="35"/>
                        </td>
                        <td>Last name<br>
                            <input type="text" name="lastName" width="30" value="" maxLength="35"/>
                        </td></tr></table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">Please provide correct hospital or blood bank address so that we can find out matched donors.</td>
                </tr>
                <tr>
                    <td><label>Hospital or<br>Blood bank address:</label></td>
                    <td><textarea id="hospitalAddress" name="hospitalAddress" width="30" rows="4"></textarea></td>
                </tr>
                <tr>
                    <td><label>City :</label></td>
                    <td><input type="text" id = "city" name="city" width="30" value="" maxLength="30"/></td>
                </tr>
                <tr>
                    <td><label>State :</label></td>
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
                    <td><label>Cell Phone :</label></td>
                    <td><input type="text" name="phone" width="30" value="" maxLength="10"/> (10 digits)</td>
                </tr>
                <tr>
                    <td><label>E-mail :</label></td>
                    <td><input type="text" name="email" width="30" value=""/></td>
                </tr>
                <tr>
                    <td><label>Gender :</label></td>
                    <td>
                        <select name="gender">
                            <option value="F">Female</option>
                            <option value="M" selected>Male</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label>Date of Birth :</label></td>
                    <td>
                        <input type="text" name="birthday" id="birthday" width="30" value=""/>
                        (optional, DD-MM-YYYY)
                    </td>
                </tr>
                <tr>
                    <td><label>Blood Group :</label></td>
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
                    <td><label>Blood Volume :</label></td>
                    <td>
                        <input type="text" name="bloodVolume" width="30" maxlength="2" value=""/>
                        Unit (1~99 units)
                    </td>
                </tr>
                <tr>
                    <td><label>Requester</label></td>
                    <td>
                        <input type="radio" name="requesterType" value="C" checked/>Care-giver
                        <input type="radio" name="requesterType" value="P"/>Patient
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <input type="button" id="register" name="register" value="Register"/>
            <input type="button" name="reset" value="Cancel" onClick="goHome()"/>
        </div>

        <input type="hidden" name="lat" id="lat" value=""/>
        <input type="hidden" name="lng" id="lng" value=""/>
        <input type="hidden" name="isMapExcuted" id="isMapExcuted" value="N"/>
        </form>

        <form id="successForm" method="post" action="/requestForBlood/success">
            <input type="hidden" name="requestId"/>
        </form>
    </body>
</html>