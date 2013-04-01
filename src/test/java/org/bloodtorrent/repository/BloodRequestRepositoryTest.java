package org.bloodtorrent.repository;

import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.testing.unitofwork.ConfigurationParser;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Date;

import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 10:31
 * To change this template use File | Settings | File Templates.
 */
public class BloodRequestRepositoryTest{
    @Rule
    public UnitOfWorkRule unitOfWorkRule = UnitOfWorkRule.getInstance();

    private BloodRequestRepository  repository ;

    @Before
    public void setup(){
        repository = new BloodRequestRepository(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void testInsert() throws Exception {
        BloodRequest bloodRequest = new BloodRequest();
        String id = "gildong@gmail.com";
        bloodRequest.setId(id);
        bloodRequest.setBirthday("26-03-2013");
        bloodRequest.setCity("Seoul");
        bloodRequest.setBloodGroup("group");
        repository.insert(bloodRequest);
        assertNotNull(repository.get(id));
    }
}
