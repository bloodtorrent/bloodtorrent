package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.views.View;
import org.bloodtorrent.dto.BloodReq;
import org.bloodtorrent.repository.BloodReqRepository;
import org.bloodtorrent.view.BloodReqView;
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
@Path("/bloodreq")
@Produces(MediaType.TEXT_HTML)
public class BloodReqResource {
    private final BloodReqRepository repository;

    public BloodReqResource(BloodReqRepository repository) {
        this.repository = repository;
    }

    @GET
    @UnitOfWork
    public BloodReqView forwardBloodRequestForm() {
        return new BloodReqView();
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
            @FormParam("bloodVolume") int bloodVolume,
            @FormParam("requesterType") String requesterType)
    {
        try {
            Calendar cal = null;
            BloodReq bloodReq = new BloodReq();
            if (birthday != null && birthday.trim().length() > 0) {
                if (!birthday.matches("[0-3][0-9]-[0-1][0-9]-[0-9]{4}")) {
                    throw new IllegalArgumentException("Date of Birth");
                }

                String[] birth = birthday.split("-");
                cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(birth[0]));
                cal.set(Calendar.MONTH, Integer.parseInt(birth[1]));
                cal.set(Calendar.YEAR, Integer.parseInt(birth[2]));
                bloodReq.setBirthday(cal.getTime());
            }
            bloodReq.setFirstName(firstName);
            bloodReq.setLastName(lastName);
            bloodReq.setHospitalAddress(hospitalAddress);
            bloodReq.setCity(city);
            bloodReq.setState(state);
            bloodReq.setPhone(phone);
            bloodReq.setEmail(email);
            bloodReq.setGender(gender);
            bloodReq.setBloodType(bloodType);
            bloodReq.setBloodVolume(bloodVolume);
            bloodReq.setRequesterType(requesterType);
            bloodReq.setDate(new Date());
            bloodReq.setValidated("N");
            bloodReq.setId("" + System.currentTimeMillis());
            createNewBloodRequest(bloodReq);
            return new CommonView("/ftl/thankyou.ftl");
        } catch (IllegalArgumentException e) {
            return new ErrorView("/ftl/error.ftl", e.getMessage());
        } catch (NullPointerException e){
            ErrorView errorView = new ErrorView("/ftl/error.ftl", e.getMessage());
            errorView.setIsNullPointerException(true);
            return errorView;

        }
    }

    public void createNewBloodRequest(BloodReq bloodReq) {
        repository.insert(bloodReq);
        //BloodReq savedInstance = repository.findByEmail(bloodReq.getEmail());
        //AssertEquals(bloodReq, savedInstance);
    }
}
