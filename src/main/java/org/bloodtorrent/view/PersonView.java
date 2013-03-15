package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import org.bloodtorrent.dto.Person;

import java.util.List;

public class PersonView extends View {
    private final Person person;

    public PersonView(Person person) {
        super("/ftl/person.ftl");
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

}
