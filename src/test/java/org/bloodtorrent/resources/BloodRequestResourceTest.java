package org.bloodtorrent.resources;

import com.yammer.dropwizard.views.View;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.BloodRequestRepository;
import org.bloodtorrent.view.BloodRequestView;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 1:56
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class BloodRequestResourceTest {
    @Mock
    BloodRequestRepository repository;
    @Mock
    NotifyDonorSendEmailResource mailResource;
    @Mock
    FindingMatchingDonorResource findingMatchingDonorResource;

    BloodRequestResource resource;

    @Before
    public void setup() {
        resource = new BloodRequestResource(repository, mailResource, findingMatchingDonorResource);
    }

    @Test
    public void resourceCallCheck() {
        Assert.assertNotNull(resource);
    }

    @Test
    public void shouldBeRegisteredSuccessfullyWithCorrectRequest() {
        // user: request correctly -> path (requestForBlood) -> result: success
        BloodRequest bloodRequest = createBloodRequest();
        Map<String,Object> jsonMap = resource.requestForBlood(bloodRequest.getFirstName(), bloodRequest.getLastName(), bloodRequest.getHospitalAddress(),
                bloodRequest.getCity(), bloodRequest.getState(), bloodRequest.getPhone(),
                bloodRequest.getEmail(), bloodRequest.getGender(), "03-11-2012",
                bloodRequest.getBloodGroup(), String.valueOf(bloodRequest.getBloodVolume()), bloodRequest.getRequesterType());

        assertThat(String.valueOf(jsonMap.get("result")), is("success"));

        verify(repository).insert(any(BloodRequest.class));
    }

    @Test
    public void wrongRequestShouldBeFilteredWithErrorMessage() {
        // user: request incorrectly -> path (requestForBlood) -> result: fail
        BloodRequest bloodRequest = createBloodRequest();
        bloodRequest.setFirstName("");

        Map<String,Object> jsonMap = resource.requestForBlood(bloodRequest.getFirstName(), bloodRequest.getLastName(), bloodRequest.getHospitalAddress(),
                bloodRequest.getCity(), bloodRequest.getState(), bloodRequest.getPhone(),
                bloodRequest.getEmail(), bloodRequest.getGender(), "03-11-2012",
                bloodRequest.getBloodGroup(), String.valueOf(bloodRequest.getBloodVolume()), bloodRequest.getRequesterType());

        assertThat(String.valueOf(jsonMap.get("result")), is("fail"));
    }

    @Test
    public void shouldSendEmailOnSuccessfulRequest() {
        when(repository.get("any id")).thenReturn(createBloodRequest());
        View view = resource.forwardBloodRequestResult("any id");

        assertTrue(view instanceof BloodRequestView);   // TODO  assertThat((BloodRequestView) view, isA(BloodRequestView.class));

        verify(mailResource).sendNotifyEmail(anyListOf(User.class), any(BloodRequest.class));
    }

    @Test
    public void shouldFindMatchingDonorsOnSuccessfulRequest() throws IllegalDataException {
        when(repository.get("any id")).thenReturn(createBloodRequest());
        View view = resource.forwardBloodRequestResult("any id");

        assertTrue(view instanceof BloodRequestView);   // TODO  assertThat((BloodRequestView) view, isA(BloodRequestView.class));

        verify(findingMatchingDonorResource).findMatchingDonors(any(BloodRequest.class));
    }


    private BloodRequest createBloodRequest() {
        BloodRequest bloodRequest = new BloodRequest();

        bloodRequest.setFirstName("BOWON");
        bloodRequest.setLastName("KIM");
        bloodRequest.setPhone("0118427020");
        bloodRequest.setEmail("bb@samsung.com");
        bloodRequest.setGender("M");
        bloodRequest.setBloodGroup("O+");
        bloodRequest.setBloodVolume("11");
        bloodRequest.setRequesterType("C");
        bloodRequest.setValidated("N");
        bloodRequest.setState("Chhattisgarh");
        bloodRequest.setCity("seoul");
        bloodRequest.setHospitalAddress("gangnamgu 320-11");
        return bloodRequest;
    }

}
