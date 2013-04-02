package org.bloodtorrent.dto;

import freemarker.template.utility.StringUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

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
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

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
        String birthday = "04-01-2013";
        req.setBirthday(birthday);
        assertThat(req.getBirthday(), is(birthday));
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
        req.setApproval(validated);
        assertThat(req.getApproval(), is(validated));
    }

    @Test
    public void shouldGetExactlySameValueSetOnDate() {
        BloodRequest req = new BloodRequest();
        Date date = new Date();
        req.setRequestDate(date);
        assertThat(req.getRequestDate(), is(date));
    }

    @Test
    public void shouldExistFirstName() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "firstName", 0);

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "firstName");

        assertThat(2, is(constraintViolations.size()));
    }

    private BloodRequest createNewBloodRequest() {
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setFirstName("BOWON");
        bloodRequest.setLastName("KIM");
        bloodRequest.setPhone("0118427020");
        bloodRequest.setEmail("bb@samsung.com");
        bloodRequest.setGender("M");
        bloodRequest.setBloodGroup("O+");
        bloodRequest.setBloodVolume("11");
        bloodRequest.setRequesterType("C");
        bloodRequest.setBirthday("01-04-2013");
        bloodRequest.setApproval("N");
        bloodRequest.setRequestDate(new Date());
        bloodRequest.setState("texas");
        bloodRequest.setCity("seoul");
        bloodRequest.setHospitalAddress("gangnamgu 320-11");

        return bloodRequest;
    }

    @Test
    public void sizeOfFirstNameShouldUnder35() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "firstName", 36);
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "firstName");
        assertThat(1, is(constraintViolations.size()));

        setDummyString(bloodRequest, "firstName", 35);
        constraintViolations = validator.validateProperty(bloodRequest, "firstName");
        assertThat(0, is(constraintViolations.size()));
    }

    @Test
    public void shouldExistLastName() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "lastName", 0);

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "lastName");

        assertThat(2, is(constraintViolations.size()));
    }

    @Test
    public void sizeOfLastNameShouldUnder35() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "lastName", 36);
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "lastName");
        assertThat(constraintViolations.size(), is(1));

        setDummyString(bloodRequest, "lastName", 35);
        constraintViolations = validator.validateProperty(bloodRequest, "lastName");
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void shouldExistAddress() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "hospitalAddress", 0);

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "hospitalAddress");

        assertThat(2, is(constraintViolations.size()));
    }

    @Test
    public void sizeOfAddressShouldUnder1000() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "hospitalAddress", 1001);
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "hospitalAddress");
        assertThat(1, is(constraintViolations.size()));

        setDummyString(bloodRequest, "hospitalAddress", 1000);
        constraintViolations = validator.validateProperty(bloodRequest, "hospitalAddress");
        assertThat(0, is(constraintViolations.size()));
    }

    @Test
    public void shouldExistCity() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "city", 0);

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "city");

        assertThat(2, is(constraintViolations.size()));
    }

    @Test
    public void sizeOfCityShouldUnder255() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "city", 256);
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "city");
        assertThat(1, is(constraintViolations.size()));

        setDummyString(bloodRequest, "city", 255);
        constraintViolations = validator.validateProperty(bloodRequest, "city");
        assertThat(0, is(constraintViolations.size()));
    }

    @Test
    public void stateNameShouldSpecific(){
        String stateNames[] = new String[]{"Andhra Pradesh" ,"Arunachal Pradesh" ,"Asom (Assam)" ,"Bihar" ,"Karnataka" ,"Kerala" ,"Chhattisgarh" ,"Goa" ,"Gujarat" ,"Haryana" ,"Himachal Pradesh" ,"Jammu And Kashmir" ,"Jharkhand" ,"West Bengal" ,"Madhya Pradesh" ,"Maharashtra" ,"Manipur" ,"Meghalaya" ,"Mizoram" ,"Nagaland" ,"Orissa" ,"Punjab" ,"Rajasthan" ,"Sikkim" ,"Tamilnadu" ,"Tripura" ,"Uttarakhand (Uttaranchal)" ,"Uttar Pradesh"};

        BloodRequest bloodRequest = createNewBloodRequest();

        for(String stateName : stateNames){
            bloodRequest.setState(stateName);
            Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "state");
            assertThat(stateName + " is undefined", 0, is(constraintViolations.size()));
        }

        bloodRequest.setState("");
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "state");
        assertThat(3, is(constraintViolations.size()));

        bloodRequest.setState(null);
        constraintViolations = validator.validateProperty(bloodRequest, "state");
        assertThat(1, is(constraintViolations.size()));
    }




    @Test
    public void shouldExistCellPhone() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "phone", 0);

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "phone");

        assertThat(constraintViolations.size(), is(3));
    }

    @Test
    public void sizeOfCellPhoneShouldExactly10() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "phone", 9);
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "phone");
        assertThat(constraintViolations.size(), is(2));

        setDummyString(bloodRequest, "phone", 11);
        constraintViolations = validator.validateProperty(bloodRequest, "phone");
        assertThat(constraintViolations.size(), is(2));

        setDummyNumericString(bloodRequest, "phone", 10);
        constraintViolations = validator.validateProperty(bloodRequest, "phone");
        assertThat(constraintViolations.size(), is(0));
    }


    @Test
    public void shouldExistEmail() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "email", 0);

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "email");

        assertThat(3, is(constraintViolations.size()));
    }

    @Test
    public void sizeOfEmailShouldUnder100() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "email", 101);
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "email");
        assertThat(constraintViolations.size(), is(2));

        bloodRequest.setEmail("muse.kang@samsung.com");
        constraintViolations = validator.validateProperty(bloodRequest, "email");
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void shouldExistGender() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "gender", 0);

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "gender");

        assertThat(constraintViolations.size(), is(2));
    }

    @Test
    public void sizeOfGenderShouldExactly1() {
        BloodRequest bloodRequest = createNewBloodRequest();
        setDummyString(bloodRequest, "gender", 2);
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "gender");
        assertThat(constraintViolations.size(), is(1));

        setDummyString(bloodRequest, "gender", 1);
        constraintViolations = validator.validateProperty(bloodRequest, "gender");
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void shouldExistBloodGroup() {
        BloodRequest bloodRequest = createNewBloodRequest();
        bloodRequest.setBloodGroup("");

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "bloodGroup");

        assertThat(constraintViolations.size(), is(2));
    }

    @Test
    public void shouldHasSpecificStringOfBloodGroup() {
        BloodRequest bloodRequest = createNewBloodRequest();
        bloodRequest.setBloodGroup("A-");
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "bloodGroup");
        assertThat(constraintViolations.size(), is(0));

        bloodRequest.setBloodGroup("C-");
        constraintViolations = validator.validateProperty(bloodRequest, "bloodGroup");
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void shouldExistRequesterType() {
        BloodRequest bloodRequest = createNewBloodRequest();
        bloodRequest.setRequesterType("");

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "requesterType");

        assertThat(constraintViolations.size(), is(2));
    }

    @Test
    public void shouldHasSpecificStringOfRequesterType() {
        BloodRequest bloodRequest = createNewBloodRequest();
        bloodRequest.setRequesterType("C");
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "requesterType");
        assertThat(constraintViolations.size(), is(0));

        bloodRequest.setRequesterType("P");
        constraintViolations = validator.validateProperty(bloodRequest, "requesterType");
        assertThat(constraintViolations.size(), is(0));

        bloodRequest.setRequesterType("X");
        constraintViolations = validator.validateProperty(bloodRequest, "requesterType");
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void shouldExistBloodVolume() {
        BloodRequest bloodRequest = createNewBloodRequest();
        bloodRequest.setBloodVolume("");

        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "bloodVolume");

        assertThat(constraintViolations.size(), is(2));
    }

    @Test
    public void shouldHasSpecificStringOfBloodVolume() {
        BloodRequest bloodRequest = createNewBloodRequest();
        bloodRequest.setBloodVolume("90");
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "bloodVolume");
        assertThat(constraintViolations.size(), is(0));

        bloodRequest.setBloodVolume("0");
        constraintViolations = validator.validateProperty(bloodRequest, "bloodVolume");
        assertThat(constraintViolations.size(), is(1));

        bloodRequest.setBloodVolume("100");
        constraintViolations = validator.validateProperty(bloodRequest, "bloodVolume");
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void shouldBirthdayMatchWithDateFormat() {
        BloodRequest bloodRequest = createNewBloodRequest();
        bloodRequest.setBirthday("11-04-1977");
        Set<ConstraintViolation<BloodRequest>> constraintViolations = validator.validateProperty(bloodRequest, "birthday");
        assertThat(0, is(constraintViolations.size()));

        bloodRequest.setBirthday("1333-19-99");
        constraintViolations = validator.validateProperty(bloodRequest, "birthday");
        assertThat(1, is(constraintViolations.size()));

        bloodRequest.setBirthday("13-19-1199");
        constraintViolations = validator.validateProperty(bloodRequest, "birthday");
        assertThat(1, is(constraintViolations.size()));

        bloodRequest.setBirthday("13/12/1999");
        constraintViolations = validator.validateProperty(bloodRequest, "birthday");
        assertThat(1, is(constraintViolations.size()));

        bloodRequest.setBirthday("11121981");
        constraintViolations = validator.validateProperty(bloodRequest, "birthday");
        assertThat(1, is(constraintViolations.size()));

        bloodRequest.setBirthday("");
        constraintViolations = validator.validateProperty(bloodRequest, "birthday");
        assertThat(0, is(constraintViolations.size()));

        bloodRequest.setBirthday(null);
        constraintViolations = validator.validateProperty(bloodRequest, "birthday");
        assertThat(0, is(constraintViolations.size()));
    }




    private <T> void setDummyString(T t, String property, int num) {
        String dummyText = makeDummyString(num);
        invokeMethod(t, property, dummyText);
    }

    private <T> void setDummyNumericString(T t, String property, int num) {
        String dummyNumericText = makeDummyNumericString(num);
        invokeMethod(t, property, dummyNumericText);
    }

    private <T> void invokeMethod(T t, String property, String dummyText) {
        for (Method method : t.getClass().getMethods()) {
            String methodName = method.getName();
            if(methodName.startsWith("set") && methodName.toLowerCase().endsWith(property.toLowerCase())) {
                try {
                    method.invoke(t, dummyText);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String makeDummyString(int num, String dummyChar) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < num ; i++){
            stringBuilder.append(dummyChar);
        }
        return stringBuilder.toString();
    }

    private String makeDummyString(int num) {
        return makeDummyString(num, "*");
    }

    private String makeDummyNumericString(int num) {
        return makeDummyString(num, "1");
    }
}
