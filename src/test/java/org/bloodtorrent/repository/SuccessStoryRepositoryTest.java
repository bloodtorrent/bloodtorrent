package org.bloodtorrent.repository;

import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.testing.unitofwork.UnitOfWorkRule;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
    private SessionFactory sessionFactory;

    @Before
    public void setup(){
        sessionFactory = unitOfWorkRule.getSessionFactory();
        repository = new SuccessStoryRepository(sessionFactory);
    }

    @Test
    public void testList() throws Exception {
          assertNotNull(repository.list());
    }

	@Test
	public void shouldSelectOneSuccessStoryWithGivenId() {
		SuccessStory story = createSuccessStory("Id_1", "N");
		repository.insert(story);
		SuccessStory fetchedStory = repository.get(story.getId());
		assertThat(story, is(fetchedStory));
	}

	@Test
	public void newlyCreatedRepositoryShouldHaveNoSuccessStories() {
		assertNull( repository.get("does_not_exist"));
	}

	private SuccessStory createSuccessStory(String id, String showMainPage) {
		SuccessStory story = new SuccessStory();
		story.setId(id);
		story.setTitle("Story title");
		story.setThumbnailPath("/path/to/thumbnail");
		story.setVisualResourcePath("/path/to/visualresource");
		story.setSummary("Story summary");
		story.setDescription("Story description");
		story.setShowMainPage(showMainPage);
		story.setCreateDate(new Date());
		return story;
	}

    private List<String> createSuccessStoryIdList(String id) {
        List<String> successStoryIdList = new ArrayList<String>();

        successStoryIdList.add(id);

        return successStoryIdList;
    }

    @Test
    public void shouldUpdateShowMainPageColumn() {
        String id = "Id1";
        String showMainPage = "N";
        List<String> successStoryIdList = createSuccessStoryIdList(id);
        SuccessStory beforeStory = createSuccessStory(id, showMainPage);
        repository.insert(beforeStory);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
        repository.selectForMain(successStoryIdList);
        SuccessStory afterStory = repository.get(id);
        assertThat(afterStory.getShowMainPage(), is("Y"));
    }

    @Test
    public void shouldUpdateNotShowMainPageColumn() {
        String showMainPage = "Y";
        String notShowMainPage = "N";
        SuccessStory insertStoryFirst = createSuccessStory("Id1",notShowMainPage);
        SuccessStory insertStorySecond = createSuccessStory("Id2", showMainPage);
        repository.insert(insertStoryFirst);
        repository.insert(insertStorySecond);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
        repository.selectForMain(createSuccessStoryIdList("Id2"));
        SuccessStory afterStory = repository.get("Id1");
        assertThat(afterStory.getShowMainPage(), is("N"));
    }
}
