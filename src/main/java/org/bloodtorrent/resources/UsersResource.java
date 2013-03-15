package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.view.CommonView;
import org.bloodtorrent.view.UserView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 2:14
 * To change this template use File | Settings | File Templates.
 */
@Path("/user")
public class UsersResource {
    private final UsersRepository repository;

    public UsersResource(UsersRepository repository) {
        this.repository = repository;
    }

    public void createNewUser(User user) {
    }

    public Object findByUserById(String id) {
        return null;
    }

    @GET
    @UnitOfWork
    public UserView forwardUserRegistForm() {
        User user = new User();
        return new UserView(user);
    }

    @POST
    @UnitOfWork
    public CommonView registDonor(@FormParam("email") String id,
                                  @FormParam("password") String password, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("phoneNumber") String phoneNumber, @FormParam("gender") String gender, @FormParam("age") int age, @FormParam("bloodType") String bloodType, @FormParam("anonymous") String anonymous, @FormParam("address") String address, @FormParam("distance") double distance) {
        System.out.println("data from request : "+id);
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setGender(gender);
        user.setAge(age);
        user.setBloodType(bloodType);
        user.setAnonymous(anonymous);
        user.setAddress(address);
        user.setDistance(distance);
        user.setRole("donor");
        this.repository.insert(user);
        return new CommonView("/ftl/registSuccess.ftl");
    }
}
