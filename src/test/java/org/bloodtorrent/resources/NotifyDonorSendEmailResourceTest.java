package org.bloodtorrent.resources;

import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.util.MailConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.mail.Transport;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/27/13
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Transport.class)
public class NotifyDonorSendEmailResourceTest {

    private String content;
    private User user;
    private BloodRequest bloodRequest;
    private List<User> userList;
    private String EMAIL_TITLE = "test email for send donors";
    private String ADMIN_MAIL = "administrator@bloodtorrent.mygbiz.com";
    private String ADMIN_PASSWORD = "p@ssw0rd";
    private MailConfiguration mailConfiguration;
    private NotifyDonorSendEmailResource notifyDonorSendEmailResource = new NotifyDonorSendEmailResource();

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

        mailConfiguration = new MailConfiguration();
        mailConfiguration.setAdminMailId(ADMIN_MAIL);
        mailConfiguration.setAdminMailPassword(ADMIN_PASSWORD);
        mailConfiguration.setDonorTitle(EMAIL_TITLE);
        mailConfiguration.setDonorContent(content);
        notifyDonorSendEmailResource.setMailConfiguration(mailConfiguration);
        PowerMockito.spy(Transport.class);
        PowerMockito.doNothing().when(Transport.class);
    }

    @Test
    public void shoudBeReturnReplaceString() {
        NotifyDonorSendEmailResource notifyDonorSendEmailResource = new NotifyDonorSendEmailResource();
        notifyDonorSendEmailResource.setMailConfiguration(mailConfiguration);
        assertThat(notifyDonorSendEmailResource.rePlaceContent(content, user, bloodRequest),
                is("Inchul Hur Priyank CUSTOMER B : India Hospital 00000000000"));
    }

    @Test
    public void shouldSendEmailToDonors() {
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
