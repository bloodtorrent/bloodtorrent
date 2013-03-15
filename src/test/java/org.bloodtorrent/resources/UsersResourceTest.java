package org.bloodtorrent.resources;

import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.mockito.Mockito;
import org.mockito.internal.MockitoCore;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 1:56
 * To change this template use File | Settings | File Templates.
 */
public class UsersResourceTest{
    private final UsersRepository repository = Mockito.mock(UsersRepository.class);
    private final UsersResource resource = new UsersResource(repository);

    @Test
    public void resourceCallCheck() {
        Assert.assertNotNull(resource);
    }

    @Test
    public void createNewUser(){
        User user = new User();
        user.setId("test@naver.com");
        user.setPassword("1234");
        user.setRole("donar");
        user.setFirstName("Test");
        user.setLastName("Kris");
        user.setPhoneNumber("0123456789");
        user.setGender("m");
        user.setAge(35);
        user.setBloodType("A+");
        user.setAnonymous("n");
        user.setAddress("Seoul, South Korea");
        user.setDistance(10);

        resource.createNewUser(user);

        //Assert.assertNotNull(resource.findByUserById("test@naver.com"));

    }
}
