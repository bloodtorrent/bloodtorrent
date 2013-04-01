package org.bloodtorrent.repository;

import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.testing.unitofwork.ConfigurationParser;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 11:40
 * To change this template use File | Settings | File Templates.
 */
public class SuccessStoryRepositoryTest{
    @Rule
    public UnitOfWorkRule unitOfWorkRule = UnitOfWorkRule.getInstance();
    private SuccessStoryRepository  repository ;

    @Before
    public void setup(){
        repository = new SuccessStoryRepository(unitOfWorkRule.getSessionFactory());
    }

    @Test
    public void testList() throws Exception {
          assertNotNull(repository.list());
    }

	@Test
	public void shouldSelectOneSuccessStoryWithGivenId() {
		SuccessStory story = createSuccessStory();
		repository.insert(story);
		SuccessStory fetchedStory = repository.get(story.getId());
		assertThat(story, is(fetchedStory));
	}

	@Test
	public void newlyCreatedRepositoryShouldHaveNoSuccessStories() {
		assertNull( repository.get("does_not_exist"));
	}

	private SuccessStory createSuccessStory() {
		SuccessStory story = new SuccessStory();
		story.setId("Id_1");
		story.setTitle("Story title");
		story.setThumbnailPath("/path/to/thumbnail");
		story.setVisualResourcePath("/path/to/visualresource");
		story.setSummary("Story summary");
		story.setDescription("Story description");
		story.setShowMainPage("Y");
		story.setCreateDate(new Date());
		return story;
	}
}
