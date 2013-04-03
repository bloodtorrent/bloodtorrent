package org.bloodtorrent.repository;

import org.bloodtorrent.dto.UserBuilder;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.junit.Before;
import org.junit.Rule;
import org.bloodtorrent.dto.User;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.bloodtorrent.repository.UsersRepository.MIN_DAYS_LAST_DONATION;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 11:54
 * To change this template use File | Settings | File Templates.
 */
public class UsersRepositoryTest{
    private static final double TEST_DEFAULT_DONOR_LATITUDE = 32.5999;
    private static final double TEST_DEFAULT_DONOR_LONGITUDE = 76.0154;
    private static final double TEST_DEFAULT_HOSPITAL_LATITUDE = 32.3793;
    private static final double TEST_DEFAULT_HOSPITAL_LONGITUDE = 75.6720;

    @Rule
    public UnitOfWorkRule unitOfWorkRule = UnitOfWorkRule.getInstance();

    private UsersRepository repository;

    @Before
    public void setup(){
        repository = new UsersRepository(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void shouldBeExistAfterInsertUser() throws Exception {
        User user = new User();
        String id = "123";
        user.setId(id);
        user.setBirthday("2013-03-30");
        user.setAddress("address, city, state, India");
        user.setAnonymous(false);
        repository.insert(user);
        assertNotNull(repository.get("123"));
    }

    @Test
    public void shouldFindMatchingUsers() {
        fourUsersButOnlyTwoMatchingBloodGroup("O+");

        List<User> foundUsers = repository.listByBloodGroupAndAfter90DaysFromLastDonateDate("O+", TEST_DEFAULT_HOSPITAL_LATITUDE, TEST_DEFAULT_HOSPITAL_LONGITUDE );

        assertThat(foundUsers.size(), is(2));
    }

    @Test
    public void shouldFindMatchingUserForLocation(){
        twoUsersButOneMatchingForDistance();
        List<User> foundUsers = repository.listByBloodGroupAndAfter90DaysFromLastDonateDate("O+", TEST_DEFAULT_HOSPITAL_LATITUDE, TEST_DEFAULT_HOSPITAL_LONGITUDE );

        assertThat(foundUsers.size(), is(1));
    }

    private void fourUsersButOnlyTwoMatchingBloodGroup(String matchingBloodGroup) {
        Date longEnoughToDonateAgain = todayMinusDays(MIN_DAYS_LAST_DONATION + 5);
        Date tooSoonToDonateAgain = todayMinusDays(MIN_DAYS_LAST_DONATION );
        Date timeToDonateAgain = todayMinusDays(MIN_DAYS_LAST_DONATION +1 );

        User expectedMatch = user(matchingBloodGroup, longEnoughToDonateAgain, "email1@bloodtorrent.com");
        User unmatchedUser = user("A+", longEnoughToDonateAgain, "email2@bloodtorrent.com");
        User anotherUnmatchedUser = user(matchingBloodGroup, tooSoonToDonateAgain, "email3@bloodtorrent.com");
        User anotherExpectedMatch = user(matchingBloodGroup, timeToDonateAgain, "email4@bloodtorrent.com");

        repository.insert(expectedMatch);
        repository.insert(unmatchedUser);
        repository.insert(anotherUnmatchedUser);
        repository.insert(anotherExpectedMatch);
    }

    private void twoUsersButOneMatchingForDistance(){

        Date longEnoughToDonateAgain = todayMinusDays(MIN_DAYS_LAST_DONATION + 5);

        double longEnoughLatitudeToDonate = TEST_DEFAULT_DONOR_LATITUDE;
        double longEnoughLongitudeToDonate = TEST_DEFAULT_DONOR_LONGITUDE;

        double notEnoughLatitudeToDonate = 33.19999;

        User expectedMatch = user("O+", longEnoughToDonateAgain, "email5@bloodtorrent.com", longEnoughLatitudeToDonate, longEnoughLongitudeToDonate);
        User unmatchedUser = user("O+", longEnoughToDonateAgain, "email6@bloodtorrent.com", notEnoughLatitudeToDonate, longEnoughLongitudeToDonate);

        repository.insert(expectedMatch);
        repository.insert(unmatchedUser);
    }

    private Date todayMinusDays(Integer numberOfDays) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -numberOfDays);
        return calendar.getTime();
    }

    private User user(String bloodGroup, Date lastDonateDate, String email) {
        return new UserBuilder(email)
                                .withBloodGroup(bloodGroup)
                                .withLastDonateDate(lastDonateDate)
                                .build();
    }

    private User user(String bloodGroup, Date lastDonateDate, String email, double latitude, double longitude) {
        return new UserBuilder(email)
                .withBloodGroup(bloodGroup)
                .withLastDonateDate(lastDonateDate)
                .withLatitude(latitude)
                .withLongitude(longitude)
                .build();
    }
}
