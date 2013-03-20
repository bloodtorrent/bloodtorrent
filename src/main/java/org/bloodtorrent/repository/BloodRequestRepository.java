package org.bloodtorrent.repository;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.bloodtorrent.dto.BloodRequest;
import org.hibernate.SessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 8:18
 * To change this template use File | Settings | File Templates.
 */
public class BloodRequestRepository extends AbstractDAO<BloodRequest>{
    public BloodRequestRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void insert(BloodRequest bloodRequest){
        super.persist(bloodRequest);
    }
}
