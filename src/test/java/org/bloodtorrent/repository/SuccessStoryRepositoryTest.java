package org.bloodtorrent.repository;

import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 11:40
 * To change this template use File | Settings | File Templates.
 */
public class SuccessStoryRepositoryTest {
    @Rule
    public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule("integration-test-configuration.json", SuccessStory.class);
    private SuccessStoryRepository  repository ;

    @Before
    public void setup(){
        repository = new SuccessStoryRepository(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void testList() throws Exception {
          assertNotNull(repository.list());
    }
}
