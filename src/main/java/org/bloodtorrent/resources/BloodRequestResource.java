package org.bloodtorrent.resources;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.views.View;
import lombok.Setter;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.BloodRequestRepository;
import org.bloodtorrent.view.BloodRequestView;
import org.bloodtorrent.view.CommonView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

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
    private final BloodTorrentValidator validator;

    public BloodRequestResource(BloodRequestRepository repository, NotifyDonorSendEmailResource mailResource, FindingMatchingDonorResource findingMatchingDonorResource) {
        this.repository = repository;
        this.mailResource = mailResource;
        this.findingMatchingDonorResource = findingMatchingDonorResource;
        this.validator = new BloodTorrentValidator();
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
            @FormParam("requesterType") String requesterType) {
        Calendar cal = null;
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setFirstName(firstName);
        bloodRequest.setLastName(lastName);
        bloodRequest.setHospitalAddress(hospitalAddress);
        bloodRequest.setCity(city);
        bloodRequest.setState(state);
        bloodRequest.setPhone(phone);
        bloodRequest.setEmail(email);
        bloodRequest.setGender(gender);

        setBirthday(birthday, bloodRequest);

        bloodRequest.setBloodGroup(bloodGroup);
        bloodRequest.setBloodVolume(bloodVolume);
        bloodRequest.setRequesterType(requesterType);
        bloodRequest.setDate(new Date());
        bloodRequest.setValidated("N");
        bloodRequest.setId(String.valueOf(System.currentTimeMillis()));

        Map<String, Object> resultMap = Maps.newHashMap();
        if (validator.isInvalid(bloodRequest)) {
            List<String > messages = new ArrayList<String>();
            String message = validator.getFirstValidationMessageAlphabetically(bloodRequest);
            messages.add(message);
            resultMap.put("result", "fail");
            resultMap.put("messages", messages);
        } else {
            createNewBloodRequest(bloodRequest);
            resultMap.put("result", "success");
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

    private void setBirthday(String birthday, BloodRequest bloodRequest) {
        Calendar cal;
        if (birthday != null && birthday.trim().length() > 0) {
            String[] birth = birthday.split("-");
            cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(birth[0]));
            cal.set(Calendar.MONTH, Integer.parseInt(birth[1]));
            cal.set(Calendar.YEAR, Integer.parseInt(birth[2]));
            bloodRequest.setBirthday(cal.getTime());
        }
    }

    public void createNewBloodRequest(BloodRequest bloodRequest) {
        repository.insert(bloodRequest);
    }

}
