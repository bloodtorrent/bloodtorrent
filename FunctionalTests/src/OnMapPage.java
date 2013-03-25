// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

import static junit.framework.Assert.assertTrue;

public class OnMapPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnMapPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyDisplayedMap() throws Exception {
		assertTrue(browser.area("Your Location").isVisible());			
	}

}