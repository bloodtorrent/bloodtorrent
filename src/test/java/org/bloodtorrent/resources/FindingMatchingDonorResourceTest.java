package org.bloodtorrent.resources;

import org.apache.commons.lang3.time.DateUtils;
import org.bloodtorrent.BloodTorrentConstants;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 10:57
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindingMatchingDonorResourceTest implements BloodTorrentConstants {
    private static final String TEST_DEFAULT_BLOOD_GROUP = "A+";
    private static final Date TEST_DEFAULT_LAST_DONATE_DATE = DateUtils.addDays(new Date(), -100);
    private static final String TEST_DEFAULT_STATE = "Andhra Pradesh";

    private final double ACCEPTANCE_DIFFERENT_KILLOMETER = 1.0;

    @Mock
    UsersRepository usersRepository;



    @Mock
    private NotifyDonorSendEmailResource mailResource;

    private FindingMatchingDonorResource resource;

    @Before
    public void setUpResource() {
        initMocks(this);
        resource = new FindingMatchingDonorResource(usersRepository);
        mailResource = new NotifyDonorSendEmailResource();
    }

    @Test
    public void shouldExactResultForIndiaNorthPosition() {
        //Donor Location : Himachal Pradesh 176306 India
        User donor = new User();
        donor.setLatitude(32.5999);
        donor.setLongitude(76.0154);

        //Hospital Location : RSD Hospital Road Ranjit Sagar Dam Colony, Punjab 145029, India
        double hospitalLatitude = 32.3793;
        double hospitalLongitude = 75.6720;

        final double realDistance = 40.5333;

        double distance = resource.getDistance(donor.getLatitude(), donor.getLongitude(), hospitalLatitude, hospitalLongitude);
        assertThat(isInMarginOfError(distance, realDistance), is(true));
    }

    @Test
    public void shouldExactResultForIndiaSouthPosition() {
        //Donor Location : National Highway 47 Kuzhithurai, Tamil Nadu, India
        User donor = new User();
        donor.setLatitude(8.32196);
        donor.setLongitude(77.19269);

        //Hospital Location : Goverment Hospital, Kanyakumari, Tamil Nadu, India
        double hospitalLatitude = 8.0834;
        double hospitalLongitude = 77.5473;

        final double realDistance = 47.2460;

        double distance = resource.getDistance(donor.getLatitude(), donor.getLongitude(), hospitalLatitude, hospitalLongitude);
        assertThat(isInMarginOfError(distance, realDistance), is(true));
    }


    @Test
    public void shouldCheckMatchingDonorRealDistanceBetween10To20() {
        double hospitalLatitude = 8.0834;
        double hospitalLongitude = 77.5473;

        User user1 = new User();
        user1.setLatitude(8.18);
        user1.setLongitude(77.59);

        user1.setDistance("50");
        assertThat(resource.isNearDonor(user1.getLatitude(), user1.getLongitude(), hospitalLatitude, hospitalLongitude, Double.parseDouble(user1.getDistance())), is(true));

        user1.setDistance("30");
        assertThat(resource.isNearDonor(user1.getLatitude(), user1.getLongitude(), hospitalLatitude, hospitalLongitude, Double.parseDouble(user1.getDistance())), is(true));

        user1.setDistance("20");
        assertThat(resource.isNearDonor(user1.getLatitude(), user1.getLongitude(), hospitalLatitude, hospitalLongitude, Double.parseDouble(user1.getDistance())), is(true));

        user1.setDistance("10");
        assertThat(resource.isNearDonor(user1.getLatitude(), user1.getLongitude(), hospitalLatitude, hospitalLongitude, Double.parseDouble(user1.getDistance())), is(false));
    }

    @Test
    public void shouldSelectMatchingDonors() {
        List<User> users = new ArrayList<User>();

        User user1 = new User();
        user1.setLatitude(8.23);
        user1.setLongitude(77.21);
        user1.setDistance("50");
        user1.setId("dornor1@email.com");

        User user2 = new User();
        user2.setLatitude(8.29);
        user2.setLongitude(77.84);
        user2.setDistance("20");
        user2.setId("dornor2@email.com");

        User user3 = new User();
        user3.setLatitude(8.18);
        user3.setLongitude(77.59);
        user3.setDistance("20");
        user3.setId("donor3@email.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);

        double hospitalLatitude = 8.0834;
        double hospitalLongitude = 77.5473;

        assertThat(resource.getMatchingDonors(users, hospitalLatitude, hospitalLongitude).size(), is(2));
    }

    private boolean isInMarginOfError(double calcDistance, double realDistance) {
        if (calcDistance <= realDistance + ACCEPTANCE_DIFFERENT_KILLOMETER
                && calcDistance >= realDistance - ACCEPTANCE_DIFFERENT_KILLOMETER) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void bloodGroupShouldBeEqualOrUnknownForAllMatchingDonors() throws IllegalDataException {
        String bloodGroup = "O-";
        when(usersRepository.listByBloodGroupAndAfter90DaysFromLastDonateDate(bloodGroup))
                .thenReturn(Arrays.asList(createUser(bloodGroup), createUser(UNKNOWN_BLOOD_GROUP)));

        BloodRequest bloodRequest = createBloodRequest();
        List<User> donors = resource.findMatchingDonors(bloodRequest);

        bloodRequest.setBloodGroup(bloodGroup);

        for (User donor : donors) {
            assertThat(donor.getBloodGroup(), anyOf(is(bloodGroup), is(UNKNOWN_BLOOD_GROUP)));
        }
    }


    @Test
    public void lastDonateDateShouldBeBefore90DaysFromTodayForAllMatchingDonors() throws IllegalDataException {
        String bloodGroup = "AB-";
        when(usersRepository.listByBloodGroupAndAfter90DaysFromLastDonateDate(bloodGroup))
                .thenReturn(Arrays.asList(createUser(bloodGroup, -91)));
        BloodRequest bloodRequest = createBloodRequest();
        bloodRequest.setBloodGroup(bloodGroup);

        List<User> donors = resource.findMatchingDonors(bloodRequest);
        Calendar currentCal = eraseTime(new Date());
        for (User donor : donors) {
            Calendar lastDonateDateCal = eraseTime(donor.getLastDonateDate());
            lastDonateDateCal.add(Calendar.DAY_OF_YEAR, 90);
            assertThat(lastDonateDateCal, lessThan(currentCal));
        }
    }

    @Test(expected = IllegalDataException.class)
    public void shouldThrowExceptionWhen90DaysDifferent() throws IllegalDataException {
        String bloodGroup = "O-";
        when(usersRepository.listByBloodGroupAndAfter90DaysFromLastDonateDate(bloodGroup))
                .thenReturn(Arrays.asList(createUser(bloodGroup, -90)));
        BloodRequest bloodRequest = createBloodRequest();
        bloodRequest.setBloodGroup(bloodGroup);
        resource.findMatchingDonors(bloodRequest);
    }

    @Test
    public void stateShouldBeEqualBetweenDonorAndHospital() throws IllegalDataException {
        String hospitalState = "Bihar";
        when(usersRepository.listByBloodGroupAndAfter90DaysFromLastDonateDate(TEST_DEFAULT_BLOOD_GROUP))
                .thenReturn(Arrays.asList(createUser(TEST_DEFAULT_BLOOD_GROUP, hospitalState)
                    , createUser()));
        BloodRequest bloodRequest = createBloodRequest();
        bloodRequest.setState(hospitalState);
        List<User> donors = resource.findMatchingDonors(bloodRequest);
        for (User donor : donors) {
            assertThat(donor.getState(), is(hospitalState));
        }
    }

    private Calendar eraseTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new GregorianCalendar(year, month, day);
    }

    private User createUser(String bloodGroup, String state) {
        User user = createUser();
        user.setBloodGroup(bloodGroup);
        user.setState(state);
        return user;
    }

    private User createUser(String bloodGroup, int offsetFromToday) {
        User user = createUser();
        user.setBloodGroup(bloodGroup);
        user.setLastDonateDate(DateUtils.addDays(new Date(), offsetFromToday));
        return user;
    }

    private User createUser(String bloodGroup) {
        User user = createUser();
        user.setBloodGroup(bloodGroup);
        return user;
    }
    
    private User createUser() {
        User user = new User();
        user.setBloodGroup(TEST_DEFAULT_BLOOD_GROUP);
        user.setLastDonateDate(TEST_DEFAULT_LAST_DONATE_DATE);
        user.setState(TEST_DEFAULT_STATE);
        return user;
    }

    private BloodRequest createBloodRequest() {
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setBloodGroup(TEST_DEFAULT_BLOOD_GROUP);
        bloodRequest.setState(TEST_DEFAULT_STATE);
        return bloodRequest;
    }

}
