package org.bloodtorrent.repository;

import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 10:31
 * To change this template use File | Settings | File Templates.
 */
public class BloodRequestRepositoryTest {
    @Rule
    public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule("integration-test-configuration.json", BloodRequest.class);

    private BloodRequestRepository  repository ;

    @Before
    public void setup(){
        repository = new BloodRequestRepository(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void testInsert() throws Exception {
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setId("gildong@gmail.com");
        bloodRequest.setBirthday(Date.valueOf("2013-03-26"));
        bloodRequest.setCity("Seoul");
        bloodRequest.setBloodGroup("group");
        repository.insert(bloodRequest);
    }
}
