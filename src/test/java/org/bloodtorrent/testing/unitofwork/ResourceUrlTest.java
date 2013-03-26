package org.bloodtorrent.testing.unitofwork;

import com.google.common.io.Resources;
import org.junit.Test;

import java.net.URL;

import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 26
 * Time: 오전 10:59
 * To change this template use File | Settings | File Templates.
 */
public class ResourceUrlTest {

    @Test
    public void shouldReturnUrlByResourceName(){
        String resourceName = "integration-test-configuration.json";
        URL url = this.getClass().getClassLoader().getResource(resourceName);
        assertNotNull(url);
    }
}
