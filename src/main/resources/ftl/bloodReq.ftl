<html>
    <head>
    <title>Request for Blood</title>
    <script type="text/javascript">

    function goHome(){
        alert("test");
    }
    </script>
    </head>
    <body>
        <form id="bloodreqFrm" method="post" action="/bloodreq" onsubmit="validate(this)">
        <div id="bloodreqInfo">
            <h1>Request for Blood</h1><hr noshade />
            <table cellspacing="3">
                <tr>
                    <td><label>Name:</label></td>
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
                    <td><textarea name="hospitalAddress" width="30" rows="4"></textarea></td>
                </tr>
                <tr>
                    <td><label>City:</label></td>
                    <td><input type="text" name="city" width="30" value="" maxLength="30"/></td>
                </tr>
                <tr>
                    <td><label>State:</label></td>
                    <td>
                        <select name="state">
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
                    <td><label>Cell Phone:</label></td>
                    <td><input type="text" name="phone" width="30" value="" maxLength="10"/> (10 digits)</td>
                </tr>
                <tr>
                    <td><label>E-mail:</label></td>
                    <td><input type="text" name="email" width="30" value=""/></td>
                </tr>
                <tr>
                    <td><label>Gender:</label></td>
                    <td>
                        <select name="gender">
                            <option value="M" selected>Male</option>
                            <option value="F">Female</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label>Date of Birth:</label></td>
                    <td>
                        <input type="text" name="birthday" width="30" value=""/>
                        (optional)
                    </td>
                </tr>
                <tr>
                    <td><label>Blood Type:</label></td>
                    <td>
                        <select name="bloodType">
                            <option value="A+">A+</option>
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
                    <td><label>Blood Volume:</label></td>
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
        <input type="submit" name="register" value="Register"/>
        <input type="button" name="reset" value="Cancel" onClick="goHome()"/>
        </form>
    </body>
</html>