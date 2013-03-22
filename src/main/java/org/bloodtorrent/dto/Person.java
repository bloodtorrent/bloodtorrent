package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "person")
@Getter @Setter @NoArgsConstructor
public class Person implements Serializable {
    @Id
    private String name;
    private String blood;

    public Person(String name, String blood) {
        this.name = name;
        this.blood = blood;
    }
}
