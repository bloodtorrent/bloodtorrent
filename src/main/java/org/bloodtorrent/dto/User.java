package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

import static org.bloodtorrent.BloodTorrentConstants.PLEASE_CHECK;
import static org.bloodtorrent.BloodTorrentConstants.PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS;

@Entity
@Table(name = "USER")
@Getter @Setter
@SuppressWarnings("PMD.UnusedPrivateField")
public class User {
    public static final int MAX_ID_LENGTH = 100;
    public static final int MAX_NAME_LENGTH = 35;
    public static final int MIN_LENGTH = 1;
    public static final int MIN_PHONE_LENGTH = 10;
    public static final int MAX_PHONE_LENGTH = 10;
    public static final int MAX_STATE_LENGTH = 255;
    public static final int MAX_CITY_LENGTH = 255;
    public static final int MAX_ADDRESS_LENGTH = 1000;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 25;
    public static final int MIN_ID_LENGTH = 5;
    @Id
    @Pattern(regexp = "^([0-9a-zA-Z_-]|(([0-9a-zA-Z_-]+[\\\\.]?)+[0-9a-zA-Z_-]))@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$", message= PLEASE_CHECK + "E-mail.")
    @Size(min = MIN_ID_LENGTH, max = MAX_ID_LENGTH, message= PLEASE_CHECK + "E-mail. ")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    //TODO: why are we validating a field named "id" as an email address? --Kris
    private String id;

    @Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH, message= PLEASE_CHECK + "Password. ")
    @Pattern(regexp ="\\D*\\d+\\D*", message= PLEASE_CHECK + "Password.")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    private String password;

    private String role;

    @Column(name = "first_name")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_NAME_LENGTH, message= PLEASE_CHECK + "First name. ")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_NAME_LENGTH, message= PLEASE_CHECK + "Last name. ")
    private String lastName;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_PHONE_LENGTH, max = MAX_PHONE_LENGTH, message= PLEASE_CHECK + "Cell Phone. ")
    @Pattern(regexp = "\\d*", message= PLEASE_CHECK + "Cell Phone.")
    @Column(name = "cell_phone")
    private String cellPhone;

    private String gender;

    @Column(name = "blood_group")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^(A\\+)|(A-)|(B\\+)|(B-)|(AB\\+)|(AB-)|(O\\+)|(O-)|(Unknown)$", message= PLEASE_CHECK + "Blood group.")
    private String bloodGroup;

    private boolean anonymous;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_CITY_LENGTH, message= PLEASE_CHECK + "City. ")
    private String city;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_STATE_LENGTH, message= PLEASE_CHECK + "State. ")
    @Pattern(regexp = "^(Andhra Pradesh)|(Arunachal Pradesh)|(Asom \\(Assam\\))|(Bihar)|(Karnataka)|(Kerala)|(Chhattisgarh)|(Goa)|(Gujarat)|(Haryana)|(Himachal Pradesh)|(Jammu And Kashmir)|(Jharkhand)|(West Bengal)|(Madhya Pradesh)|(Maharashtra)|(Manipur)|(Meghalaya)|(Mizoram)|(Nagaland)|(Orissa)|(Punjab)|(Rajasthan)|(Sikkim)|(Tamilnadu)|(Tripura)|(Uttarakhand \\(Uttaranchal\\))|(Uttar Pradesh)$", message = PLEASE_CHECK + "State.")
    private String state;

    @NotBlank(message= PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_ADDRESS_LENGTH, message= PLEASE_CHECK + "Address. ")
    private String address;

    @NotBlank(message= PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^5|10|20|50$" , message= PLEASE_CHECK + "Distance.")
    private String distance;

    @Column(name = "birthday")
    @Pattern(regexp ="^((0[1-9]|[1-2][0-9]|3[0-1])\\-(0[0-9]|1[0-2])\\-(19[0-9][0-9]|20\\d{2}))*$", message= PLEASE_CHECK + "Date of birth.")
    private String birthday;

    @Column(name = "last_donate_date")
    private Date lastDonateDate;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "is_admin")
    private char isAdmin = 'N';
}
