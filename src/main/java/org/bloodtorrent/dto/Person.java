package org.bloodtorrent.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "person")
public class Person implements Serializable {
    @Id
    private String name;
    private String blood;

    public Person(String name, String blood) {
        this.name = name;
        this.blood = blood;
    }

    public Person() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}
