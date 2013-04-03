package org.bloodtorrent.resources;

import org.bloodtorrent.BloodTorrentConstants;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.view.ResultView;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.bloodtorrent.BloodTorrentConstants.*;
/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 1:56
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class UsersResourceTest {


    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    private UsersResource usersResource;

    @Mock
    private UsersRepository usersRepository;

    @Before
    public void setUpResource() {
        usersResource = new UsersResource(usersRepository);
    }

    private UsersResource.PotentialDonor createNewPotentialDonor() {
        UsersResource.PotentialDonor potentialDonor = new UsersResource.PotentialDonor();
        potentialDonor.setId("bloodtorrent@naver.com");
        potentialDonor.setPassword("passw0rd");
        potentialDonor.setFirstName("Blood");
        potentialDonor.setLastName("Torrent");
        potentialDonor.setCellPhone("0123456789");
        potentialDonor.setGender("male");
        potentialDonor.setBloodGroup("A+");
        potentialDonor.setAnonymous(false);
        potentialDonor.setAddress("BR Mehta Ln");
        potentialDonor.setCity("New Delhi");
        potentialDonor.setState("Bihar");
        potentialDonor.setDistance("10");
        potentialDonor.setBirthday("18-03-1980");
        potentialDonor.setLatitude(17.458418734757736);
        potentialDonor.setLongitude(78.33536359287109);
        return potentialDonor;
    }

    @Test
    public void shouldRegisterDonorThenReturnResultView() {
        User user = createNewPotentialDonor();
        Map<String, Object> jsonMap = usersResource.registerDonor(user.getFirstName(), user.getLastName(), user.getId(), user.getPassword()
                , user.getPassword(), user.getAddress(), user.getCity(), user.getState(), user.getCellPhone(), user.getBloodGroup()
                , user.getDistance(), user.getGender(), user.getBirthday(), user.isAnonymous(), user.getLatitude(), user.getLongitude(), "1");

        assertThat(jsonMap.get("result"), hasToString("success"));
    }

    @Test
    public void shouldOccurEmailDuplicationMessageOnceUsingTakenEmailAddress(){
        when(usersRepository.get("bloodtorrent@naver.com")).thenReturn(createNewPotentialDonor());
        UsersResource.PotentialDonor donor = createNewPotentialDonor();
        Map<String, Object> jsonMap = usersResource.registerDonor(donor.getFirstName(), donor.getLastName(), donor.getId(), donor.getPassword()
                , donor.getPassword(), donor.getAddress(), donor.getCity(), donor.getState(), donor.getCellPhone(), donor.getBloodGroup()
                , donor.getDistance(), donor.getGender(), donor.getBirthday(), donor.isAnonymous(), donor.getLatitude(), donor.getLongitude(), "1");

        assertThat(jsonMap.get(JSON_RESULT_KEY), hasToString(JSON_FAIL_VALUE));
        assertThat(jsonMap.get(JSON_MESSAGE_KEY), hasToString(UsersResource.EMAIL_DUPLICATION_MESSAGE));
    }

    @Test
    public void shouldOccurConfirmMessageOnceThereAreDifferentPasswords() {
        String password = "passw0rd";
        String confirmPassword = "password";
        UsersResource.PotentialDonor donor = createNewPotentialDonor();

        Map<String, Object> jsonMap = usersResource.registerDonor(donor.getFirstName(), donor.getLastName(), donor.getId(), password
                , confirmPassword, donor.getAddress(), donor.getCity(), donor.getState(), donor.getCellPhone(), donor.getBloodGroup()
                , donor.getDistance(), donor.getGender(), donor.getBirthday(), donor.isAnonymous(), donor.getLatitude(), donor.getLongitude(), "1");

        assertThat(jsonMap.get(JSON_RESULT_KEY), hasToString(JSON_FAIL_VALUE));
        assertThat(jsonMap.get(JSON_MESSAGE_KEY), hasToString(UsersResource.PASSWORD_DIFFERENT_MESSAGE));
    }

    @Test
    public void shouldShowErrorMessageOnceProvidingWrongInformation() {
        UsersResource.PotentialDonor donor = createNewPotentialDonor();
        donor.setBirthday("20120101");

        Map<String, Object> jsonMap = usersResource.registerDonor(donor.getFirstName(), donor.getLastName(), donor.getId(), donor.getPassword()
                , donor.getPassword(), donor.getAddress(), donor.getCity(), donor.getState(), donor.getCellPhone(), donor.getBloodGroup()
                , donor.getDistance(), donor.getGender(), donor.getBirthday(), donor.isAnonymous(), donor.getLatitude(), donor.getLongitude(), "1");

        assertThat(jsonMap.get(JSON_RESULT_KEY), hasToString(JSON_FAIL_VALUE));
        assertThat(jsonMap.get(JSON_MESSAGE_KEY), notNullValue());
        assertThat(jsonMap.get(JSON_MESSAGE_KEY), instanceOf(String.class));
    }

    @Test
    public void shouldProvideErrorMessageOnceBirthdayIsInvalid() {
        UsersResource.PotentialDonor donor = createNewPotentialDonor();
        donor.setBirthday("31-02-1910");

        Map<String, Object> jsonMap = usersResource.registerDonor(donor.getFirstName(), donor.getLastName(), donor.getId(), donor.getPassword()
                , donor.getPassword(), donor.getAddress(), donor.getCity(), donor.getState(), donor.getCellPhone(), donor.getBloodGroup()
                , donor.getDistance(), donor.getGender(), donor.getBirthday(), donor.isAnonymous(), donor.getLatitude(), donor.getLongitude(), "1");

        assertThat(jsonMap.get(JSON_RESULT_KEY), hasToString(JSON_FAIL_VALUE));
        assertThat(jsonMap.get(JSON_MESSAGE_KEY), hasToString(PLEASE_CHECK + "Date of birth."));
    }

    @Test
    public void shouldCheckCalculatingDonationDate(){
        User user = createNewPotentialDonor();

        // TODO: Why is the deprecated constructor called in the following? (Scott)
        //1. within 1 month
        Date today = new Date(113, 2, 26);
        usersResource.calculateLastDonateDate(today, "31", user);
        assertThat(user.getLastDonateDate(), is(new Date(113,1,23)));
        //2. 2month ago
        usersResource.calculateLastDonateDate(today, "61", user);
        assertThat(user.getLastDonateDate(), is(new Date(113,0,24)));
        //3. 3month ago
        usersResource.calculateLastDonateDate(today, "91", user);
        assertThat(user.getLastDonateDate(), is(new Date(112,11,25)));
        //4. can not remember
        usersResource.calculateLastDonateDate(today, "91", user);
        assertThat(user.getLastDonateDate(), is(new Date(112,11,25)));
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
