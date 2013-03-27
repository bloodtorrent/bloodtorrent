package org.bloodtorrent.resources;

import org.bloodtorrent.ResourceManager;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.util.MailConfiguration;
import org.bloodtorrent.util.MailUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/27/13
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotifyDonorSendEmailResourceTest {

    private String content;
    private User user;
    private BloodRequest bloodRequest;
    private List<User> userList;
    private String EMAIL_TITLE = "test email for send donors";
    private String ADMIN_MAIL = "administrator@bloodtorrent.mygbiz.com";
    private String ADMIN_PASSWORD = "p@ssw0rd";


    @Before
    public void init() {
        userList = new ArrayList();
        content = "<DONOR_NAME> <PATIENT_NAME> <BLOOD_TYPE> %:% <HOSPITAL_ADDRESS> <CELL_PHONE>";
        user = new User();
        user.setId("inchul.hur@gmail.com");
        user.setFirstName("Inchul");
        user.setLastName("Hur");
        userList.add(user);
        bloodRequest = new BloodRequest();
        bloodRequest.setFirstName("Priyank");
        bloodRequest.setLastName("CUSTOMER");
        bloodRequest.setBloodGroup("B");
        bloodRequest.setHospitalAddress("India Hospital");
        bloodRequest.setPhone("00000000000");

        MailConfiguration mailConfiguration = new MailConfiguration();
        mailConfiguration.setAdminMailId(ADMIN_MAIL);
        mailConfiguration.setAdminMailPassword(ADMIN_PASSWORD);
        mailConfiguration.setDonorTitle(EMAIL_TITLE);
        mailConfiguration.setDonorContent(content);
        ResourceManager.getInstance().add(mailConfiguration);
    }

    @Test
    public void shoudBeReturnReplaceString() {
        NotifyDonorSendEmailResource notifyDonorSendEmailResource = new NotifyDonorSendEmailResource();
        assertThat(notifyDonorSendEmailResource.rePlaceContent(content, user, bloodRequest),
                is("Inchul Hur Priyank CUSTOMER B : India Hospital 00000000000"));
    }

    //@Test
    public void shouldSendEmailToDonors() {
        NotifyDonorSendEmailResource notifyDonorSendEmailResource = new NotifyDonorSendEmailResource();

        boolean result= true;
        try{
            notifyDonorSendEmailResource.sendNotifyEmail(userList, bloodRequest);
        }catch(Exception e) {
            result = false;
            e.printStackTrace();
        }
        assertThat(result, is(true));
    }
}
