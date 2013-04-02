package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.bloodtorrent.BloodTorrentConstants;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "BLOOD_REQUEST")
@Setter @Getter
@SuppressWarnings("PMD.UnusedPrivateField")
public class BloodRequest implements BloodTorrentConstants {

    @Id
    private String id;

    @Column(name = "request_date")
    private Date requestDate;

    @NotBlank
    @Pattern(regexp = "^Y|N$")
    private String approval;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = 1, max = 35, message= PLEASE_CHECK + "First name. ")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = 1, max = 35, message= PLEASE_CHECK + "Last name. ")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message= PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = 1, max = 1000 , message= PLEASE_CHECK + "Address. ")
    @Column(name = "hospital_address")
    private String hospitalAddress;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = 1, max = 255, message= PLEASE_CHECK + "City. ")
    private String city;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = 1, max = 255, message= PLEASE_CHECK + "State. ")
    @Pattern(regexp = "^(Andhra Pradesh)|(Arunachal Pradesh)|(Asom \\(Assam\\))|(Bihar)|(Karnataka)|(Kerala)|(Chhattisgarh)|(Goa)|(Gujarat)|(Haryana)|(Himachal Pradesh)|(Jammu And Kashmir)|(Jharkhand)|(West Bengal)|(Madhya Pradesh)|(Maharashtra)|(Manipur)|(Meghalaya)|(Mizoram)|(Nagaland)|(Orissa)|(Punjab)|(Rajasthan)|(Sikkim)|(Tamilnadu)|(Tripura)|(Uttarakhand \\(Uttaranchal\\))|(Uttar Pradesh)$")
    private String state;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^[0-9]{10}$", message= PLEASE_CHECK + "Cell Phone.")
    @Size(min = 10, max = 10, message= PLEASE_CHECK + "Cell Phone. ")
    private String phone;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^([0-9a-zA-Z_-]|(([0-9a-zA-Z_-]+[\\\\.]?)+[0-9a-zA-Z_-]))@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$", message= PLEASE_CHECK + "E-mail.")
    @Size(min = 5, max = 100, message= PLEASE_CHECK + "E-mail. ")
    private String email;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Size(min = 1, max = 1, message= PLEASE_CHECK + "Gender. ")
    private String gender;

    @Pattern(regexp ="^((0[1-9]|[1-2][0-9]|3[0-1])\\-(0[0-9]|1[0-2])\\-(19[0-9][0-9]|20\\d{2}))*$", message= PLEASE_CHECK + "Date of Birth.")
    private String birthday;

    @Column(name = "blood_group")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^(A\\+)|(A-)|(B\\+)|(B-)|(AB\\+)|(AB-)|(O\\+)|(O-)|(Unknown)$", message= PLEASE_CHECK + "Blood Group.")
    private String bloodGroup;

    @Column(name = "blood_volume")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^([1-9])|([1-9][0-9])$", message= PLEASE_CHECK + "Blood Volume.")
    private String bloodVolume;

    @Column(name = "requester_type")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
    @Pattern(regexp = "^C|P$", message= PLEASE_CHECK + "Requester.")
    private String requesterType;
}
