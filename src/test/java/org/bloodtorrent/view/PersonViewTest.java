package org.bloodtorrent.view;

import org.bloodtorrent.dto.Person;
import org.junit.Test;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PersonViewTest {
    @Test
    public void getterShouldReturnSamePersonObjectThatIsGivenToConstructor() {
        Person mockPerson = mock(Person.class);
        PersonView personView = new PersonView(mockPerson);
        assertThat(personView.getPerson(), sameInstance(mockPerson));
    }
}
