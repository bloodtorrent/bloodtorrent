package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.dto.BloodReq;
import org.bloodtorrent.repository.BloodReqRepository;
import org.bloodtorrent.view.BloodReqView;
import org.bloodtorrent.view.CommonView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public CommonView requestForBlood(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("location") String location,
            @FormParam("phone") String phone,
            @FormParam("email") String email,
            @FormParam("gender") String gender,
            @FormParam("age") int age,
            @FormParam("bloodType") String bloodType,
            @FormParam("bloodVolume") int bloodVolume,
            @FormParam("requesterType") String requesterType)
    {
        BloodReq bloodReq = new BloodReq();
        bloodReq.setFirstName(firstName);
        bloodReq.setLastName(lastName);
        bloodReq.setLocation(location);
        bloodReq.setPhone(phone);
        bloodReq.setEmail(email);
        bloodReq.setGender(gender);
        bloodReq.setAge(age);
        bloodReq.setBloodType(bloodType);
        bloodReq.setBloodVolume(bloodVolume);
        bloodReq.setRequesterType(requesterType);
        bloodReq.setDate(new Date());
        bloodReq.setId("" + System.currentTimeMillis());
        createNewBloodRequest(bloodReq);
        return new CommonView("/ftl/thankyou.ftl");
    }

    public void createNewBloodRequest(BloodReq bloodReq) {
        repository.insert(bloodReq);
        //BloodReq savedInstance = repository.findByEmail(bloodReq.getEmail());
        //AssertEquals(bloodReq, savedInstance);
    }
}
