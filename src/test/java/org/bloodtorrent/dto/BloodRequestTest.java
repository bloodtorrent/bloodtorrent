package org.bloodtorrent.dto;

import freemarker.template.utility.StringUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 20
 * Time: 오전 3:23
 * To change this template use File | Settings | File Templates.
 */
public class BloodRequestTest {
    @Test
    public void shouldGetExactlySameValueSetOnFirstName() {
        BloodRequest req = new BloodRequest();
        String firstName = "John";
        req.setFirstName(firstName);
        assertThat(req.getFirstName(), is(firstName));
    }
    @Test
    public void shouldGetExactlySameValueSetOnLastName() {
        BloodRequest req = new BloodRequest();
        String lastName = "Harris";
        req.setLastName(lastName);
        assertThat(req.getLastName(), is(lastName));
    }
    @Test
    public void shouldGetExactlySameValueSetOnHospitalAddress() {
        BloodRequest req = new BloodRequest();
        String hospitalAddress = "320-11, Gangnam-gu";
        req.setHospitalAddress(hospitalAddress);
        assertThat(req.getHospitalAddress(), is(hospitalAddress));
    }
    @Test
    public void shouldGetExactlySameValueSetOnCity() {
        BloodRequest req = new BloodRequest();
        String city = "Seoul";
        req.setCity(city);
        assertThat(req.getCity(), is(city));
    }
    @Test
    public void shouldGetExactlySameValueSetOnState() {
        BloodRequest req = new BloodRequest();
        String state = "Gyunggi-do";
        req.setState(state);
        assertThat(req.getState(), is(state));
    }
    @Test
    public void shouldGetExactlySameValueSetOnPhone() {
        BloodRequest req = new BloodRequest();
        String phone = "0101234567";
        req.setPhone(phone);
        assertThat(req.getPhone(), is(phone));
    }
    @Test
    public void shouldGetExactlySameValueSetOnEmail() {
        BloodRequest req = new BloodRequest();
        String email = "id@domain.com";
        req.setEmail(email);
        assertThat(req.getEmail(), is(email));
    }
    @Test
    public void shouldGetExactlySameValueSetOnGender() {
        BloodRequest req = new BloodRequest();
        String gender = "M";
        req.setGender(gender);
        assertThat(req.getGender(), is(gender));
    }
    @Test
    public void shouldGetExactlySameValueSetOnBirthday() {
        BloodRequest req = new BloodRequest();
        Date birthday = new Date();
        req.setBirthday(birthday);
        assertThat(req.getBirthday(), is(birthday));
    }

    @Test
     public void shouldGetExactlySameValueSetOnBloodType() {
        BloodRequest req = new BloodRequest();
        String bloodType = "A+";
        req.setBloodType(bloodType);
        assertThat(req.getBloodType(), is(bloodType));
    }

    @Test
     public void shouldGetExactlySameValueSetOnBloodVolume() {
        BloodRequest req = new BloodRequest();
        String bloodVolume = "11";
        req.setBloodVolume(bloodVolume);
        assertThat(req.getBloodVolume(), is(11));
    }

    @Test
    public void shouldGetExactlySameValueSetOnRequester() {
        BloodRequest req = new BloodRequest();
        String requester = "C";
        req.setRequesterType(requester);
        assertThat(req.getRequesterType(), is(requester));
    }

    @Test
    public void shouldGetExactlySameValueSetOnValidated() {
        BloodRequest req = new BloodRequest();
        String validated = "N";
        req.setValidated(validated);
        assertThat(req.getValidated(), is(validated));
    }

    @Test
    public void shouldGetExactlySameValueSetOnDate() {
        BloodRequest req = new BloodRequest();
        Date date = new Date();
        req.setDate(date);
        assertThat(req.getDate(), is(date));
    }


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
