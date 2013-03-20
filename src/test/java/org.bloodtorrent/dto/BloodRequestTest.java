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
public class BloodRequestTest {
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyFirstName() {
        BloodRequest req = new BloodRequest();
        req.setFirstName(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyLastName() {
        BloodRequest req = new BloodRequest();
        req.setLastName(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyPhone() {
        BloodRequest req = new BloodRequest();
        req.setPhone(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyEmail() {
        BloodRequest req = new BloodRequest();
        req.setEmail(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyBloodType() {
        BloodRequest req = new BloodRequest();
        req.setBloodType(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyBloodVolumeString() {
        BloodRequest req = new BloodRequest();
        req.setBloodVolume((String) null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyBloodVolumeInteger() {
        BloodRequest req = new BloodRequest();
        req.setBloodVolume((Integer) null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyRequesterType() {
        BloodRequest req = new BloodRequest();
        req.setRequesterType(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyState() {
        BloodRequest req = new BloodRequest();
        req.setState(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyCity() {
        BloodRequest req = new BloodRequest();
        req.setCity(null);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullOrEmptyHospitalAddress() {
        BloodRequest req = new BloodRequest();
        req.setHospitalAddress(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenWrongFormatOfPhone() {
        BloodRequest req = new BloodRequest();
        req.setPhone("not a number");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenWrongFormatOfEmail() {
        BloodRequest req = new BloodRequest();
        req.setEmail("invalidEmail");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfFirstName() {
        BloodRequest req = new BloodRequest();
        req.setFirstName("123456789012345678901234567890123456");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfLastName() {
        BloodRequest req = new BloodRequest();
        req.setLastName("123456789012345678901234567890123456");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfCity() {
        BloodRequest req = new BloodRequest();
        String s = "";
        String bigS = StringUtil.leftPad(s, 256, 'a');
        req.setCity(bigS);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenExceedDigitsOfHospitalAddress() {
        BloodRequest req = new BloodRequest();
        String s = "";
        String bigS = StringUtil.leftPad(s, 1001, 'a');
        req.setHospitalAddress(bigS);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenWrongFormatOfBloodVolumeInteger() {
        BloodRequest req = new BloodRequest();
        req.setBloodVolume(0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenWrongFormatOfBloodVolumeString() {
        BloodRequest req = new BloodRequest();
        req.setBloodVolume("xx");
    }
}
