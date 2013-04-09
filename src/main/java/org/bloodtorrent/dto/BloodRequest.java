package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

import static org.bloodtorrent.BloodTorrentConstants.PLEASE_CHECK;
import static org.bloodtorrent.BloodTorrentConstants.PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS;

@Entity(name = "BLOOD_REQUEST")
@Setter @Getter
@SuppressWarnings("PMD.UnusedPrivateField")
public class BloodRequest {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 35;
    public static final int MAX_ADDRESS_LENGTH = 1000;
    public static final int MAX_CITY_LENGTH = 255;
    public static final int MAX_STATE_LENGTH = 255;
    public static final int MAX_PHONE_LENGTH = 10;
    public static final int MIN_PHONE_LENGTH = 10;
    public static final int MAX_EMAIL_LENGTH = 100;
    public static final int MIN_EMAIL_LENGTH = 5;
    @Id
    private String id;

    @Column(name = "request_date")
    private Date requestDate;

    @NotBlank
    @Pattern(regexp = "^Y|N$")
    private String approval;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_NAME_LENGTH, message= PLEASE_CHECK + "First name. ")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_NAME_LENGTH, message= PLEASE_CHECK + "Last name. ")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message= PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_ADDRESS_LENGTH, message= PLEASE_CHECK + "Address. ")
    @Column(name = "hospital_address")
    private String hospitalAddress;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_CITY_LENGTH, message= PLEASE_CHECK + "City. ")
    private String city;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MAX_STATE_LENGTH, message= PLEASE_CHECK + "State. ")
    @Pattern(regexp = "^(Andhra Pradesh)|(Arunachal Pradesh)|(Asom \\(Assam\\))|(Bihar)|(Karnataka)|(Kerala)|(Chhattisgarh)|(Goa)|(Gujarat)|(Haryana)|(Himachal Pradesh)|(Jammu And Kashmir)|(Jharkhand)|(West Bengal)|(Madhya Pradesh)|(Maharashtra)|(Manipur)|(Meghalaya)|(Mizoram)|(Nagaland)|(Orissa)|(Punjab)|(Rajasthan)|(Sikkim)|(Tamilnadu)|(Tripura)|(Uttarakhand \\(Uttaranchal\\))|(Uttar Pradesh)$", message = PLEASE_CHECK + "State.")
    private String state;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^[0-9]{10}$", message= PLEASE_CHECK + "Cell Phone.")
    @Size(min = MIN_PHONE_LENGTH, max = MAX_PHONE_LENGTH, message= PLEASE_CHECK + "Cell Phone. ")
    private String phone;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^([0-9a-zA-Z_-]|(([0-9a-zA-Z_-]+[\\\\.]?)+[0-9a-zA-Z_-]))@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$", message= PLEASE_CHECK + "E-mail.")
    @Size(min = MIN_EMAIL_LENGTH, max = MAX_EMAIL_LENGTH, message= PLEASE_CHECK + "E-mail. ")
    private String email;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = MIN_LENGTH, max = MIN_LENGTH, message= PLEASE_CHECK + "Gender. ")
    private String gender;

    @Pattern(regexp ="^((0[1-9]|[1-2][0-9]|3[0-1])\\-(0[0-9]|1[0-2])\\-(19[0-9][0-9]|20\\d{2}))*$", message= PLEASE_CHECK + "Date of Birth.")
    private String birthday;

    @Column(name = "blood_group")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^(A\\+)|(A-)|(B\\+)|(B-)|(AB\\+)|(AB-)|(O\\+)|(O-)$", message= PLEASE_CHECK + "Blood Group.")
    private String bloodGroup;

    @Column(name = "blood_volume")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^([1-9])|([1-9][0-9])$", message= PLEASE_CHECK + "Blood Volume.")
    private String bloodVolume;

    @Column(name = "requester_type")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^C|P$", message= PLEASE_CHECK + "Requester.")
    private String requesterType;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
}
