package org.bloodtorrent.dto;

import java.util.Date;

public class UserBuilder {
    private String email;
    private String bloodGroup = "Q";
    private Date lastDonateDate = new Date();
    private String state = "Andhra Pradesh";
    private String city = "city";
    private String firstName = "first name";
    private String lastName = "last name";
    private String address = "address";
    private String distance = "20";
    private String password = "password1";
    private String cellPhone = "0000000000";
    private String role = "donor";
    private double latitude = 32.5999;
    private double longitude = 76.0154;


    public UserBuilder(String email) {
        this.email = email;
    }


    public UserBuilder withBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public UserBuilder withLastDonateDate(Date lastDonateDate) {
        this.lastDonateDate = lastDonateDate;
        return this;
    }

    public UserBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public UserBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public UserBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(email);
        user.setBloodGroup(bloodGroup);
        user.setLastDonateDate(lastDonateDate);
        user.setState(state);
        user.setCity(city);
        user.setDistance(distance);
        user.setAddress(address);
        user.setCellPhone(cellPhone);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        return user;
    }
}
