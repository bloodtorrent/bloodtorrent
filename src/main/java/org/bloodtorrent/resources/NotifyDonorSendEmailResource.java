package org.bloodtorrent.resources;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.util.MailConfiguration;
import org.bloodtorrent.util.MailSender;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
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
        return  content.replaceAll("%:%",":")
                        .replaceAll("<DONOR_NAME>", oneUser.getFirstName() + " " + oneUser.getLastName())
                        .replaceAll("<PATIENT_NAME>", bloodRequest.getFirstName() + " " + bloodRequest.getLastName())
                        .replaceAll("<BLOOD_TYPE>", bloodRequest.getBloodGroup())
                        .replaceAll("<HOSPITAL_ADDRESS>", bloodRequest.getHospitalAddress())
                        .replaceAll("<CELL_PHONE>", bloodRequest.getPhone());
    }
}
