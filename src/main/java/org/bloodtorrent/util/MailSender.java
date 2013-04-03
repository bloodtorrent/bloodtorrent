package org.bloodtorrent.util;

import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailSender {

    private static final String PROTOCOL = "smtp";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    @Setter
    private MailConfiguration mailConfiguration;
    public void sendEmail(String to, String title, String content) {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", PROTOCOL);
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.auth", "true");
        String adminId = mailConfiguration.getAdminMailId();
        String adminPassword = mailConfiguration.getAdminMailPassword();
        Authenticator authenticator = new SMTPAuthenticator(adminId, adminPassword);
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, authenticator);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(adminId));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);
            message.setContent(content, CONTENT_TYPE);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class SMTPAuthenticator extends Authenticator {
        private PasswordAuthentication passwordAuthentication;
        SMTPAuthenticator(String userName, String password) {
            passwordAuthentication = new PasswordAuthentication(userName, password);
        }
        public PasswordAuthentication getPasswordAuthentication() {
            return passwordAuthentication;
        }
    }
}
