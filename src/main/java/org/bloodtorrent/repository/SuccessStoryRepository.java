package org.bloodtorrent.repository;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.bloodtorrent.dto.SuccessStory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import java.util.List;

public class SuccessStoryRepository extends AbstractDAO<SuccessStory> {

    public SuccessStoryRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * List up at most 3 success stories for showing on main page.
     * @return
     */
    public List<SuccessStory> list() {
        Query query = currentSession().createQuery("from SuccessStory s where s.showMainPage like 'Y' order by s.createDate desc");
        return list(query);
    }

	public void insert(SuccessStory story) {
		super.persist(story);
	}

	public SuccessStory get(String id){

		return super.get(id);
	}

    public List<SuccessStory> getListForSuccessStoriesView() {
        return currentSession().createCriteria(SuccessStory.class).addOrder(Order.desc("createDate")).list();
    }
}
