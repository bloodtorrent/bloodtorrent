package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.dto.Person;
import org.bloodtorrent.repository.PersonRepository;
import org.bloodtorrent.view.PersonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/person")
@Produces(MediaType.TEXT_HTML)
public class PersonResource {
    private final PersonRepository repository;

    public PersonResource(PersonRepository repository) {
        this.repository = repository;
    }

    @GET
    @UnitOfWork
    public PersonView getPerson(@QueryParam("name") String name) {
        Person person = repository.findPersonByName(name);
        return new PersonView(person);
    }
}