package org.bloodtorrent.resources;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.views.View;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.BloodRequestRepository;
import org.bloodtorrent.util.BloodTorrentValidator;
import org.bloodtorrent.view.BloodRequestView;
import org.bloodtorrent.view.CommonView;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.bloodtorrent.BloodTorrentConstants.*;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 7:55
 * To change this template use File | Settings | File Templates.
 */
@Path("/requestForBlood")
public class BloodRequestResource {
    private final BloodRequestRepository repository;
    private final NotifyDonorSendEmailResource mailResource;
    private final FindingMatchingDonorResource findingMatchingDonorResource;

    private BloodRequestValidator validator;

    protected BloodRequestResource(BloodRequestRepository repository, NotifyDonorSendEmailResource mailResource, FindingMatchingDonorResource findingMatchingDonorResource) {
        this.repository = repository;
        this.mailResource = mailResource;
        this.findingMatchingDonorResource = findingMatchingDonorResource;
        initValidator();
    }

    private void initValidator() {
        this.validator = new BloodRequestValidator();
        this.validator.setFieldNames("firstName", "lastName", "hospitalAddress", "city", "state", "phone", "email", "gender", "birthday", "bloodGroup", "bloodVolume", "requesterType");
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public View forwardBloodRequestForm() {
        return new CommonView("/ftl/bloodRequest.ftl");
    }

    @POST
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    @Path("success")
    public View forwardBloodRequestResult(@FormParam("requestId") String requestId) {
        BloodRequest bloodRequest = repository.get(requestId);
        List<User> donors = null;
        try {
            donors = findMatchingDonors(bloodRequest);
            sendEmailToMatchingDonors(bloodRequest, donors);
        } catch (IllegalDataException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            donors = Lists.newArrayList();
        }

        return new BloodRequestView(donors);
    }

    @POST
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> requestForBlood(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("hospitalAddress") String hospitalAddress,
            @FormParam("city") String city,
            @FormParam("state") String state,
            @FormParam("phone") String phone,
            @FormParam("email") String email,
            @FormParam("gender") String gender,
            @FormParam("birthday") String birthday,
            @FormParam("bloodGroup") String bloodGroup,
            @FormParam("bloodVolume") String bloodVolume,
            @FormParam("requesterType") String requesterType,
            @FormParam("lat") double latitude,
            @FormParam("lng") double longitude
    ) {

        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setFirstName(firstName);
        bloodRequest.setLastName(lastName);
        bloodRequest.setHospitalAddress(hospitalAddress);
        bloodRequest.setCity(city);
        bloodRequest.setState(state);
        bloodRequest.setPhone(phone);
        bloodRequest.setEmail(email);
        bloodRequest.setGender(gender);
        bloodRequest.setBirthday(birthday);
        bloodRequest.setBloodGroup(bloodGroup);
        bloodRequest.setBloodVolume(bloodVolume);
        bloodRequest.setRequesterType(requesterType);
        bloodRequest.setRequestDate(new Date());
        bloodRequest.setApproval("N");
        bloodRequest.setId(String.valueOf(System.currentTimeMillis()));
        bloodRequest.setLatitude(latitude);
        bloodRequest.setLongitude(longitude);

        Map<String, Object> resultMap = Maps.newHashMap();

        if (validator.isInvalid(bloodRequest)) {
            String message = validator.getFirstValidationMessage(bloodRequest);
            resultMap.put(JSON_RESULT_KEY, JSON_FAIL_VALUE);
            resultMap.put(JSON_MESSAGE_KEY, message);
        } else {
            createNewBloodRequest(bloodRequest);
            resultMap.put(JSON_RESULT_KEY, JSON_SUCCESS_VALUE);
            resultMap.put("requestId", bloodRequest.getId());
        }
        return resultMap;
    }

    private List<User> findMatchingDonors(BloodRequest bloodRequest) throws IllegalDataException {
        try {
            return findingMatchingDonorResource.findMatchingDonors(bloodRequest);
        } catch (IllegalDataException e) {
            e.printStackTrace();
            throw new IllegalDataException("Finding matching donor failed.");
        }
    }

    private void sendEmailToMatchingDonors(BloodRequest bloodRequest, List<User> donors) {
        mailResource.sendNotifyEmail(donors, bloodRequest);
    }

    public void createNewBloodRequest(BloodRequest bloodRequest) {
        repository.insert(bloodRequest);
    }

    private class BloodRequestValidator extends BloodTorrentValidator<BloodRequest> {
        public boolean isInvalid(BloodRequest bloodRequest) {
            if(super.isInvalid(bloodRequest)) {
                return true;
            }
            return isDateOfBirthInvalid(bloodRequest);
        }

        public String getFirstValidationMessage(BloodRequest bloodRequest) {
            if(super.isInvalid(bloodRequest)) {
                return super.getFirstValidationMessage(bloodRequest);
            }
            if (isDateOfBirthInvalid(bloodRequest)) {
                return PLEASE_CHECK + "Date of birth.";
            }
            throw new RuntimeException("Ambiguous violation.");
        }

        private boolean isDateOfBirthInvalid(BloodRequest bloodRequest) {
            // (date of birth) String -> date type -> validation
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String birthday = bloodRequest.getBirthday();

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
    }

}
