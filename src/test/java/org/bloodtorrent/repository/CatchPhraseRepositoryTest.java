package org.bloodtorrent.repository;

import org.bloodtorrent.dto.CatchPhrase;
import org.bloodtorrent.testing.unitofwork.ConfigurationParser;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/27/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class CatchPhraseRepositoryTest {
    @Rule
    public UnitOfWorkRule unitOfWorkRule = UnitOfWorkRule.getInstance();
    private CatchPhraseRepository repository;

    @Before
    public void setup() {
        repository = new CatchPhraseRepository(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void shouldExistExactlyOnlyOneRecord() {
        CatchPhrase catchPhrase = repository.get();
        assertNotNull(catchPhrase);
        assertThat(catchPhrase.getImagePath().isEmpty(), is(false));
    }

}
