package org.bloodtorrent.dto;

import freemarker.template.utility.StringUtil;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 20
 * Time: 오전 3:23
 * To change this template use File | Settings | File Templates.
 */
public class BloodReqTest {
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyFirstName() {
        BloodReq req = new BloodReq();
        req.setFirstName(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyLastName() {
        BloodReq req = new BloodReq();
        req.setLastName(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyPhone() {
        BloodReq req = new BloodReq();
        req.setPhone(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyEmail() {
        BloodReq req = new BloodReq();
        req.setEmail(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyBloodType() {
        BloodReq req = new BloodReq();
        req.setBloodType(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyBloodVolume() {
        BloodReq req = new BloodReq();
        req.setBloodVolume(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyRequesterType() {
        BloodReq req = new BloodReq();
        req.setRequesterType(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyState() {
        BloodReq req = new BloodReq();
        req.setState(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyCity() {
        BloodReq req = new BloodReq();
        req.setCity(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyHospitalAddress() {
        BloodReq req = new BloodReq();
        req.setHospitalAddress(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenWrongFormatOfPhone() {
        BloodReq req = new BloodReq();
        req.setPhone("not a number");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenWrongFormatOfEmail() {
        BloodReq req = new BloodReq();
        req.setEmail("invalidEmail");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfFirstName() {
        BloodReq req = new BloodReq();
        req.setFirstName("123456789012345678901234567890123456");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfLastName() {
        BloodReq req = new BloodReq();
        req.setLastName("123456789012345678901234567890123456");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfCity() {
        BloodReq req = new BloodReq();
        String s = "";
        String bigS = StringUtil.leftPad(s, 256, 'a');
        req.setCity(bigS);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfHospitalAddress() {
        BloodReq req = new BloodReq();
        String s = "";
        String bigS = StringUtil.leftPad(s, 1001, 'a');
        req.setHospitalAddress(bigS);
    }
}
