// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnCommonHeader {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnCommonHeader(Browser browser) {
		this.browser = browser;
	}

	public void verifyGoHomePage() throws Exception {
		browser.row(0).click();
		browser.span("Blood Torrent").click();
	
	}

}