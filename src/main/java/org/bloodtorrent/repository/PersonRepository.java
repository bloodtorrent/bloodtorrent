package org.bloodtorrent.repository;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.bloodtorrent.dto.Person;
import org.hibernate.SessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: SDS
 * Date: 13. 3. 13
 * Time: 오전 10:28
 * To change this template use File | Settings | File Templates.
 */
public class PersonRepository extends AbstractDAO<Person> {

    public PersonRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void insert(String name, String blood){
            persist(new Person(name, blood));
    }

    public Person findPersonByName(String name){
          return get(name);
    }

}
