<html>
    <head>

    </head>
    <body>
        <form id="bloodreqFrm" method="post" action="/bloodreq" onsubmit="validate(this)">
        <div id="bloodreqInfo">
            <h1>Request for Blood</h1><hr noshade />
            <table cellspacing="3">
                <tr>
                    <td><label>*Name:</label></td>
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
                    <td><label>*Location:</label></td>
                    <td><input type="text" name="location" width="30" value=""/></td>
                </tr>
                <tr>
                    <td><label>*Phone Number:</label></td>
                    <td><input type="text" name="phone" width="30" value="" maxLength="10"/></td>
                </tr>
                <tr>
                    <td><label>*E-mail:</label></td>
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
                    <td><label>Age:</label></td>
                    <td>
                        <input type="text" name="age" width="30" value=""/>
                        Only numbers are allowed
                    </td>
                </tr>
                <tr>
                    <td><label>*Blood Type:</label></td>
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
                    <td><label>*Blood Volume:</label></td>
                    <td>
                        <input type="text" name="bloodVolume" width="30" maxlength="2" value=""/>
                        Unit (1~99 units)
                    </td>
                </tr>
                <tr>
                    <td><label>*Requester</label></td>
                    <td>
                        <input type="radio" name="requesterType" value="C"/>Care-giver
                        <input type="radio" name="requesterType" value="P"/>Patient
                    </td>
                </tr>
            </table>
        </div>
        <input type="submit" name="register" value="Register"/>
        <input type="reset" name="reset" value="Cancel"/>
        </form>
    </body>
</html>