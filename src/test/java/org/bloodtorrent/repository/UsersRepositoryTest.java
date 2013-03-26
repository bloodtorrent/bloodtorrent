package org.bloodtorrent.repository;

import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.testing.unitofwork.ConfigurableIntegrationTest;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.junit.Before;
import org.junit.Rule;
import org.bloodtorrent.dto.User;
import org.junit.Test;

import java.sql.Date;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 11:54
 * To change this template use File | Settings | File Templates.
 */
public class UsersRepositoryTest extends ConfigurableIntegrationTest {
    @Rule
    public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule(configuration.getDatabaseConfiguration(), User.class);

    private UsersRepository repository;

    @Before
    public void setup(){
        repository = new UsersRepository(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void shouldBeExistAfterInsertUser() throws Exception {
        User user = new User();
        String id = "123";
        user.setId(id);
        user.setBirthDay("2013-03-30");
        user.setAddress("address, city, state, India");
        user.setAnonymous(false);
        repository.insert(user);
        assertNotNull(repository.get("123"));
    }
}
