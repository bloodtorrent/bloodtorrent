package org.bloodtorrent.resources;

import lombok.NoArgsConstructor;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.view.CommonView;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/28/13
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/sendMailTest")
@Produces(MediaType.TEXT_HTML)
@NoArgsConstructor(access = PROTECTED)
public class TestSendEmailResource {

    public static final int TEST_USER_COUNT = 3;

    @POST
    public CommonView sendEmail(
            @FormParam("uId") List<String> uId,
            @FormParam("uFirstName") List<String> uFirstName,
            @FormParam("uLastName") List<String> uLastName,
            @FormParam("pFirstName") String pFirstName,
            @FormParam("pLastName") String pLastName,
            @FormParam("pBlood") String pBlood,
            @FormParam("pAddress") String pAddress,
            @FormParam("pCellPhone") String pCellPhone){

        List<User> userList = new ArrayList();
        for(int i=0;i< TEST_USER_COUNT;i++){
            User user = new User();
            user.setId(uId.get(i));
            user.setFirstName(uFirstName.get(i));
            user.setLastName(uLastName.get(i));
            userList.add(user);
        }
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setFirstName(pFirstName);
        bloodRequest.setLastName(pLastName);
        bloodRequest.setBloodGroup(pBlood);
        bloodRequest.setHospitalAddress(pAddress);
        bloodRequest.setPhone(pCellPhone);

        NotifyDonorSendEmailResource notifyDonorSendEmailResource = new NotifyDonorSendEmailResource();
        notifyDonorSendEmailResource.sendNotifyEmail(userList, bloodRequest);

        return new CommonView("/ftl/testSendEmail.ftl");
    }
}
