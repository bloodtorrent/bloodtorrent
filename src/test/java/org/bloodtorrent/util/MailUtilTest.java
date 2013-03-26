package org.bloodtorrent.util;

import org.bloodtorrent.ResourceManager;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailUtilTest {

    private String ADMIN_MAIL = "administrator@bloodtorrent.mygbiz.com";
    private String ADMIN_PASSWORD = "p@ssw0rd";

    @Before
    public void init() {
        MailConfiguration mailConfiguration = new MailConfiguration();
        mailConfiguration.setAdminMailId(ADMIN_MAIL);
        mailConfiguration.setAdminMailPassword(ADMIN_PASSWORD);
        ResourceManager.getInstance().add(mailConfiguration);
    }

    //@Test
    public void mailTest() {
        boolean hasSucceeded = true;
        String to = "administrator@bloodtorrent.mygbiz.com";
        String title = "[BloodTorrent] Mailing Test";
        String content = "<html><body>Hello World: " + System.currentTimeMillis() + "</body></html>";
        MailUtil mailUtil = new MailUtil();
        try {
            mailUtil.sendEmail(to, title, content);
        } catch (Exception e) {
            hasSucceeded = false;
            e.printStackTrace();
        }
        assertThat(hasSucceeded, is(true));
    }
}
