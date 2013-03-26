package org.bloodtorrent.resources;

import org.bloodtorrent.dto.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 10:56
 * To change this template use File | Settings | File Templates.
 */
public class FindingMatchingDonorResource {
    private final double STATUTE_MILE_TO_KILLOMETER = 1.609344;
    private final double NAUTICAL_MILE_TO_STATUTE_MILE = 1.1515;

    public double getDistance(double latitudeOfDonor, double longitudeOfDonor, double latitudeOfHospital, double longitudeOfHospital) {
        double theta = longitudeOfDonor - longitudeOfHospital;
        double distance = Math.sin(degreeToRadian(latitudeOfDonor)) * Math.sin(degreeToRadian(latitudeOfHospital)) + Math.cos(degreeToRadian(latitudeOfDonor)) * Math.cos(degreeToRadian(latitudeOfHospital)) * Math.cos(degreeToRadian(theta));
        distance = Math.acos(distance);
        distance = radianToDegree(distance);

        distance = distanceToKillometer(distance);
        return distance;
    }

    private double distanceToKillometer(double distance) {
        return NauticalMilesToStatuteMile(distance) * STATUTE_MILE_TO_KILLOMETER;
    }

    private double NauticalMilesToStatuteMile(double distance) {
        return distanceToNauticalMiles(distance) * NAUTICAL_MILE_TO_STATUTE_MILE;
    }

    private double distanceToNauticalMiles(double distance) {
        return distance * 60;
    }

    private double degreeToRadian(double degree) {
        return (degree * Math.PI / 180.0);
    }

    private double radianToDegree(double radian) {
        return (radian * 180.0 / Math.PI);
    }

    public List<User> getMatchingDonors(List<User> users, double hospitalLatitude, double hospitalLongitude) {
        for (User user : users) {
            if (!isNearDonor(user.getLatitude(), user.getLongitude(), hospitalLatitude, hospitalLongitude, Double.parseDouble(user.getDistance()))) {
                users.remove(user);
            }
        }
        return users;
    }

    public boolean isNearDonor(double userLatitude, double userLongitude, double hospitalLatitude, double hospitalLongitude, double distance) {
        if (distance < this.getDistance(userLatitude, userLongitude, hospitalLatitude, hospitalLongitude)) {
            return false;
        } else {
            return true;
        }
    }
}
