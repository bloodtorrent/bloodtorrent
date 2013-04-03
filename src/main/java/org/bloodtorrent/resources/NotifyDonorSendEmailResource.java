package org.bloodtorrent.resources;

import lombok.Setter;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.util.MailConfiguration;
import org.bloodtorrent.util.MailSender;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/27/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotifyDonorSendEmailResource {
    @Setter
    private MailConfiguration mailConfiguration;

    public void sendNotifyEmail(List<User> userList, BloodRequest bloodRequest) {
        MailSender mailSender = new MailSender();
        mailSender.setMailConfiguration(mailConfiguration);
        for(User oneUser : userList){
            String title = mailConfiguration.getDonorTitle();
            String content = rePlaceContent(mailConfiguration.getDonorContent(), oneUser, bloodRequest);
            mailSender.sendEmail(oneUser.getId(), title, content);
        }
    }


    public String rePlaceContent(String content, User oneUser, BloodRequest bloodRequest) {
        content = content.replaceAll("%:%",":");
        content = content.replaceAll("<DONOR_NAME>", oneUser.getFirstName() + " " + oneUser.getLastName());
        content = content.replaceAll("<PATIENT_NAME>", bloodRequest.getFirstName() + " " + bloodRequest.getLastName());
        content = content.replaceAll("<BLOOD_TYPE>", bloodRequest.getBloodGroup());
        content = content.replaceAll("<HOSPITAL_ADDRESS>", bloodRequest.getHospitalAddress());
        content = content.replaceAll("<CELL_PHONE>", bloodRequest.getPhone());
        return content;
    }
}
