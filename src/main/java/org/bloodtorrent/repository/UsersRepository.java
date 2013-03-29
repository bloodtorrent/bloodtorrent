package org.bloodtorrent.repository;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.bloodtorrent.dto.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 1:42
 * To change this template use File | Settings | File Templates.
 */
public class UsersRepository extends AbstractDAO<User> {
    public static final int MIN_DAYS_LAST_DONATION = 90;

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UsersRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void insert(User user){
        super.persist(user);
    }

    public User get(String id) {
        return super.get(id);
    }

    /**
     * With given parameter as <code>bloodGroup</code>, return all users which blood type is either equal or unknown.
     * And also the users should be able to donor, which means that his/her last donate date is before 90 days than today.
     * @param bloodGroup A blood group string of A/B/AB/O with Rh+- or just "Unknown". For example it may be "A+".
     * @author James, Scott
     */
    public List<User> listByBloodGroupAndAfter90DaysFromLastDonateDate(String bloodGroup) {
        Query query = currentSession().createQuery("from User u where (u.bloodGroup = :bloodGroup or u.bloodGroup = 'Unknown') and (current_date() - u.lastDonateDate) > " + MIN_DAYS_LAST_DONATION + " ");
        query.setParameter("bloodGroup", bloodGroup);

        return list(query);
    }
}
