package org.bloodtorrent.repository;

import org.bloodtorrent.dto.UserBuilder;
import org.bloodtorrent.testing.unitofwork.ConfigurableIntegrationTest;
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
public class UsersRepositoryTest extends ConfigurableIntegrationTest {
    @Rule
    public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule(configuration.getDatabaseConfiguration(), User.class);

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
        user.setBirthDay("2013-03-30");
        user.setAddress("address, city, state, India");
        user.setAnonymous(false);
        repository.insert(user);
        assertNotNull(repository.get("123"));
    }

    @Test
    public void shoulFindMatchingUsers() {
        threeUsersButOnlyOneMatchingBloodGroup("O+");

        List<User> foundUsers = repository.listByBloodGroupAndAfter90DaysFromLastDonateDate("O+");

        assertThat(foundUsers.size(), is(1));
    }

    private void threeUsersButOnlyOneMatchingBloodGroup(String matchingBloodGroup) {
        Date longEnoughToDonateAgain = todayMinusDays(MIN_DAYS_LAST_DONATION + 5);
        Date tooSoonToDonateAgain = todayMinusDays(MIN_DAYS_LAST_DONATION - 1);

        User expectedMatch = user(matchingBloodGroup, longEnoughToDonateAgain, "email1@naver.com");
        User unmatchedUser = user("A+", longEnoughToDonateAgain, "email2@naver.com");
        User anotherUnmatchedUser = user(matchingBloodGroup, tooSoonToDonateAgain, "email3@naver.com");

        repository.insert(expectedMatch);
        repository.insert(unmatchedUser);
        repository.insert(anotherUnmatchedUser);
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
}
