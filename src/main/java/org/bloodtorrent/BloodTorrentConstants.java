package org.bloodtorrent;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/27/13
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BloodTorrentConstants {
    String UNKNOWN_BLOOD_GROUP = "Unknown";
    String JSON_RESULT_KEY = "result";
    String JSON_MESSAGE_KEY = "message";
    String JSON_SUCCESS_VALUE = "success";
    String JSON_FAIL_VALUE = "fail";

    String PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS = "You must fill in all of the fields.";
    String PLEASE_CHECK = "Please enter a valid ";

    double STATUTE_MILE_TO_KILOMETER = 1.609344;
    double NAUTICAL_MILE_TO_STATUTE_MILE = 1.1515;
    double DEGREE_PER_FIFTY_KM_FOR_LONGITUDE = 0.54;
    double DEGREE_PER_FIFTY_KM_FOR_LATITUDE = 0.45;
}
