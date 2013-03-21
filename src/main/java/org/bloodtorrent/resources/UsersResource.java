package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.apache.commons.lang3.time.DateUtils;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.view.ResultView;
import org.bloodtorrent.view.UserView;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


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

    @GET
    @UnitOfWork
    public UserView forwardUserRegistForm() {
        User user = new User();
        return new UserView(user);
    }

    @POST
    @UnitOfWork
    public ResultView registDonor(@FormParam("firstName") String firstName,
                                  @FormParam("lastName") String lastName,
                                  @FormParam("email") String id,
                                  @FormParam("password") String password,
                                  @FormParam("confirmPassword") String confirmPassword,
                                  @FormParam("address") String address,
                                  @FormParam("city") String city,
                                  @FormParam("state") String state,
                                  @FormParam("cellPhone") String cellPhone,
                                  @FormParam("bloodGroup") String bloodGroup,
                                  @FormParam("distance") String distance,
                                  @FormParam("gender") String gender,
                                  @FormParam("birthDay") String birthDay,
                                  @FormParam("anonymous") boolean anonymous,
                                  @FormParam("lastDonate") String lastDonate) {

        User user = setUserFormParam(firstName, lastName, id, password, address, city, state, cellPhone, bloodGroup, distance, gender, birthDay, anonymous);

        calculateLastDonateDate(lastDonate, user);

        String result = "fail";
        List<String> messages = new ArrayList<String>();

        checkPassword(messages, password, confirmPassword);
        checkEmailDuplicated(messages, user);

        Set<ConstraintViolation<User>> constraintViolations = validateUserInfo(user);

        if(constraintViolations.size() > 0){
            messages = createViolationMessage(messages, constraintViolations);
        }else{
            this.repository.insert(user);
            result = "success";
            messages.add("Thank you for signing up as a donor. Please go ahead and log in");
        }

        return new ResultView(result, messages);
    }

    private List<String> createViolationMessage(List<String> messages, Set<ConstraintViolation<User>> constraintViolations) {
        for(ConstraintViolation constraintViolation :constraintViolations){
            messages.add(constraintViolation.getMessage()) ;
        }
        return messages;
    }

    private Set<ConstraintViolation<User>> validateUserInfo(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(user);
    }

    private void calculateLastDonateDate(String lastDonate, User user) {
        Date lastDonateDate = DateUtils.addMonths(new Date(), -Integer.parseInt(lastDonate));
        user.setLastDonateDate(lastDonateDate);
    }

    private User setUserFormParam(String firstName, String lastName, String id, String password, String address, String city, String state, String cellPhone, String bloodGroup, String distance, String gender, String birthDay, boolean anonymous) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCellPhone(cellPhone);
        user.setGender(gender);
        user.setBloodGroup(bloodGroup);
        user.setAnonymous(anonymous);
        user.setAddress(address);
        user.setState(state);
        user.setCity(city);
        user.setDistance(distance);
        user.setRole("donor");
        user.setBirthDay(birthDay);
        return user;
    }

    private List<String> checkPassword(List<String> messages, String password, String confirmPassword) {
        if(!password.equals(confirmPassword)){
            messages.add("password and confirm password are not same.");
        }
        return messages;
    }

    public List<String> checkEmailDuplicated(List<String> messages, User user) {
        if(!(this.repository.get(user.getId()) == null)){
            messages.add("This email address is already taken.")  ;
        }
        return messages;
    }
}
