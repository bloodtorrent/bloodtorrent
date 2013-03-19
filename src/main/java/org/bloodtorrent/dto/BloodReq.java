package org.bloodtorrent.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 8:05
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "TB_BLOOD_REQ")
public class BloodReq {
    @Id
    private String id;
    private Date date;
    private String validated;
    @javax.persistence.Column(name = "first_name")
    private String firstName;
    @javax.persistence.Column(name = "last_name")
    private String lastName;
    private String location;
    private String phone;
    private String email;
    private String gender;
    private Integer age;
    @javax.persistence.Column(name = "blood_type")
    private String bloodType;
    @javax.persistence.Column(name = "blood_volume")
    private Integer bloodVolume;
    @javax.persistence.Column(name = "requester_type")
    private String requesterType;


    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("firstName is null or empty!");
        }else if(firstName.trim().length() > 35) {
            throw new IllegalArgumentException("firstName is too long! (Maximum 35)");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() ==0){
            throw new IllegalArgumentException("lastName is null or empty!");
        }else if(lastName.trim().length() > 35) {
            throw new IllegalArgumentException("lastName is too long! (Maximum 35)");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().length() ==0){
            throw new IllegalArgumentException("phone is null or empty!");
        }else if(!phone.matches("[0-9]{10}")){
            throw new IllegalArgumentException("phone is not digit or not 10 digits");
        }
        this.phone = phone;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().length() ==0){
            throw new IllegalArgumentException("email is null or empty!");
        }else if(!email.matches("^([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$")){
            throw new IllegalArgumentException("email is wrong formatted");
        }
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBloodType(String bloodType) {
        if (bloodType == null || bloodType.trim().length() ==0){
                throw new IllegalArgumentException("bloodType is null or empty!");
        }
        this.bloodType = bloodType;
    }

    public void setBloodVolume(Integer bloodVolume) {
        if (bloodVolume == null || bloodVolume==0){
            throw new IllegalArgumentException("bloodVolume is null or zero!");
        }
        this.bloodVolume = bloodVolume;
    }

    public void setRequesterType(String requesterType) {
        if (requesterType == null || requesterType.trim().length() ==0){
            throw new IllegalArgumentException("requesterType is null or empty!");
        }
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

    public Integer getAge() {
        return age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public Integer getBloodVolume() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().length() ==0){
            throw new IllegalArgumentException("location is null or empty!");
        }
        this.location = location;
    }
}
