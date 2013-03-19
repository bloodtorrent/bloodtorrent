package org.bloodtorrent.dto;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 20
 * Time: 오전 3:23
 * To change this template use File | Settings | File Templates.
 */
public class BloodReqTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyFirstName() {
        BloodReq req = new BloodReq();
        req.setFirstName(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyLastName() {
        BloodReq req = new BloodReq();
        req.setLastName(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyLocation() {
        BloodReq req = new BloodReq();
        req.setLocation(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyPhone() {
        BloodReq req = new BloodReq();
        req.setPhone(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyEmail() {
        BloodReq req = new BloodReq();
        req.setEmail(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyBloodType() {
        BloodReq req = new BloodReq();
        req.setBloodType(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyBloodVolume() {
        BloodReq req = new BloodReq();
        req.setBloodVolume(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNullOrEmptyRequesterType() {
        BloodReq req = new BloodReq();
        req.setRequesterType(null);
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
}
