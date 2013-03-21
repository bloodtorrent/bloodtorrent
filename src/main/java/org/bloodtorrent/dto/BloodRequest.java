package org.bloodtorrent.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 8:05
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "TB_BLOOD_REQ")
public class BloodRequest {

    private static final String PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS = "Please fill out all the mandatory fields";
    private static final String PLEASE_CHECK = "Please check";

    @Id
    private String id;
    private Date date;
    private String validated;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(first name)")
    @Size(min = 1, max = 35, message= PLEASE_CHECK + " first name")
    @javax.persistence.Column(name = "first_name")
    private String firstName;

    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(last name)")
    @Size(min = 1, max = 35, message= PLEASE_CHECK + " last name")
    @javax.persistence.Column(name = "last_name")
    private String lastName;
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(Cell Phone)")
    @Pattern(regexp = "^[0-9]{10}$", message= PLEASE_CHECK + " Cell Phone")
    @Size(min = 10, max = 10, message= PLEASE_CHECK + " Cell Phone")
    private String phone;
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(E-mail)")
    @Pattern(regexp = "^([0-9a-zA-Z_-]([0-9a-zA-Z_-]|\\.)+[0-9a-zA-Z_-])@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$", message= PLEASE_CHECK + " email address")
    @Size(min = 5, max = 100, message= PLEASE_CHECK + " E-mail")
    private String email;
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(gender)")
    @Size(min = 1, max = 1, message= PLEASE_CHECK + " gender")
    private String gender;
    @javax.persistence.Column(name = "blood_group")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(blood group)")
    @Pattern(regexp = "^(A\\+)|(A-)|(B\\+)|(B-)|(AB\\+)|(AB-)|(O\\+)|(O-)|(Unknown)$", message= PLEASE_CHECK + " blood group")
    private String bloodGroup;
    @javax.persistence.Column(name = "blood_volume")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(blood volume)")
    @Pattern(regexp = "^([1-9])|([1-9][0-9])$", message= PLEASE_CHECK + " blood volume")
    private String bloodVolume;
    @javax.persistence.Column(name = "requester_type")
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(requester type)")
    @Pattern(regexp = "^C|P$", message= PLEASE_CHECK + " requester type")
    private String requesterType;
    private Date birthday;
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(city)")
    @Size(min = 1, max = 255, message= PLEASE_CHECK + " city size(1-255)")
    private String city;
    @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS+"(state)")
    @Size(min = 1, max = 255, message= PLEASE_CHECK + " state size(1-255)")
    @Pattern(regexp = "^(Andhra Pradesh)|(Arunachal Pradesh)|(Asom \\(Assam\\))|(Bihar)|(Karnataka)|(Kerala)|(Chhattisgarh)|(Goa)|(Gujarat)|(Haryana)|(Himachal Pradesh)|(Jammu And Kashmir)|(Jharkhand)|(West Bengal)|(Madhya Pradesh)|(Maharashtra)|(Manipur)|(Meghalaya)|(Mizoram)|(Nagaland)|(Orissa)|(Punjab)|(Rajasthan)|(Sikkim)|(Tamilnadu)|(Tripura)|(Uttarakhand \\(Uttaranchal\\))|(Uttar Pradesh)$")
    private String state;
    @NotBlank(message= PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS + "(address)")
    @Size(min = 1, max = 1000 , message= PLEASE_CHECK + " address size(1-1000)")
    @javax.persistence.Column(name = "hospital_address")
    private String hospitalAddress;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setBloodVolume(String bloodVolume) {
        this.bloodVolume = bloodVolume;
    }

    public void setRequesterType(String requesterType) {
        this.requesterType = requesterType;
    }

    public void setValidated(String validated) {
        this.validated = validated;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getValidated() {
        return validated;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getBloodVolume() {
        return bloodVolume;
    }

    public String getRequesterType() {
        return requesterType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {

        this.hospitalAddress = hospitalAddress;
    }
}
