package org.bloodtorrent.bootstrap;

import org.bloodtorrent.repository.BloodRequestRepository;
import org.bloodtorrent.repository.CatchPhraseRepository;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.resources.*;
import org.eclipse.jetty.server.SessionManager;
import org.hibernate.SessionFactory;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class ResourceCreator {

    public Set<Object> createResources(SessionFactory sessionFactory, SimpleConfiguration configuration, SessionManager httpsSessionManager) {

        final UsersRepository userRepository = new UsersRepository(sessionFactory);
        final BloodRequestRepository bloodReqRepository = new BloodRequestRepository(sessionFactory);
        final SuccessStoryRepository successStoryRepository = new SuccessStoryRepository(sessionFactory);
        final CatchPhraseRepository catchPhraseRepository = new CatchPhraseRepository(sessionFactory);

        NotifyDonorSendEmailResource mailResource = new NotifyDonorSendEmailResource();
        mailResource.setMailConfiguration(configuration.getMailConfiguration());

        MainResource mainResource = new MainResource(httpsSessionManager);
        SuccessStoryResource successStoryResource = new SuccessStoryResource(httpsSessionManager, successStoryRepository);
        mainResource.setSuccessStoryResource(successStoryResource);
        CatchPhraseResource catchPhraseResource = new CatchPhraseResource(catchPhraseRepository);
        mainResource.setCatchPhraseResource(catchPhraseResource);

        AdminResource adminResource = new AdminResource(httpsSessionManager);
        adminResource.setMainResource(mainResource);

        LogOffResource logOffResource = new LogOffResource(httpsSessionManager);
        logOffResource.setMainResource(mainResource);

        FindingMatchingDonorResource findingMatchingDonorResource = new FindingMatchingDonorResource(userRepository);

        BloodRequestResource bloodRequestResource = new BloodRequestResource(bloodReqRepository, mailResource, findingMatchingDonorResource);

        LoginFailResource loginFailResource = new LoginFailResource();
        loginFailResource.setMainResource(mainResource);

        return newHashSet(mainResource,
                          successStoryResource,
                          catchPhraseResource,
                          adminResource,
                          logOffResource,
                          loginFailResource,
                          findingMatchingDonorResource,
                          bloodRequestResource,
                          mailResource,
                          new UsersResource(userRepository),
                          new LoginResource(httpsSessionManager, userRepository),
                          new TestPageSendEmailResource(),
                          new TestSendEmailResource());
    }
}
