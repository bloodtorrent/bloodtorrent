package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.views.View;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.repository.BloodRequestRepository;
import org.bloodtorrent.view.BloodRequestView;
import org.bloodtorrent.view.CommonView;
import org.bloodtorrent.view.ErrorView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 7:55
 * To change this template use File | Settings | File Templates.
 */
@Path("/requestForBlood")
@Produces(MediaType.TEXT_HTML)
public class BloodRequestResource {
    private final BloodRequestRepository repository;

    public BloodRequestResource(BloodRequestRepository repository) {
        this.repository = repository;
    }

    @GET
    @UnitOfWork
    public BloodRequestView forwardBloodRequestForm() {
        return new BloodRequestView();
    }

    @POST
    @UnitOfWork
    public View requestForBlood(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("hospitalAddress") String hospitalAddress,
            @FormParam("city") String city,
            @FormParam("state") String state,
            @FormParam("phone") String phone,
            @FormParam("email") String email,
            @FormParam("gender") String gender,
            @FormParam("birthday") String birthday,
            @FormParam("bloodType") String bloodType,
            @FormParam("bloodVolume") String bloodVolume,
            @FormParam("requesterType") String requesterType)
    {
        try {
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

            if (birthday != null && birthday.trim().length() > 0) {
                if (!birthday.matches("[0-3][0-9]-[0-1][0-9]-[0-9]{4}")) {
                    throw new IllegalArgumentException("Date of Birth");
                }

                String[] birth = birthday.split("-");
                cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(birth[0]));
                cal.set(Calendar.MONTH, Integer.parseInt(birth[1]));
                cal.set(Calendar.YEAR, Integer.parseInt(birth[2]));
                bloodRequest.setBirthday(cal.getTime());
            }

            bloodRequest.setBloodType(bloodType);
            bloodRequest.setBloodVolume(bloodVolume);
            bloodRequest.setRequesterType(requesterType);
            bloodRequest.setDate(new Date());
            bloodRequest.setValidated("N");
            bloodRequest.setId("" + System.currentTimeMillis());
            createNewBloodRequest(bloodRequest);
            return new CommonView("/ftl/thankyou.ftl");
        } catch (IllegalArgumentException e) {
            return new ErrorView("/ftl/error.ftl", e.getMessage());
        } catch (NullPointerException e){
            ErrorView errorView = new ErrorView("/ftl/error.ftl", e.getMessage());
            errorView.setIsNullPointerException(true);
            return errorView;

        }
    }

    public void createNewBloodRequest(BloodRequest bloodRequest) {
        repository.insert(bloodRequest);
    }
}
