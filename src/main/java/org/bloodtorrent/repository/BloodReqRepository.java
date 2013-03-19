package org.bloodtorrent.repository;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.bloodtorrent.dto.BloodReq;
import org.bloodtorrent.dto.User;
import org.hibernate.SessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 8:18
 * To change this template use File | Settings | File Templates.
 */
public class BloodReqRepository extends AbstractDAO<BloodReq>{
    public BloodReqRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void insert(BloodReq bloodReq){
        super.persist(bloodReq);
    }
}
