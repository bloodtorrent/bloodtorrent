package org.bloodtorrent.testing.unitofwork;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import org.bloodtorrent.bootstrap.SimpleHibernateBundle;
import org.bloodtorrent.dto.BloodRequest;
import org.hibernate.SessionFactory;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Add to your JUnit4 test:
 * <blockquote><pre>
 * {@Rule}
 * public UnitOfWorkRule unitOfWorkRule = new UnitOfWorkRule("conf/myproject-configuration-test.json", ... entity classes);
 * {@Before}
 * public void setUp(){
 *     myDao = new MyDao(unitOfWorkRule);
 * }
 * </pre></blockquote>
 * and you can test your DAO layer with DB under transaction.
 * By default all after all changes in db <b>rollback</b> will be called.
 * If commit needed - use new UnitOfWorkRule(...).commitDefault();
 */

public class UnitOfWorkRule extends TestWatcher {

    UnitOfWorkHelper unitOfWorkHelper = new UnitOfWorkHelper();

    boolean commitDefault = false;

    private static UnitOfWorkRule instance = new UnitOfWorkRule();

    public static UnitOfWorkRule getInstance() {
        return instance;
    }

    public UnitOfWorkRule(){
        try {
            unitOfWorkHelper.initDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UnitOfWorkRule commitDefault(){
        commitDefault = true;
        return this;
    }

    @Override
    protected void starting(Description description) {
        unitOfWorkHelper.startSession();
    }

    @Override
    protected void finished(Description description) {
        if(commitDefault)
            unitOfWorkHelper.commitAndCloseSession();
        else
            unitOfWorkHelper.rollbackAndCloseSession();
    }

    public SessionFactory getSessionFactory() {
        return unitOfWorkHelper.getSessionFactory();
    }
}