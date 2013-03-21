package org.bloodtorrent.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

public class Location implements Serializable {
    private String address;
    private String city;
    private String state;

    public Location(String address, String city, String state) {
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public Location() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
