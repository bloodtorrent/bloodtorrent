<html>
    <head>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=en"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
        <script type="text/javascript" language="javascript">
        //<![CDATA[
        $(function() {
            $("#birthDay").datepicker({
                showOtherMonths: true,
                selectOtherMonths: true,
                dateFormat: "dd-mm-yy"
            });
            $("#register").click(function(){
                if ($("#orginalAddress").val() != "" && $("#city").val() != "" && $("#state").val() != "") {
                    if ( ($("#lat").val() == "" || $("#lng").val() == "") || $("isMapExcuted").val() == "N") {
                        var fullAddress = $("#orginalAddress").val() + "," + $("#city").val() + "," + $("#state").val() + "," +"India";
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
                                        $("#user").submit();
                                    }else {
                                        alert("Not found: " + status);
                                    }
                                }
                            );
                    } else {
                       $("#user").submit();
                    }
                } else {
                    $("#user").submit();
                }
            });
        });
        //]]>
        </script>
    </head>
    <body>

        <form id="user" method="post" action="/user">
        <div id="title">
            <center><label><h2><u>New Account Registration as Donor</u></h2><label></center>
        </div>
        <div id="userInfo">
            <table>
                <tr>
                    <td></td>
                    <td><label>First name</label></td>
                    <td><label>Last name</label></td>
                </tr>
                <tr>
                    <td><label>Name : </label></td>
                    <td><input type="text" name="firstName" width="30" maxLength="35"/></td>
                    <td><input type="text" name="lastName" width="30" maxLength="35"/></td>
                </tr>
                <tr>
                    <td><label>E-Mail : </label></td>
                    <td><input type="text" name="email" width="30"/></td>
                    <td><label>Your email will also act as login id</label></td>
                </tr>
                <tr>
                    <td><label>Password : </label></td>
                    <td><input type="password" name="password" width="30" minLength="8" maxLength="25"/></td>
                    <td><label>(8~25 characters at least 1 digit)</label></td>
                </tr>
                <tr>
                    <td><label>Confirm Password : </label></td>
                    <td colspan="2"><input type="password" name="confirmPassword" width="30" minLength="8" maxLength="25"/></td>
                </tr>
                <tr>
                    <td colspan="3"><label>Please provide correct address so that we can suggest donation locations closer to this address.</label></td>
                </tr>
                <tr>
                    <td><label>Address : </label></td>
                    <td colspan="2"><textarea name="address" id="orginalAddress" rows="3" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td><label>City : </label></td>
                    <td><input type="text" name="city" id="city" width="30" maxLength="255"/></td>
                </tr>
                <tr>
                    <td><label>State : </label></td>
                    <td>
                        <select name="state" id="state">
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
                    </td>
                    <td>
                        <input type="button" id="popupMap" value="map"/>
                        <label id="messageLabel">Use a map to specify exact location</label>
                    </td>
                </tr>
                <tr>
                    <td><label>Cell Phone</label></td>
                    <td><input type="text" name="cellPhone" width="30" maxlength="10"/></td>
                    <td><label>(10 digits)</label></td>
                </tr>
                <tr>
                    <td><label>Blood Group:</label></td>
                    <td>
                        <select name="bloodGroup">
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
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><label>How far are you willing to travel to donate blood?</label>
                    <select name="distance">
                        <option value="5" selected>5</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="50">50</option>
                    </select> Km
                    </td>
                </tr>
                <tr>
                    <td ><label>Gender : </label> </td>
                    <td colspan="2">
                        <select name="gender">
                            <option value="female" selected>Female</option>
                            <option value="male">Male</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label>Date of Birth : </label> </td>
                    <td><input type="text" id="birthDay" name="birthDay"/> (optional)</td>
                </tr>
                <tr>
                    <td colspan="3"><label>Do you want to remain anonymous ?</label><input type="radio" name="anonymous" value="Y"/>Y <input type="radio" name="anonymous" value="N" checked/>N</td>
                </tr>
                <tr>
                    <td colspan="3">
                        <label>When did you last donate blood?</label>
                        <select name="lastDonate">
                            <option value="1" selected>within 1 month</option>
                            <option value="2">within 2 months</option>
                            <option value="3">within 3 months</option>
                            <option value="4">I can't remember</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <center>

        <input type="hidden" name="lat" id="lat" value=""/>
        <input type="hidden" name="lng" id="lng" value=""/>
        <input type="hidden" name="isMapExcuted" id="isMapExcuted" value="N"/>

        <input type="button" id="register" name="register" value="Register"/>
        <a href ="/"><input type="button" name="cancel" value="Cancel"/></a>
        </center>
        </form>

        <#include "location.ftl"/>
    </body>
</html>
