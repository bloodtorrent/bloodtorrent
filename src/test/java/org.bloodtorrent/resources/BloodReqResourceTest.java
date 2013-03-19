package org.bloodtorrent.resources;

import org.bloodtorrent.dto.BloodReq;
import org.bloodtorrent.repository.BloodReqRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 1:56
 * To change this template use File | Settings | File Templates.
 */
public class BloodReqResourceTest {
    private final BloodReqRepository repository = Mockito.mock(BloodReqRepository.class);
    private final BloodReqResource resource = new BloodReqResource(repository);

    @Test
    public void resourceCallCheck() {
        Assert.assertNotNull(resource);
    }

    @Test
    public void createNewBloodReq(){
        BloodReq bloodReq = new BloodReq();

        bloodReq.setFirstName("BOWON");
        bloodReq.setLastName("KIM");
        bloodReq.setLocation("Seoul");
        bloodReq.setPhone("0118427020");
        bloodReq.setEmail("bb@samsung.com");
        bloodReq.setGender("M");
        bloodReq.setAge(30);
        bloodReq.setBloodType("O+");
        bloodReq.setBloodVolume(11);
        bloodReq.setRequesterType("C");
        bloodReq.setValidated("N");
        bloodReq.setDate(new Date());

        resource.createNewBloodRequest(bloodReq);
    }
}
