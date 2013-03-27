package org.bloodtorrent.resources;

import org.bloodtorrent.dto.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 10:57
 * To change this template use File | Settings | File Templates.
 */
public class FindingMatchingDonorResourceTest {
    private final double ACCEPTANCE_DIFFERENT_KILLOMETER = 1.0;

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

        FindingMatchingDonorResource resource = new FindingMatchingDonorResource();
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

        FindingMatchingDonorResource resource = new FindingMatchingDonorResource();
        double distance = resource.getDistance(donor.getLatitude(), donor.getLongitude(), hospitalLatitude, hospitalLongitude);
        assertThat(isInMarginOfError(distance, realDistance), is(true));
    }


    @Test
    public void shouldCheckMatchingDonorRealDistanceBetween10To20() {
        double hospitalLatitude = 8.0834;
        double hospitalLongitude = 77.5473;

        FindingMatchingDonorResource resource = new FindingMatchingDonorResource();

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
        user2.setDistance("50");
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

        FindingMatchingDonorResource resource = new FindingMatchingDonorResource();

        assertThat(resource.getMatchingDonors(users, hospitalLatitude, hospitalLongitude).size(), is(3));
    }

    private boolean isInMarginOfError(double calcDistance, double realDistance) {
        if (calcDistance <= realDistance + ACCEPTANCE_DIFFERENT_KILLOMETER
                && calcDistance >= realDistance - ACCEPTANCE_DIFFERENT_KILLOMETER) {
            return true;
        } else {
            return false;
        }
    }
}
