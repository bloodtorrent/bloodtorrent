package org.bloodtorrent.resources;

import com.google.common.collect.Maps;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.views.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.time.DateUtils;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.util.BloodTorrentValidator;
import org.bloodtorrent.view.CommonView;
import org.hibernate.validator.constraints.NotBlank;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import static org.bloodtorrent.BloodTorrentConstants.*;


/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 2:14
 * To change this template use File | Settings | File Templates.
 */
@Path("/user")
public class UsersResource {
    public static final String EMAIL_DUPLICATION_MESSAGE = "This email address is already taken.";
    public static final String PASSWORD_DIFFERENT_MESSAGE = "Please enter confirm password same as password.";
    public static final String INVALID_LOCATION_MESSAGE = "location";

    private final UsersRepository repository;
    private UserRegistrationValidator validator;

    protected UsersResource(UsersRepository repository) {
        this.repository = repository;
        initValidator();
    }

    private void initValidator() {
        this.validator = new UserRegistrationValidator();
        this.validator.setFieldNames("firstName", "lastName", "id", "password", "confirmPassword", "address", "city", "state", "cellPhone", "bloodGroup", "distance", "gender", "birthday");
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public View forwardUserRegisterForm() {
        return new CommonView("/ftl/donorRegister.ftl");
    }

    @POST
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    @Path("success")
    public View forwardUserRegisterResult() {
        return new CommonView("/ftl/thankyouForRegistration.ftl");
    }

    @POST
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> registerDonor(@FormParam("firstName") String firstName,
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
                                    @FormParam("birthday") String birthday,
                                    @FormParam("anonymous") boolean anonymous,
                                    @FormParam("lat") double latitude,
                                    @FormParam("lng") double longitude,
                                    @FormParam("lastDonate") String lastDonate) {

        String lowerCaseId = id.toLowerCase();
        PotentialDonor potentialDonor = assemblePotentialDonorWithParam(firstName, lastName, lowerCaseId, password, confirmPassword, address, city, state, cellPhone, bloodGroup, distance, gender, birthday, anonymous, latitude, longitude);

        Map<String, Object> resultMap = Maps.newHashMap();
        if(validator.isInvalid(potentialDonor)) {
            String message = validator.getFirstValidationMessage(potentialDonor);
            resultMap.put(JSON_RESULT_KEY, JSON_FAIL_VALUE);
            resultMap.put(JSON_MESSAGE_KEY, message);
        } else {
            createDonor(lastDonate, potentialDonor);
            resultMap.put(JSON_RESULT_KEY, JSON_SUCCESS_VALUE);
        }

        return resultMap;
    }

    private void createDonor(String lastDonate, PotentialDonor potentialDonor) {
        User user = transmute(potentialDonor);
        calculateLastDonateDate(lastDonate, user);
        repository.insert(user);
    }

    public void calculateLastDonateDate(String lastDonate, User user) {
        this.calculateLastDonateDate(new Date(), lastDonate, user);
    }

    public void calculateLastDonateDate(Date today, String lastDonate, User user) {
        Date lastDonateDate = DateUtils.addDays(today, - Integer.parseInt(lastDonate));
        user.setLastDonateDate(lastDonateDate);
    }

    private PotentialDonor assemblePotentialDonorWithParam(String firstName, String lastName, String id, String password, String confirmPassword, String address, String city, String state, String cellPhone, String bloodGroup, String distance, String gender, String birthday, boolean anonymous, double latitude, double longitude) {
        PotentialDonor potentialDonor = new PotentialDonor();
        potentialDonor.setId(id);
        potentialDonor.setPassword(password);
        potentialDonor.setConfirmPassword(confirmPassword);
        potentialDonor.setFirstName(firstName);
        potentialDonor.setLastName(lastName);
        potentialDonor.setCellPhone(cellPhone);
        potentialDonor.setGender(gender);
        potentialDonor.setBloodGroup(bloodGroup);
        potentialDonor.setAnonymous(anonymous);
        potentialDonor.setAddress(address);
        potentialDonor.setState(state);
        potentialDonor.setCity(city);
        potentialDonor.setDistance(distance);
        potentialDonor.setBirthday(birthday);
        potentialDonor.setLatitude(latitude);
        potentialDonor.setLongitude(longitude);
        return potentialDonor;
    }

    private User transmute(PotentialDonor potentialDonor) {
        User user = new User();
        user.setId(potentialDonor.getId());
        user.setPassword(potentialDonor.getPassword());
        user.setFirstName(potentialDonor.getFirstName());
        user.setLastName(potentialDonor.getLastName());
        user.setCellPhone(potentialDonor.getCellPhone());
        user.setGender(potentialDonor.getGender());
        user.setBloodGroup(potentialDonor.getBloodGroup());
        user.setAnonymous(potentialDonor.isAnonymous());
        user.setAddress(potentialDonor.getAddress());
        user.setState(potentialDonor.getState());
        user.setCity(potentialDonor.getCity());
        user.setDistance(potentialDonor.getDistance());
        user.setBirthday(potentialDonor.getBirthday());
        user.setLatitude(potentialDonor.getLatitude());
        user.setLongitude(potentialDonor.getLongitude());
        user.setIsAdmin('N');
        user.setRole("donor");
        return user;
    }

    private class UserRegistrationValidator extends BloodTorrentValidator<PotentialDonor> {
        public boolean isInvalid(PotentialDonor potentialDonor) {
            if(super.isInvalid(potentialDonor)) {
                return true;
            }
            return isEmailDuplicated(potentialDonor) || isConfirmPasswordDifferent(potentialDonor) || isDateOfBirthInvalid(potentialDonor);
        }

        public String getFirstValidationMessage(PotentialDonor potentialDonor) {
            if(super.isInvalid(potentialDonor)) {
                return super.getFirstValidationMessage(potentialDonor);
            }
            if(isEmailDuplicated(potentialDonor)) {
                return EMAIL_DUPLICATION_MESSAGE;
            }
            if(isConfirmPasswordDifferent(potentialDonor)) {
                return PASSWORD_DIFFERENT_MESSAGE;
            }
            if(isInvalidLocation(potentialDonor)) {
                return INVALID_LOCATION_MESSAGE;
            }
            if (isDateOfBirthInvalid(potentialDonor)) {
                return PLEASE_CHECK + "Date of birth.";
            }
            throw new RuntimeException("Ambiguous violation.");
        }

        private boolean isInvalidLocation(PotentialDonor potentialDonor) {
            return false;  //To change body of created methods use File | Settings | File Templates.
        }

        private boolean isEmailDuplicated(PotentialDonor potentialDonor) {
            return !(repository.get(potentialDonor.getId()) == null);
        }

        private boolean isDateOfBirthInvalid(PotentialDonor potentialDonor) {
            // (date of birth) String -> date type -> validation
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String birthday = potentialDonor.getBirthday();

            if (birthday == null || birthday.trim().length() == 0) {
                return false;
            }

            String[] birthdayElements = birthday.split("-");
            Calendar calendar = GregorianCalendar.getInstance();

            try {
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(birthdayElements[0]));
                calendar.set(Calendar.MONTH, Integer.parseInt(birthdayElements[1]) - 1);
                calendar.set(Calendar.YEAR, Integer.parseInt(birthdayElements[2]));
            } catch (NumberFormatException e) {
                return true;
            }

            Date dateConverted = calendar.getTime();
            String dateConvertedString = dateFormat.format(dateConverted);

            return !dateConvertedString.equals(birthday);
        }

        private boolean isConfirmPasswordDifferent(PotentialDonor potentialDonor) {
            return !potentialDonor.getPassword().equals(potentialDonor.getConfirmPassword());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PotentialDonor extends User {

        @NotBlank(message=PLEASE_FILL_OUT_ALL_THE_MANDATORY_FIELDS)
        private String confirmPassword;
    }
}
