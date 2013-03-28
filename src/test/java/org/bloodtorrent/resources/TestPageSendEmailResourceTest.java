package org.bloodtorrent.resources;

import org.bloodtorrent.view.CommonView;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/28/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestPageSendEmailResourceTest {

    @Test
    public void shouldReturnTestSendEmailPage() {
        TestPageSendEmailResource testPageSendEmailResource = new TestPageSendEmailResource();
        CommonView commonView = testPageSendEmailResource.forwardToTestPage();
        assertThat(commonView.getTemplateName(), is("/ftl/testSendEmail.ftl"));
    }
}
