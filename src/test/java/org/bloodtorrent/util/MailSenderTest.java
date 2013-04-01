package org.bloodtorrent.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.mail.Transport;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Transport.class)
public class MailSenderTest {

    private String ADMIN_MAIL = "administrator@bloodtorrent.mygbiz.com";
    private String ADMIN_PASSWORD = "p@ssw0rd";
    private String EMAIL_TITLE = "test email for send donors";
    private MailSender mailSender = new MailSender();
    @Before
    public void init() {
        MailConfiguration mailConfiguration = new MailConfiguration();
        mailConfiguration.setAdminMailId(ADMIN_MAIL);
        mailConfiguration.setAdminMailPassword(ADMIN_PASSWORD);

        PowerMockito.spy(Transport.class);
        PowerMockito.doNothing().when(Transport.class);

        mailConfiguration = new MailConfiguration();
        mailConfiguration.setAdminMailId(ADMIN_MAIL);
        mailConfiguration.setAdminMailPassword(ADMIN_PASSWORD);
        mailConfiguration.setDonorTitle(EMAIL_TITLE);
        String content = "<DONOR_NAME> <PATIENT_NAME> <BLOOD_TYPE> %:% <HOSPITAL_ADDRESS> <CELL_PHONE>";
        mailConfiguration.setDonorContent(content);
        mailSender.setMailConfiguration(mailConfiguration);
    }

    @Test
    public void mailTest() {
        boolean hasSucceeded = true;
        String to = "inchul.hur@gmail.com";
        String title = "[BloodTorrent] Mailing Test";
        String content = "<html><body>Hello World: " + System.currentTimeMillis() + "</body></html>";

        try {
            mailSender.sendEmail(to, title, content);
        } catch (Exception e) {
            hasSucceeded = false;
            e.printStackTrace();
        }
        assertThat(hasSucceeded, is(true));
    }
}
