package org.bloodtorrent.repository;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.bloodtorrent.dto.User;
import org.hibernate.SessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 1:42
 * To change this template use File | Settings | File Templates.
 */
public class UsersRepository extends AbstractDAO<User> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UsersRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public boolean isExistentId(String id){
        return get(id) != null;
    }

    public void insert(User user){
        super.persist(user);
    }

    public User get(String id) {
        return super.get(id);
    }
}
