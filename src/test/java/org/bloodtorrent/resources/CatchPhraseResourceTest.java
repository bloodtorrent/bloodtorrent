package org.bloodtorrent.resources;

import org.bloodtorrent.dto.CatchPhrase;
import org.bloodtorrent.repository.CatchPhraseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class CatchPhraseResourceTest {

    @Mock
    private CatchPhraseRepository repository;

    private CatchPhraseResource resource;

    @Before
    public void setUpResource() {
        resource = new CatchPhraseResource(repository);
        when(repository.get()).thenReturn(aNewCatchPhrase());
    }

    private CatchPhrase aNewCatchPhrase() {
        CatchPhrase catchPhrase = new CatchPhrase();
        catchPhrase.setImagePath("/images/cp_test.png");
        catchPhrase.setId("TEST001");
        return catchPhrase;
    }

    @Test
    public void shouldGetCorrectCatchPhrase() {
        CatchPhrase catchPhrase = resource.get();
        assertNotNull(catchPhrase);
        assertThat(catchPhrase.getImagePath().isEmpty(), is(false));

        verify(repository).get();
    }


}
