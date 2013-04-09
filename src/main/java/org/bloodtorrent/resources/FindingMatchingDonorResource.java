package org.bloodtorrent.resources;

import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;

import java.util.*;

import static org.bloodtorrent.BloodTorrentConstants.*;

public class FindingMatchingDonorResource {
    public static final double DEGREE_180 = 180.0;
    public static final int MODIFICATION_FACTOR = 60;
    public static final int DONATE_LIMIT_PERIOD = 90;
    private UsersRepository usersRepository;

    protected FindingMatchingDonorResource(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    public double getDistance(double latitudeOfDonor, double longitudeOfDonor, double latitudeOfHospital, double longitudeOfHospital) {
        double theta = longitudeOfDonor - longitudeOfHospital;
        double distance = Math.sin(degreeToRadian(latitudeOfDonor)) * Math.sin(degreeToRadian(latitudeOfHospital)) + Math.cos(degreeToRadian(latitudeOfDonor)) * Math.cos(degreeToRadian(latitudeOfHospital)) * Math.cos(degreeToRadian(theta));
        distance = Math.acos(distance);
        distance = radianToDegree(distance);

        distance = distanceToKillometer(distance);
        return distance;
    }

    private double distanceToKillometer(double distance) {
        return nauticalMilesToStatuteMile(distance) * STATUTE_MILE_TO_KILOMETER;
    }

    private double nauticalMilesToStatuteMile(double distance) {
        return distanceToNauticalMiles(distance) * NAUTICAL_MILE_TO_STATUTE_MILE;
    }

    private double distanceToNauticalMiles(double distance) {
        return distance * MODIFICATION_FACTOR;
    }

    private double degreeToRadian(double degree) {
        return (degree * Math.PI / DEGREE_180);
    }

    private double radianToDegree(double radian) {
        return (radian * DEGREE_180 / Math.PI);
    }

    public List<User> getMatchingDonors(List<User> users, double hospitalLatitude, double hospitalLongitude) {

        List<User> matchingDonors = new ArrayList<User>();

        for (User user : users) {
            if (isNearDonor(user.getLatitude(), user.getLongitude(), hospitalLatitude, hospitalLongitude, Double.parseDouble(user.getDistance()))) {
                matchingDonors.add(user);
            }
        }
        return matchingDonors;
    }

    public boolean isNearDonor(double userLatitude, double userLongitude, double hospitalLatitude, double hospitalLongitude, double distance) {
        return distance >= this.getDistance(userLatitude, userLongitude, hospitalLatitude, hospitalLongitude);
    }

    public List<User> findMatchingDonors(BloodRequest bloodRequest) throws IllegalDataException {
        List<User> donors = usersRepository.listByBloodGroupAndAfter90DaysFromLastDonateDate(bloodRequest.getBloodGroup(), bloodRequest.getLatitude(), bloodRequest.getLongitude());

        validateMatchingDonors(bloodRequest, donors);
        List<User> filteredDonors = getMatchingDonors(donors, bloodRequest.getLatitude(), bloodRequest.getLongitude());

        return filteredDonors;
    }

    private void validateMatchingDonors(BloodRequest bloodRequest, List<User> donors) throws IllegalDataException {
        for (User donor : donors) {
            if(isNotValidBloodGroup(bloodRequest.getBloodGroup(), donor)) {
                throw new IllegalDataException("The blood group should be equal to the one of request.");
            }
            if(isNotValidDonateDate(donor)) {
                throw new IllegalDataException("With all found users, the last donate date of each user should be before 90 days from today.");
            }
        }
    }

    /**
     *  Please comprehend with the following figure. :)
     * -----------------+--------
     *     NOT OK       |   OK
     * ---------|-------|--------
     *  before()|equal()| after()
     *
     */
    private boolean isNotValidDonateDate(User donor) {
        Calendar calendarCurrent = eraseTime(new Date());
        
        Calendar calendarValidDonate = eraseTime(donor.getLastDonateDate());
        calendarValidDonate.add(Calendar.DAY_OF_MONTH, DONATE_LIMIT_PERIOD);

        return !calendarCurrent.after(calendarValidDonate);
    }

    private boolean isNotValidBloodGroup(String bloodGroup, User donor) {
        return !(donor.getBloodGroup().equals(bloodGroup) || UNKNOWN_BLOOD_GROUP.equals(donor.getBloodGroup()));
    }

    private Calendar eraseTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new GregorianCalendar(year, month, day);
    }
}
