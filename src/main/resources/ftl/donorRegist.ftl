<html>
    <body>
        <form id="userFrm" method="post" action="/user">
        <div id="userInfo">
            <table>
                <tr>
                    <td><label>*first name</label></td>
                    <td><input type="text" name="firstName" width="30" value="firstname" maxLength="35"/></td>
                </tr>
                <tr>
                    <td><label>*last name</label></td>
                    <td><input type="text" name="lastName" width="30" value="lastname" maxLength="35"/></td>
                </tr>
                <tr>
                    <td><label>*address</label></td>
                    <td><input type="text" name="address" width="30" value="address"/></td>
                </tr>
                <tr>
                    <td><label>*phonenumber</label></td>
                    <td><input type="text" name="phoneNumber" width="30" value="0123456789" maxLength="10"/></td>
                </tr>
                <tr>
                    <td><label>*email</label></td>
                    <td><input type="text" name="email" width="30" value="email"/></td>
                </tr>
                <tr>
                    <td><label>*password</label></td>
                    <td><input type="password" name="password" width="30" value="password" maxLength="25"/></td>
                </tr>
                <tr>
                    <td><label>gender</label></td>
                    <td>
                        <select name="gender">
                            <option value="male">male</option>
                            <option value="female">female</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label>age</label></td>
                    <td><input type="text" name="age" width="30" value="30"/></td>
                </tr>
                <tr>
                    <td><label>*bloodtype</label></td>
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
                    <td><label>*distance</label></td>
                    <td><input type="text" name="distance" width="30" value="1"/> Km</td>
                </tr>
                <tr>
                    <td colspan="2">Do you want to remain anonymous?</td>
                </tr>
                <tr>
                    <td colspan="2"><input type="radio" name="anonymous" value="Y"/>Y <input type="radio" name="anonymous" value="N" checked/>N</td>
                </tr>
            </table>
        </div>
        <input type="submit" name="register" value="Register"/>
        <input type="submit" name="cancel" value="Cancel"/>
        </form>
    </body>
</html>