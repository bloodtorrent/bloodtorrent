package org.bloodtorrent.repository;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.bloodtorrent.dto.CatchPhrase;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CatchPhraseRepository extends AbstractDAO<CatchPhrase> {

    public CatchPhraseRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public CatchPhrase get() {
        Query query = currentSession().createQuery("from CatchPhrase c");
        return uniqueResult(query);
    }
}
